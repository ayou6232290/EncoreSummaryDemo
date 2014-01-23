package com.example.enoresummarydemo.adapter;

import android.widget.BaseAdapter;

import com.example.enoresummarydemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public abstract class CommonBaseAdapter extends BaseAdapter {
	private DisplayImageOptions mDisplayImageOptions;
	/**
	 * 初始化imageChache
	 * @param loadingRes
	 * @param failRes
	 * @param emptyRes
	 */
	public void initImageCache(int loadingRes,int failRes,int emptyRes)
	{
		if(mDisplayImageOptions == null){
			mDisplayImageOptions = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_launcher)
			.showImageForEmptyUri(R.drawable.ic_launcher)
			.showImageOnFail(R.drawable.ic_launcher)
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.displayer(new FadeInBitmapDisplayer(500)).build();
		}
	}
	/**
	 * 获取imageChache
	 * @return
	 */
	public DisplayImageOptions madeImageCache()
	{
		if(mDisplayImageOptions == null){
			initImageCache(R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher);
		}
		return mDisplayImageOptions;
	}
}
