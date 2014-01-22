package com.example.enoresummarydemo.fragment.base;

import com.example.enoresummarydemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * BaseFragment
 * 
 * @author Encore.liang
 * 
 */
public abstract class BaseFragment extends Fragment {

	/**
	 * 请求超时msg
	 */
	public static final int MESSAGE_TIME_OUT = 0x3588;
	/**
	 * 请求失败
	 */
	public static final int MESSAGE_REQUEST_FAIL = 0X3599;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getHandler();
	}

	/**
	 * 公共startActivify
	 * 
	 * @param intent
	 */
	public void commonStartActivity(Intent intent) {
		startActivity(intent);
	}

	/**
	 * 案件点击处理
	 * 
	 * @param keyCode
	 * @param event
	 * @return
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return true;
	};

	private Handler mBaseHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MESSAGE_REQUEST_FAIL:
				Toast.makeText(getActivity(), (String)msg.obj, Toast.LENGTH_LONG).show();
				break;
			}
		};
	};
	
	/**
	 * 发送消息
	 * @param what
	 * @param object
	 */
	public void sendErrorMessage(int what, Object object){
		Message message = new Message();
		message.what = what;
		message.obj = object;
		mBaseHandler.sendMessage(message);
	}
	/**
	 * 发送消息
	 * @param handler
	 * @param what
	 * @param object
	 */
	public void sendMessage(Handler handler,int what, Object object){
		Message message = new Message();
		message.what = what;
		message.obj = object;
		handler.sendMessage(message);
	}

	/**
	 * 获取handler
	 * 
	 * @return
	 */
	public Handler getHandler() {
		return mBaseHandler;
	}

	/**
	 * 获取默认ImageCache对象
	 * 
	 * @return
	 */
	public DisplayImageOptions madeDefaultImageCache() {
		DisplayImageOptions mOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher).showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true).cacheOnDisc(true).displayer(new FadeInBitmapDisplayer(500)).build();
		return mOptions;
	}
}
