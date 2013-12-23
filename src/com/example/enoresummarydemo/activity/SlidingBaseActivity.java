package com.example.enoresummarydemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;
import cn.encore.lib.sliding.SlidingMenu;
import cn.encore.lib.sliding.app.SlidingFragmentActivity;

import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.fragment.MenuFragment;
import com.example.enoresummarydemo.fragment.base.BaseFragment;

/**
 * 左侧菜单BaseActivity
 * 
 * @author Encore.liang
 * 
 */
public class SlidingBaseActivity extends SlidingFragmentActivity {

	/**
	 * 菜单fragment
	 */
	public MenuFragment mMenuFragment;

	// 是否退出app
	public boolean mIsExitApp = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 设置菜单view
		setBehindContentView(R.layout.menu_frame);
		// 给菜单view赋值
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mMenuFragment = new MenuFragment();
			t.replace(R.id.menu_frame, mMenuFragment);
			t.commit();
		} else {
			mMenuFragment = (MenuFragment) this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		// IntentFilter filter = new IntentFilter();
		// filter.addAction(BaseActivity.RECEVICE_EXIT_APP);
		// this.registerReceiver(mBroadcastReceiver, filter);
	}

	/**
	 * 改变主内容view的fragment
	 * 
	 * @param fragment
	 *            需要替换的fragment
	 * @param TAG
	 *            标记
	 * @param isAddTobackStack
	 *            是否添加到后退堆栈
	 * @param isEnbaleAnimtion
	 *            是否开打动画
	 */
	public void changeFragmentToContainer(BaseFragment fragment, String tag, boolean isAddTobackStack, boolean isEnbaleAnimtion) {
		Fragment f = getSupportFragmentManager().findFragmentByTag(tag);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

		// 启动动画
		if (isEnbaleAnimtion) {
			// ft.setCustomAnimations(R.anim.fragment_fade_in,
			// R.anim.fragment_fade_out);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);// 设置动画效果

		}
		// 后退堆栈
		if (isAddTobackStack) {
			ft.addToBackStack(null);
		}

		if (f != null) {
			ft.attach(f);
		} else {
			ft.replace(R.id.container, fragment, tag); // 替换新的fragment,
		}
		ft.commit();
		getSupportFragmentManager().executePendingTransactions();
	}

	/**
	 * fragment 是否已创建
	 * 
	 * @param tag
	 *            fragment tag;
	 * @return
	 */
	public boolean isFragmentCreate(String tag) {
		Fragment f = getSupportFragmentManager().findFragmentByTag(tag);
		if (f == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// 菜单没有打开,退出程序
			if (!getSlidingMenu().isMenuShowing()) {
				finish();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	public Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			mIsExitApp = false;
		}
	};

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			finish();
		}
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			unregisterReceiver(mBroadcastReceiver);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
