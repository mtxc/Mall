package com.wiipu.mall.model;

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
	private String imgUrl;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "ProductData [id=" + id + ", imgUrl=" + imgUrl + ", info="
				+ info + ", price=" + price + "]";
	}

}
