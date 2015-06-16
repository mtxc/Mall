package com.test;

import java.util.ArrayList;

public class Floor {

	private String name;
	private ArrayList<Product> products;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Floor [name=" + name + ", products=" + products + "]";
	}
	
}
