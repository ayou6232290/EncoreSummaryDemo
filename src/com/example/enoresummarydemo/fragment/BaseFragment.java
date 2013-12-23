package com.example.enoresummarydemo.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

/**
 * BaseFragment
 * 
 * @author Encore.liang
 * 
 */
public abstract class BaseFragment extends Fragment {
	
	/**
	 * 公共startActivify
	 * @param intent
	 */
	public void commonStartActivity(Intent intent) {
		startActivity(intent);
	}
	
	/**
	 * 案件点击处理
	 * @param keyCode
	 * @param event
	 * @return
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event){
		return true;
	};
}
