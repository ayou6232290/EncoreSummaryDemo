package com.example.enoresummarydemo.fragment.fram;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.activity.CommonFragmentActivity;
import com.example.enoresummarydemo.activity.SlidingActivity;
import com.example.enoresummarydemo.fragment.base.BaseFragment;

public class FramFragment extends BaseFragment {

	private Button mBtnSlidingMenu;

	private Button mBtnViewPagerFragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_fram, null);
		initUI(view);
		return view;
	}

	public void initUI(View view) {
		mBtnSlidingMenu = (Button) view.findViewById(R.id.btnSlidingMenu);
		mBtnViewPagerFragment = (Button) view.findViewById(R.id.btnViewPagerFragment);

		mBtnSlidingMenu.setOnClickListener(mOnClickListener);
		mBtnViewPagerFragment.setOnClickListener(mOnClickListener);
	}

	public OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnSlidingMenu:
				commonStartActivity(new Intent(getActivity(),SlidingActivity.class));
				break;
			case R.id.btnViewPagerFragment:
//				commonStartActivity(new Intent());
				CommonFragmentActivity.startCommonActivity(getActivity(), ViewPagerFragment.class, true, null);
				break;
			}
		}
	};

}
