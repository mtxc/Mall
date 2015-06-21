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
	 * 商品图片url
	 */
	private String imgUrl;
	private String info;
	private float price;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
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
