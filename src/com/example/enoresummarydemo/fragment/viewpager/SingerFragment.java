package com.example.enoresummarydemo.fragment.viewpager;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.encore.lib.http.OnRequestCallback;
import cn.encore.lib.widget.XListView;

import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.api.HttpApi;
import com.example.enoresummarydemo.bean.SingerTypeItem;
import com.example.enoresummarydemo.bean.SingerTypeVO;
import com.example.enoresummarydemo.fragment.base.ProgressFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;

public class SingerFragment extends ProgressFragment {
	/**
	 * 刷新数据
	 */
	public static final int TAG_REFRESH_DATA = 0;
	// fragment 主体内容view
	private View mContentView;
	// listView
	private XListView mListView;

	private DisplayImageOptions mOptions;
	
	private List<SingerTypeItem> mSingerTypeItems;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mContentView = inflater.inflate(R.layout.common_listview, null);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Setup content view
		setContentView(mContentView);
		// 初始化UI
		initUI();
		// 初始化imageCache
		mOptions = madeDefaultImageCache();
		// 获取内容
		obtainData();
	}

	/**
	 * 初始化UI
	 */
	public void initUI() {
		mListView = (XListView) mContentView.findViewById(R.id.listView);
		mListView.setPullLoadEnable(false);
		mListView.setPullRefreshEnable(false);
		mListView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true));
		/**
		 * 错误点击处理
		 */
		setErrorButtonClick(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				obtainData();
			}
		});
	}

	/**
	 * 
	 */
	public void obtainData() {
		
		// 设置当前为loading状态
		setContentShown(false);

		HttpApi.madeSingerList(getActivity(), new OnRequestCallback() {

			@Override
			public void onSuccess(Object result) {
				// TODO Auto-generated method stub
				SingerTypeVO singerTypeVO = (SingerTypeVO) result;
				// 刷新数据
				sendMessage(mHandler, TAG_REFRESH_DATA, singerTypeVO.getAdList());
			}

			@Override
			public void onFail(String msg, int state) {
				// TODO Auto-generated method stub
				sendErrorMessage(MESSAGE_REQUEST_FAIL, msg);
				//
				setContentError(true, msg);
			}
		});
	}

	/**
	 * 刷新数据
	 * 
	 * @param hotSaleItems
	 */
	public void refreshtDatas(List<SingerTypeItem> hotSaleItems) {
		if (hotSaleItems != null && hotSaleItems.size() != 0) {
			setContentShown(true);
		} else {
			diposeFail("没有数据");
		}
	}

	/**
	 * 处理失败
	 * 
	 * @param msg
	 */
	public void diposeFail(final String msg) {
		setContentError(true, msg);
	}

	public Handler mHandler = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			if (!isAdded()) {
				return;
			}
			switch (msg.what) {
			case TAG_REFRESH_DATA:
				refreshtDatas(msg.obj != null ? (List<SingerTypeItem>) msg.obj : null);
				break;
			}
		};
	};
}
