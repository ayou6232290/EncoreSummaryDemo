package com.example.enoresummarydemo.fragment.base;

import com.example.enoresummarydemo.activity.SlidingBaseActivity;

/**
 * 抽屉类BaseFragment
 * 
 * @author Encore.liang
 * 
 */
public class SlidingBaseFragment extends BaseFragment {

	/**
	 * 设置左侧菜单滑动方式 SlidingMenu.TOUCHMODE_FULLSCREEN : 全屏滑动
	 * SlidingMenu.TOUCHMODE_MARGIN : 边缘滑动 (适合viewPage页的fragment)
	 * 
	 * @param model
	 */
	public void setSlidingMenuTouchModel(int model) {
		if (getActivity() == null || !(getActivity() instanceof SlidingBaseActivity)) {
			return;
		}
		SlidingBaseActivity slidingBaseActivity = (SlidingBaseActivity) getActivity();
		slidingBaseActivity.getSlidingMenu().setTouchModeAbove(model);
	}

}
