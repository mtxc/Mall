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
	private String info;
	private int price;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

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
		return "ProductData [id=" + id + ", imgUrls=" + imgUrls + ", info="
				+ info + ", price=" + price + "]";
	}

}
