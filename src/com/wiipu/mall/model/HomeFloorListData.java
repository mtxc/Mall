package com.wiipu.mall.model;

import java.util.ArrayList;

/**
 * 首页楼层列表数据
 */
public class HomeFloorListData {

	private ArrayList<HomeFloorData> floors;

	public ArrayList<HomeFloorData> getFloors() {
		return floors;
	}

	public void setFloors(ArrayList<HomeFloorData> floors) {
		this.floors = floors;
	}

	@Override
	public String toString() {
		return "HomeFloorListData [floors=" + floors + "]";
	}
}
