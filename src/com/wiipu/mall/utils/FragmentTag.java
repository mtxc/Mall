package com.wiipu.mall.utils;

/**
 * FragmentManager存储Fragment用的tag的枚举
 * tag表示Fragment的完整类名
 */
public enum FragmentTag {
	TAG_HOME("com.wiipu.mall.fragment.HomeFragment"),
	TAG_CATEGORY("com.wiipu.mall.fragment.CategoryFragment"),
	TAG_SCAN("com.wiipu.mall.fragment.ScanFragment"),
	TAG_CART("com.wiipu.mall.fragment.CartFragment"),
	TAG_MY("com.wiipu.mall.fragment.MyFragment");
	
	String tag;
	
	private FragmentTag(String tag){
		this.tag = tag;
	}
	
	public String getTag(){
		return tag;
	}
}
