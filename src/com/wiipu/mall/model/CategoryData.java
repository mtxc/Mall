package com.wiipu.mall.model;

/**
 * 每种分类数据
 */
public class CategoryData {
	
	private int id;
	
	private String name;

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

	@Override
	public String toString() {
		return "CategoryData [id=" + id + ", name=" + name + "]";
	}

}
