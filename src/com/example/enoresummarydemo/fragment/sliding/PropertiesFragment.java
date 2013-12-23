package com.example.enoresummarydemo.fragment.sliding;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import cn.encore.lib.sliding.SlidingMenu;

import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.activity.SlidingActivity;
import com.example.enoresummarydemo.fragment.BaseFragment;

public class PropertiesFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_properties, null);
		init(view);
		return view;
	}

	public void init(View view) {
		
		((SlidingActivity)getActivity()).setTitle("Properties");
		
		RadioGroup mode = (RadioGroup) view.findViewById(R.id.mode);
		mode.check(R.id.left);
		mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				SlidingMenu sm = ((SlidingActivity) getActivity()).getSlidingMenu();
				switch (checkedId) {
				case R.id.left:
					sm.setMode(SlidingMenu.LEFT);
					sm.setShadowDrawable(R.drawable.shadow);
					break;
				case R.id.right:
					sm.setMode(SlidingMenu.RIGHT);
					sm.setShadowDrawable(R.drawable.shadowright);
					break;
				case R.id.left_right:
					sm.setMode(SlidingMenu.LEFT_RIGHT);
					sm.setSecondaryMenu(R.layout.menu_frame_two);
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame_two, new SampleListFragment()).commit();
					sm.setSecondaryShadowDrawable(R.drawable.shadowright);
					sm.setShadowDrawable(R.drawable.shadow);
				}
			}
		});

		// touch mode stuff
		RadioGroup touchAbove = (RadioGroup) view.findViewById(R.id.touch_above);
		touchAbove.check(R.id.touch_above_full);
		touchAbove.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.touch_above_full:
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					break;
				case R.id.touch_above_margin:
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
					break;
				case R.id.touch_above_none:
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
					break;
				}
			}
		});

		// scroll scale stuff
		SeekBar scrollScale = (SeekBar) view.findViewById(R.id.scroll_scale);
		scrollScale.setMax(1000);
		scrollScale.setProgress(333);
		scrollScale.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				getSlidingMenu().setBehindScrollScale((float) seekBar.getProgress() / seekBar.getMax());
			}
		});

		// behind width stuff
		SeekBar behindWidth = (SeekBar) view.findViewById(R.id.behind_width);
		behindWidth.setMax(1000);
		behindWidth.setProgress(750);
		behindWidth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				float percent = (float) seekBar.getProgress() / seekBar.getMax();
				getSlidingMenu().setBehindWidth((int) (percent * getSlidingMenu().getWidth()));
				getSlidingMenu().requestLayout();
			}
		});

		// shadow stuff
		CheckBox shadowEnabled = (CheckBox) view.findViewById(R.id.shadow_enabled);
		shadowEnabled.setChecked(true);
		shadowEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
					getSlidingMenu().setShadowDrawable(getSlidingMenu().getMode() == SlidingMenu.LEFT ? R.drawable.shadow : R.drawable.shadowright);
				else
					getSlidingMenu().setShadowDrawable(null);
			}
		});
		SeekBar shadowWidth = (SeekBar) view.findViewById(R.id.shadow_width);
		shadowWidth.setMax(1000);
		shadowWidth.setProgress(75);
		shadowWidth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				float percent = (float) seekBar.getProgress() / (float) seekBar.getMax();
				int width = (int) (percent * (float) getSlidingMenu().getWidth());
				getSlidingMenu().setShadowWidth(width);
				getSlidingMenu().invalidate();
			}
		});

		// fading stuff
		CheckBox fadeEnabled = (CheckBox) view.findViewById(R.id.fade_enabled);
		fadeEnabled.setChecked(true);
		fadeEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				getSlidingMenu().setFadeEnabled(isChecked);
			}
		});
		SeekBar fadeDeg = (SeekBar) view.findViewById(R.id.fade_degree);
		fadeDeg.setMax(1000);
		fadeDeg.setProgress(666);
		fadeDeg.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				getSlidingMenu().setFadeDegree((float) seekBar.getProgress() / seekBar.getMax());
			}
		});
	}

	public SlidingMenu getSlidingMenu() {
		return ((SlidingActivity) getActivity()).getSlidingMenu();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return true;
	}
}
