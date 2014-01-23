package com.example.enoresummarydemo.fragment.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.encore.lib.widget.XListView;
import cn.encore.lib.widget.XListView.IXListViewListener;

import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.adapter.CommonBaseAdapter;
import com.example.enoresummarydemo.fragment.base.ProgressFragment;
import com.example.enoresummarydemo.utils.Extra;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class ImageCacheFragment extends ProgressFragment {

	private View mContentView;

	private XListView mListView;

	private DisplayImageOptions mOptions;

	public static ImageCacheFragment newInstance() {
		ImageCacheFragment fragment = new ImageCacheFragment();
		return fragment;
	}

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
		initImageCache();
		// 获取内容
		obtainData();
	}

	/**
	 * 初始化UI
	 */
	public void initUI() {
		mListView = (XListView) mContentView.findViewById(R.id.listView);
		mListView.setPullLoadEnable(true);
		mListView.setPullRefreshEnable(true);
		mListView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true));
		mListView.setXListViewListener(mListViewListener);
	}

	public IXListViewListener mListViewListener = new IXListViewListener() {

		@Override
		public void onRefresh() {
			// TODO Auto-generated method stub
			getHandler().postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
//					ImageLoader.getInstance().clearMemoryCache();
//					ImageLoader.getInstance().clearDiscCache();
//					mListView.setAdapter(new ItemAdapter());
					onLoad();
				}
			}, 2000);
		}

		@Override
		public void onLoadMore() {
			// TODO Auto-generated method stub
			getHandler().postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					mListView.setPullLoadEnable(false);
					onLoad();
				}
			}, 2000);
		}
	};
	
	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	/**
	 * 初始化imageCache
	 */
	public void initImageCache() {
		mOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_launcher)
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher)
		.cacheInMemory(true).cacheOnDisc(true)
		.displayer(new FadeInBitmapDisplayer(500))
		.build();
	}

	public void obtainData() {
		// 设置当前为loading状态
		setContentShown(false);
		getHandler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				mListView.setAdapter(new ItemAdapter());
				setContentShown(true);
//				setContentError(true);
//				setErrorButtonClick(mClickListener);
			}
		}, 1000);
	}
	
	public OnClickListener mClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(getActivity(), "error click!", 0).show();
		}
	};

	class ItemAdapter extends CommonBaseAdapter {

		private class ViewHolder {
			public TextView text;
			public ImageView image;
		}

		@Override
		public int getCount() {
			return Extra.IMAGES.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = convertView;
			final ViewHolder holder;
			if (convertView == null) {
				view = getActivity().getLayoutInflater().inflate(R.layout.item_list_image, parent, false);
				holder = new ViewHolder();
				holder.text = (TextView) view.findViewById(R.id.text);
				holder.image = (ImageView) view.findViewById(R.id.image);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			holder.text.setText("Item " + (position + 1));

			ImageLoader.getInstance().displayImage(Extra.IMAGES[position], holder.image, mOptions);
			return view;
		}
	}

}
