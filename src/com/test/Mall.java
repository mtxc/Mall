package com.test;

import java.util.ArrayList;

public class Mall {
private ArrayList<Floor> floors;

public ArrayList<Floor> getFloors() {
	return floors;
}

public void setFloors(ArrayList<Floor> floors) {
	this.floors = floors;
}

@Override
public String toString() {
	return "Mall [floors=" + floors + "]";
}
}
