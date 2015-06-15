package com.wiipu.mall.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wiipu.mall.R;

/**
 * 分类页面右部的fragment
 */
public class CategoryRightFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_category_right,
				container, false);
		return view;
	}

}
