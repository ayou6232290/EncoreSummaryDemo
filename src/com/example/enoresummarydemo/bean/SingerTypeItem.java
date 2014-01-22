package com.example.enoresummarydemo.bean;

import java.io.Serializable;
import java.util.List;

public class SingerTypeItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1219731810087570260L;
	
	private int id;
	private String imgUrl;
	private String intor;
	private String name;
	private int status;
	private int theOrder;
	private int type;
	
	public List<String> hot;
	
	/**
	 * 拼接imageUrl
	 */
	public String jointImageUrl;
	
	public String getJointImageUrl() {
		return jointImageUrl;
	}

	public void setJointImageUrl(String jointImageUrl) {
		this.jointImageUrl = jointImageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getIntor() {
		return intor;
	}

	public void setIntor(String intor) {
		this.intor = intor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTheOrder() {
		return theOrder;
	}

	public void setTheOrder(int theOrder) {
		this.theOrder = theOrder;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<String> getHot() {
		return hot;
	}

	public void setHot(List<String> hot) {
		this.hot = hot;
	}

	public SingerTypeItem(int id, String imgUrl, String intor, String name, int status, int theOrder, int type, List<String> hot) {
		super();
		this.id = id;
		this.imgUrl = imgUrl;
		this.intor = intor;
		this.name = name;
		this.status = status;
		this.theOrder = theOrder;
		this.type = type;
		this.hot = hot;
	}

	public SingerTypeItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getHotString() {
		if (hot == null || hot.size() == 0) {
			return "";
		}
		String hs = "";
		for (int i = 0; i < hot.size(); i++) {
			hs += hot.get(i);
			if (i != hot.size() - 1) {
				hs += "、";
			}
		}
		return hs;
	}
}
