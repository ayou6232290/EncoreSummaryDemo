package com.example.enoresummarydemo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.bean.SingerTypeItem;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SingerAdapter extends CommonBaseAdapter {

	private Context mContext;

	private List<SingerTypeItem> mSingerItems;

	private LayoutInflater mLayoutInflater;

	public SingerAdapter(Context context) {
		mContext = context;
		mLayoutInflater = LayoutInflater.from(mContext);
		// 初始化imageCache
		initImageCache(R.drawable.icon_singer_default_bg, R.drawable.icon_singer_default_bg, R.drawable.icon_singer_default_bg);
	}

	public void setDatas(List<SingerTypeItem> singerItems) {
		mSingerItems = singerItems;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mSingerItems == null ? 0 : mSingerItems.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return mSingerItems == null ? null : mSingerItems.get(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public View getView(int position, View converView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (converView == null) {
			converView = mLayoutInflater.inflate(R.layout.adapter_list_item, null);
			viewHolder = new ViewHolder(converView);

			converView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) converView.getTag();
		}
		viewHolder.setData(mSingerItems.get(position));
		return converView;
	}

	public class ViewHolder {
		private TextView mTvName;
		private TextView mTvDesc;
		private ImageView mImgLogo;

		public ViewHolder(View view) {
			mTvName = (TextView) view.findViewById(R.id.tvName);
			mTvDesc = (TextView) view.findViewById(R.id.tvDesc);
			mImgLogo = (ImageView) view.findViewById(R.id.imgLogo);
		}

		public void setData(SingerTypeItem singerItem) {
			mTvName.setText(singerItem.getName());
			mTvDesc.setText(singerItem.getHotString());
			if (singerItem.getJointImageUrl() == null || "".equals(singerItem.getJointImageUrl())) {
				singerItem.setJointImageUrl(getSingerImageUrl(String.valueOf(singerItem.getId()), false));
			}
			ImageLoader.getInstance().displayImage(singerItem.getJointImageUrl(), mImgLogo, madeImageCache());
		}
	}

	/**
	 * 拼装歌手小图片url
	 * 
	 * @param singerId
	 */
	public static String getSingerImageUrl(String singerId, boolean isBigImage) {
		String url = "http://mpic.easou.com:82/client/singer/" + singerId + (isBigImage ? ".jpg" : ".png");
		return url;
	}
}
