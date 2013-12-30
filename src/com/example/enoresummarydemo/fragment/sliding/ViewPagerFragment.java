package com.example.enoresummarydemo.fragment.sliding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.encore.lib.sliding.SlidingMenu;

import com.example.enoresummarydemo.fragment.base.SlidingBaseFragment;

public class ViewPagerFragment extends SlidingBaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setSlidingMenuTouchModel(SlidingMenu.TOUCHMODE_FULLSCREEN);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	/**
	 * 初始化UI
	 * 
	 * @param view
	 */
//	public void initUI(View view) {
//
//		setTitle(R.drawable.icon_title_recommend);
//
//		mRgTabs = (RadioGroup) view.findViewById(R.id.rgTabs);
//		mViewPager = (ViewPager) view.findViewById(R.id.pager);
//		mIndicator = (BitmapPageIndicator) view.findViewById(R.id.indicator);
//
//		mViewPager.setAdapter(new FragmentAdapter(getChildFragmentManager()));
//		mIndicator.setViewPager(mViewPager);
//		mRgTabs.setOnCheckedChangeListener(this);
//		mIndicator.setOnPageChangeListener(mOnPageChangeListener);
//
//		// 取消初始动画效果
//		mIndicator.setFades(false);
//	}
//
//	@Override
//	public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//		if (mViewPager == null || mIndicator == null)
//			return;
//		// 切换当前项
//		switch (checkedId) {
//		// case R.id.rboHot:
//		// mIndicator.setCurrentItem(SUBTAB_HOT);
//		// break;
//		case R.id.rboSpecial:
//			mIndicator.setCurrentItem(SUBTAB_SPECIAL);
//			break;
//		case R.id.rboTop:
//			mIndicator.setCurrentItem(SUBTAB_TOP);
//			break;
//
//		}
//	}

	// viewPager data adapter
//	public class FragmentAdapter extends FragmentPagerAdapter {
//
//		public FragmentAdapter(FragmentManager fm) {
//			super(fm);
//		}
//
//		@Override
//		public Fragment getItem(int index) {
//			BaseFragment newFragment = null;
//			switch (index) {
//			// case SUBTAB_HOT:
//			// newFragment = new HotFragment();
//			// break;
//			case SUBTAB_SPECIAL:
//				newFragment = new SpecialFragment();
//				break;
//			case SUBTAB_TOP:
//				newFragment = new TopFragment();
//				break;
//			}
//			return newFragment;
//		}
//
//		@Override
//		public int getCount() {
//			// TODO Auto-generated method stub
//			return SUBTAB_COUNT;
//		}
//	}
//
//	@Override
//	public void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
//	}
//	
//	@Override
//	public void onDestroyView() {
//		// TODO Auto-generated method stub
//		super.onDestroyView();
//		mViewPager.removeAllViews();
//		
//		mIndicator = null;
//		mViewPager = null;
//		mRgTabs = null;
//	
//	}
//
//	/**
//	 * view page 切换监听
//	 */
//	public OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
//
//		@Override
//		public void onPageScrollStateChanged(int arg0) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void onPageScrolled(int arg0, float arg1, int arg2) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void onPageSelected(int position) {
//			// TODO Auto-generated method stub
//			if (getActivity() == null || !(getActivity() instanceof SlidingBaseActivity)) {
//				return;
//			}
//			SlidingBaseActivity slidingBaseActivity = (SlidingBaseActivity) getActivity();
//			switch (position) { // 最左边的热门tab,可以全部范围滑动到菜单, 专题,榜单,只能在边缘滑动才能打开左侧菜单
//			case 0:
//				slidingBaseActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//				break;
//			default:
//				slidingBaseActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
//				break;
//			}
//		}
//	};
}
