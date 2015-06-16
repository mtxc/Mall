package com.wiipu.mall.shoppingcart;

public class AddressBean {

	private String name;			//收货人姓名
	private String smearedAddress;	//区地址
	private String detailAddress;	//详细地址
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSmearedAddress() {
		return smearedAddress;
	}
	public void setSmearedAddress(String smearedAddress) {
		this.smearedAddress = smearedAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
}
