package com.wiipu.mall.model;

import java.util.ArrayList;

/**
 * 商品数据
 */
public class ProductData {

	/**
	 * 商品id
	 */
	private int id;
	/**
	 * 商品图片url数组
	 */
	private ArrayList<String> imgUrls;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<String> getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(ArrayList<String> imgUrls) {
		this.imgUrls = imgUrls;
	}

	@Override
	public String toString() {
		return "ProductData [id=" + id + ", imgUrls=" + imgUrls + "]";
	}

}
