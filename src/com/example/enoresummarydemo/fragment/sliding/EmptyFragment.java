package com.example.enoresummarydemo.fragment.sliding;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enoresummarydemo.R;
import com.example.enoresummarydemo.fragment.base.BaseFragment;

@SuppressLint("ValidFragment") 
public class EmptyFragment extends BaseFragment {
	private String mShowText;
	
	public EmptyFragment(){}
	
	public EmptyFragment(String showText){
		mShowText = showText;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_empty, null);
		TextView textView = (TextView) view.findViewById(R.id.textView);
		if(mShowText!=null && !mShowText.equals(""))
			textView.setText(mShowText);
		return view;
	}

}
