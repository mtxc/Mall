package com.wiipu.mall.model;

import java.util.ArrayList;

/**
 * 分类列表数据
 */
public class CategoryListData {

	private ArrayList<CategoryData> categorys;

	public ArrayList<CategoryData> getCategorys() {
		return categorys;
	}

	public void setCategorys(ArrayList<CategoryData> categorys) {
		this.categorys = categorys;
	}

	@Override
	public String toString() {
		return "CategoryListData [categorys=" + categorys + "]";
	}
	
}
