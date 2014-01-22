package com.example.enoresummarydemo.bean;

import java.io.Serializable;
import java.util.List;

public class SingerTypeVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7048052261794393743L;
	
	private int count;
	private List<SingerTypeItem> adList;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<SingerTypeItem> getAdList() {
		return adList;
	}
	public void setAdList(List<SingerTypeItem> adList) {
		this.adList = adList;
	}
}
