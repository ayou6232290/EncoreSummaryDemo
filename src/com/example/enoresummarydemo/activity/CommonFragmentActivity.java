package com.example.enoresummarydemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.example.enoresummarydemo.BaseActivity;
import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.fragment.base.BaseFragment;
import com.example.enoresummarydemo.fragment.sliding.ViewPagerFragment;

/**
 * 公共加载fragmentAcitivty
 * 
 * @author Encore.liang
 * 
 */
public class CommonFragmentActivity extends BaseActivity {
	
	public static final String KEY_PACKAGENAME = "packageName";
	public static final String KEY_ISSWIPEBACK = "isSwipeBack";

	private BaseFragment mFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		boolean isSwipeBack = getIntent().getBooleanExtra(KEY_ISSWIPEBACK, false);
		setIsInnitSwipeBack(isSwipeBack);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.container_fram);

		String pkg = getIntent().getStringExtra(KEY_PACKAGENAME);
		Bundle bundle = getIntent().getExtras();
		
		try {
			//获取到需要启动的包
			mFragment = (BaseFragment) Class.forName(pkg).newInstance();
			
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			if(bundle != null){
				mFragment.setArguments(bundle);
			}
			ft.add(R.id.container, mFragment);
			ft.commit();
			getSupportFragmentManager().executePendingTransactions();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 启动公共activity
	 * @param activity
	 * @param pkg 需要启动的包名
	 * @param isSwipeBack 是否滑动退出activity
	 * @param bundle 需要
	 */
	public void startCommonActivity(Activity activity, String pkg, boolean isSwipeBack, Bundle bundle) {
		if (pkg == null || pkg.equals("")) {
			return;
		}
		Intent intent = new Intent(activity, CommonFragmentActivity.class);
		intent.putExtra(KEY_PACKAGENAME, pkg);
		intent.putExtra(KEY_ISSWIPEBACK, isSwipeBack);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivity(intent);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(mFragment != null){
				boolean flag = mFragment.onKeyDown(keyCode, event);
				if(!flag){
					return false;
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mFragment != null){
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.detach(mFragment);
			ft.remove(mFragment);
			mFragment = null;
		}
	}
}
