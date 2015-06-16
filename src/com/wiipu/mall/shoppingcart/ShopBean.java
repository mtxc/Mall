package com.wiipu.mall.shoppingcart;

public class ShopBean {
	private int shopId;				//商品ID
	private int shopPicture;		//商品图片资源ID
	private String storeName;		//店家名称
	private String shopName;		//商品名称
	private String shopDescription;	//商品描述
	private float shopPrice;		//商品单价
	private int shopNumber;			//商品数量
	private boolean isChoosed;		//商品是否在购物车中被选中
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getShopPicture() {
		return shopPicture;
	}
	public void setShopPicture(int shopPicture) {
		this.shopPicture = shopPicture;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDescription() {
		return shopDescription;
	}
	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}
	public float getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(float shopPrice) {
		this.shopPrice = shopPrice;
	}
	public int getShopNumber() {
		return shopNumber;
	}
	public void setShopNumber(int shopNumber) {
		this.shopNumber = shopNumber;
	}
	public boolean isChoosed() {
		return isChoosed;
	}
	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}
}
