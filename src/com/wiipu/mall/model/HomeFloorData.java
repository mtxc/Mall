package com.wiipu.mall.model;

import java.util.ArrayList;

/**
 * 首页楼层商品数据
 */
public class HomeFloorData {

	/**
	 * 楼层名称
	 */
	private String floor;
	/**
	 * 该楼层所有商品
	 */
	private ArrayList<ProductData> products;

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public ArrayList<ProductData> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<ProductData> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "HomeFloorData [floor=" + floor + ", products=" + products + "]";
	}

}
