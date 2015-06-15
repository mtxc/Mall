package com.wiipu.mall.fragment;

import com.wiipu.mall.R;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 分类页面左部的fragment
 */
public class CategoryLeftFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_category_left, container, false);
		return view;
	}
	
}
