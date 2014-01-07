package com.example.enoresummarydemo.fragment.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.activity.CommonFragmentActivity;
import com.example.enoresummarydemo.activity.JazzyActivity;
import com.example.enoresummarydemo.activity.SlidingActivity;
import com.example.enoresummarydemo.fragment.base.BaseFragment;

public class ViewFragment extends BaseFragment {
	private Button mBtnJazzyViewPager;

	private Button mBtnImageCache;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_views, null);
		initUI(view);
		return view;
	}

	public void initUI(View view) {
		mBtnJazzyViewPager = (Button) view.findViewById(R.id.btnJazzyViewPager);
		mBtnImageCache = (Button) view.findViewById(R.id.btnImageCache);

		mBtnJazzyViewPager.setOnClickListener(mOnClickListener);
		mBtnImageCache.setOnClickListener(mOnClickListener);
	}

	public OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnJazzyViewPager:
				commonStartActivity(new Intent(getActivity(), JazzyActivity.class));
				break;
			case R.id.btnImageCache:
				String pkg = ImageCacheFragment.class.getName();
				CommonFragmentActivity.startCommonActivity(getActivity(), pkg, true, null);
				break;
			}
		}
	};

}
