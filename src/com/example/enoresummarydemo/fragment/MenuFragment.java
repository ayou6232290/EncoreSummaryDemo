package com.example.enoresummarydemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.encore.lib.sliding.CustomViewAbove;

import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.activity.SlidingBaseActivity;
import com.example.enoresummarydemo.fragment.base.BaseFragment;
import com.example.enoresummarydemo.fragment.sliding.EmptyFragment;
import com.example.enoresummarydemo.fragment.sliding.PropertiesFragment;
import com.example.enoresummarydemo.fragment.sliding.ViewPagerFragment;

/**
 * 左侧菜单fragment
 * 
 * @author Encore.liang
 * 
 */
public class MenuFragment extends BaseFragment {

	// 最后选中的fragment
	private BaseFragment mLastFragment;
	// 当前tag
	private String mCurrentTag;

	public View mMenuAbout;
	// menu参数设置
	public View mMenuProperties;
	// menuViewPager
	public View mMenuViewPager;
	// 
	public View mMenu03;
	// 
	public View mMenu04;
	// 
	public View mMenu05;

	// 是否启动切换fragment动画
	private boolean mIsEnbaleAnimtion = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View menuView = inflater.inflate(R.layout.fragment_menu, null);
		initUI(menuView);
		return menuView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		// 初始默认设置lastFragment
		String tag = String.valueOf(R.id.menuProperties);
		BaseFragment recommendFragment = (BaseFragment) ((SlidingBaseActivity) getActivity()).getSupportFragmentManager().findFragmentByTag(tag);
		if (recommendFragment != null) {
			mLastFragment = recommendFragment;
		}
	}

	/**
	 * init menu UI
	 * 
	 * @param menuView
	 */
	public void initUI(View menuView) {
		mMenuProperties = menuView.findViewById(R.id.menuProperties);
		mMenuViewPager = menuView.findViewById(R.id.menuViewPager);
		mMenu03 = menuView.findViewById(R.id.menu03);
		mMenu04 = menuView.findViewById(R.id.menu04);
		mMenu05 = menuView.findViewById(R.id.menu05);
		mMenuAbout = menuView.findViewById(R.id.menuAbout);

		mMenuProperties.setOnClickListener(mMenuOnClickListener);
		mMenuViewPager.setOnClickListener(mMenuOnClickListener);
		mMenu03.setOnClickListener(mMenuOnClickListener);
		mMenu04.setOnClickListener(mMenuOnClickListener);
		mMenu05.setOnClickListener(mMenuOnClickListener);
		mMenuAbout.setOnClickListener(mOnClickListener);

		mMenuProperties.setSelected(true);
	}

	public OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.menuAbout:
				break;

			default:
				break;
			}
		}
	};

	public Handler mHandler = new Handler() {
	};

	/**
	 * 菜单点击事件
	 */
	public OnClickListener mMenuOnClickListener = new OnClickListener() {

		@Override
		public void onClick(final View v) {
			// TODO Auto-generated method stub

			// 滑动回主内容view
			if (getActivity() != null) {
				((SlidingBaseActivity) getActivity()).toggle();
			}

			int duration = 0;

			if (CustomViewAbove.MENU_DURATION > 200) {
				duration = CustomViewAbove.MENU_DURATION - 200;
			}

			mHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					mCurrentTag = String.valueOf(v.getId());

					changeMenu(v);
				}
			}, duration);
		}
	};
	/**
	 * 改变menu
	 * @param v
	 */
	public void changeMenu(View v)
	{
		BaseFragment newFragment = (BaseFragment) ((SlidingBaseActivity) getActivity()).getSupportFragmentManager().findFragmentByTag(String.valueOf(v.getId()));
		boolean isNew = false;
		if (newFragment == null) {
			isNew = true;
			switch (v.getId()) {
			case R.id.menuViewPager:
				newFragment = new ViewPagerFragment();
				break;
			case R.id.menuProperties:
				newFragment = new PropertiesFragment();
				break;
			case R.id.menu03:
				newFragment = new EmptyFragment();
				break;
			case R.id.menu04:
				newFragment = new EmptyFragment();
				break;
			case R.id.menu05:
				newFragment = new EmptyFragment();
				break;
			}
		}
		setCurrentTagBg(v.getId());
		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
		// 启动动画
		if (mIsEnbaleAnimtion) {
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);// 设置动画效果
		}
		// 判断是否和最后的fragment不同,做相应操作
		if (newFragment != mLastFragment) {
			if (mLastFragment != null) {
				ft.detach(mLastFragment);
			}
			if (isNew) {
				ft.add(R.id.container, newFragment, mCurrentTag);
			} else {
				ft.attach(newFragment);
			}
			mLastFragment = newFragment;
		}
		ft.commit();
		getActivity().getSupportFragmentManager().executePendingTransactions();
	}

	/**
	 * 检查fragment是否已经创建
	 * 
	 * @param tag
	 */
	public boolean checkIsCreate(String tag) {
		boolean isCreate = false; // 是否创建了fragment
		if (getActivity() != null) {
			isCreate = ((SlidingBaseActivity) getActivity()).isFragmentCreate(tag);
		}
		return isCreate;
	}

	public Fragment getLastFragment() {
		return mLastFragment;
	}

	/**
	 * 设置当前bg
	 * 
	 * @param currentTag
	 */
	public void setCurrentTagBg(int currentTag) {

		mMenuProperties.setSelected(false);
		mMenuViewPager.setSelected(false);
		mMenu03.setSelected(false);
		mMenu04.setSelected(false);
		mMenu05.setSelected(false);

		switch (currentTag) {
		case R.id.menuViewPager:
			mMenuViewPager.setSelected(true);
			break;
		case R.id.menuProperties:
			mMenuProperties.setSelected(true);
			break;
		case R.id.menu03:
			mMenu03.setSelected(true);
			break;
		case R.id.menu04:
			mMenu04.setSelected(true);
			break;
		case R.id.menu05:
			mMenu05.setSelected(true);
			break;
		}
	}

}
