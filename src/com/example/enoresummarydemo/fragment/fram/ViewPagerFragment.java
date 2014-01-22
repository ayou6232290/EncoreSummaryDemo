package com.example.enoresummarydemo.fragment.fram;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.fragment.base.BaseFragment;
import com.example.enoresummarydemo.fragment.viewpager.CategoryFragment;
import com.example.enoresummarydemo.fragment.viewpager.SingerFragment;
import com.example.enoresummarydemo.fragment.viewpager.TopFragment;
import com.viewpagerindicator.UnderlinePageIndicator;

public class ViewPagerFragment extends BaseFragment implements OnCheckedChangeListener {
	// 歌手
	public static final int SUBTAB_SINGER = 0;
	// 分类
	public static final int SUBTAB_CATEGORY = 1;
	// 排行
	public static final int SUBTAB_TOP = 2;
	// 总tab数
	public static final int SUBTAB_COUNT = SUBTAB_TOP + 1;

	private View mContentView;

	// tabs
	private RadioGroup mRgTabs;
	// viewPager
	private ViewPager mViewPager;
	// indicator
	private UnderlinePageIndicator mIndicator;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mContentView = inflater.inflate(R.layout.fragment_viewpager, null);
		initUI(mContentView);
		return mContentView;
	}

	/**
	 * 初始化ui
	 * 
	 * @param v
	 *            当前view
	 */
	public void initUI(View v) {
		mRgTabs = (RadioGroup) v.findViewById(R.id.rgTabs);
		mViewPager = (ViewPager) v.findViewById(R.id.pager);
		mIndicator = (UnderlinePageIndicator) v.findViewById(R.id.indicator);

		mViewPager.setAdapter(new FragmentAdapter(getChildFragmentManager()));
		mIndicator.setViewPager(mViewPager);
		mRgTabs.setOnCheckedChangeListener(this);
		// 取消初始动画效果
		mIndicator.setFades(false);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		if (mViewPager == null || mIndicator == null)
			return;
		// 切换当前项
		switch (checkedId) {
		case R.id.rboSinger:
			mIndicator.setCurrentItem(SUBTAB_SINGER);
			break;
		case R.id.rboCategory:
			mIndicator.setCurrentItem(SUBTAB_CATEGORY);
			break;
		case R.id.rboTop:
			mIndicator.setCurrentItem(SUBTAB_TOP);
			break;

		}
	}

	// viewPager data adapter
	public class FragmentAdapter extends FragmentPagerAdapter {

		public FragmentAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int index) {
			BaseFragment newFragment = null;
			switch (index) {
			case SUBTAB_SINGER:
				 newFragment = new SingerFragment();
				break;
			case SUBTAB_CATEGORY:
				 newFragment = new CategoryFragment();
				break;
			case SUBTAB_TOP:
				 newFragment = new TopFragment();
				break;
			}
			return newFragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return SUBTAB_COUNT;
		}
	}

}
