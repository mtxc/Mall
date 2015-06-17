package com.wiipu.mall.model;

import java.util.ArrayList;

/**
 * 二级分类列表数据
 */
public class SecondCategoryListData {

	private ArrayList<SecondCategoryData> secondCategorys;

	public ArrayList<SecondCategoryData> getSecondCategorys() {
		return secondCategorys;
	}

	public void setSecondCategorys(ArrayList<SecondCategoryData> secondCategorys) {
		this.secondCategorys = secondCategorys;
	}

	@Override
	public String toString() {
		return "SecondCategoryListData [secondCategorys=" + secondCategorys
				+ "]";
	}
	
}
