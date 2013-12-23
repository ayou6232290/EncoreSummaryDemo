package com.example.enoresummarydemo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import cn.encore.lib.sliding.SlidingMenu;

import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.fragment.sliding.PropertiesFragment;
import com.example.enoresummarydemo.widget.Header;

public class SlidingActivity extends SlidingBaseActivity {

	// 菜单view
	public Header mHeader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sliding_frame);
		// 初始化ui
		initUI();
		// 添加显示的第一个fragment
		attchFristFragment();
	}

	/**
	 * init UI
	 */
	public void initUI() {
		mHeader = (Header) findViewById(R.id.header);
		if (mHeader != null) {
			mHeader.setLeftBtn(R.drawable.ic_launcher, mOnClickListener);
			mHeader.removeRightBtn();
		}
	}

	/**
	 * 点击事件
	 */
	public OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.left:
				toggle();
				break;
			}
		}
	};

	/**
	 * 显示首页的fragment
	 */
	public void attchFristFragment() {
		PropertiesFragment viewpagerContainerFragment = new PropertiesFragment();
		// replace
		changeFragmentToContainer(viewpagerContainerFragment, String.valueOf(R.id.menuProperties), false, false);
	}

	/**
	 * 设置底部菜单控制栏的title显示
	 * 
	 * @param title
	 */
	public void setTitle(String content) {
		if (mHeader != null) {
			mHeader.setTitle(content);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu = null;
	}
}
