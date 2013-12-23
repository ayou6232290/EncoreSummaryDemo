package com.example.enoresummarydemo.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.enoresummarydemo.R;

public class Header extends RelativeLayout {
	TextView mTitle;
	TextView mLeft;
	TextView mRight;

	View bar;

	public Header(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialise(context);
	}

	public Header(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialise(context);
	}

	public Header(Context context) {
		super(context);
		initialise(context);
	}

	private void initialise(final Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.header, null);
		bar = view;
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		view.setLayoutParams(layoutParams);

		mTitle = (TextView) view.findViewById(R.id.title);
		mLeft = (TextView) view.findViewById(R.id.left);
		mRight = (TextView) view.findViewById(R.id.right);
		this.addView(view);

		// 默认设置跳转到播放器
//		setRightBtn(R.drawable.btn_music_selector, new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// to play
////				Intent intent = new Intent(context, PlayActivity.class);
////				intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
////				context.startActivity(intent);
//			}
//		});
//		
//		setLeftBtn(R.drawable.btn_menu_selector, new )
		
	}

	/**
	 * 设置titlebar的背景
	 * 
	 * @param res
	 */
	public void setTitleBarBackGround(int res) {
		bar.setBackgroundResource(res);
	}
	
	public int[] getViewXY()
	{
		return null;
	}

	/**
	 * 设置文字颜色
	 * 
	 * @param colorStr
	 */
	public void setTitleColor(String colorStr) {
		mTitle.setTextColor(Color.parseColor(colorStr));
	}

	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
		mTitle.setVisibility(View.VISIBLE);
		mTitle.setText(title);
	}

	/**
	 * 设置标题
	 */
	public void setTitle(int title) {
		mTitle.setVisibility(View.VISIBLE);
		mTitle.setText(title);
	}
	
//	public void setTitle(int drawableId){
//		if(drawableId == -1){
//			return;
//		}
//		mTitle.setVisibility(View.VISIBLE);
//		mTitle.setBackgroundResource(drawableId);
//	}

	public View getTitleView() {
		return mTitle;
	}

	/**
	 * 设置左边按钮
	 * 
	 * @param draw
	 * @param onClickListener
	 */
	public void setLeftBtn(int draw, View.OnClickListener onClickListener) {
		mLeft.setVisibility(View.VISIBLE);
		mLeft.setBackgroundResource(draw);
		mLeft.setOnClickListener(onClickListener);
		// 左边有按钮刚不显示logo
		// hideLogo();
	}

	/**
	 * 设置左边按钮
	 * 
	 * @param draw
	 * @param onClickListener
	 */
	public void setLeftBtn(int draw, String text, View.OnClickListener onClickListener) {
		mLeft.setVisibility(View.VISIBLE);
		mLeft.setBackgroundResource(draw);
		mLeft.setText(text);
		mLeft.setOnClickListener(onClickListener);
		// 左边有按钮刚不显示logo
		// hideLogo();
	}

	public View getLeftBtn() {
		return mLeft;
	}

	/**
	 * 设置右边按钮
	 * 
	 * @param draw
	 * @param onClickListener
	 */
	public void setRightBtn(int draw, View.OnClickListener onClickListener) {
		mRight.setVisibility(View.VISIBLE);
		mRight.setBackgroundResource(draw);
		mRight.setOnClickListener(onClickListener);
	}

	/**
	 * 设置右边按钮
	 * 
	 * @param draw
	 * @param onClickListener
	 */
	public void setRightBtn(int draw, String text, View.OnClickListener onClickListener) {
		mRight.setVisibility(View.VISIBLE);
		mRight.setBackgroundResource(draw);
		mRight.setText(text);
		mRight.setOnClickListener(onClickListener);
	}

	public View getRightBtn() {
		return mRight;
	}

	public void removeRightBtn() {
		mRight.setVisibility(View.INVISIBLE);
	}

	public void removeLeftBtn() {
		mLeft.setVisibility(View.INVISIBLE);
	}
}
