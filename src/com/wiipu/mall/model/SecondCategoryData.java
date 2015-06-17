package com.wiipu.mall.model;

/**
 * 二级分类数据
 */
public class SecondCategoryData {

	private int id;
	private String name;
	private String imgUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "SecondCategoryData [id=" + id + ", name=" + name + ", imgUrl="
				+ imgUrl + "]";
	}

}
