package com.wiipu.mall.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.wiipu.mall.R;
import com.wiipu.mall.adapter.CategoryRightGridAdapter;
import com.wiipu.mall.model.SecondCategoryData;

/**
 * 分类页面右部的fragment
 */
public class CategoryRightFragment extends Fragment {

	private GridView gridView;
	private ArrayList<SecondCategoryData> categorys;
	private CategoryRightGridAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_category_right,
				container, true);
		initView(view);
		return view;
	}

	/**
	 * 初始化视图
	 * @param view 父视图
	 */
	private void initView(View view){
		gridView = (GridView) view.findViewById(R.id.category_right_grid);
		categorys = new ArrayList<SecondCategoryData>();
		adapter = new CategoryRightGridAdapter(getActivity(), categorys, R.layout.item_category_right_grid);
		gridView.setAdapter(adapter);
	}
	
	/**
	 * 从服务器获取二级分类列表数据
	 * @param categoryId 上级分类列表的id
	 */
	public void refreshSecondCategorys(int categoryId){
		ArrayList<SecondCategoryData> list = new ArrayList<SecondCategoryData>();
		//////////////////////////////////////
		////////////////假数据/////////////////
		for(int i=0; i<10; i++){
			SecondCategoryData data = new SecondCategoryData();
			data.setId(i);
			data.setName("分类"+i);
			data.setImgUrl("http://b.hiphotos.baidu.com/image/pic/item/14ce36d3d539b6006bae3d86ea50352ac65cb79a.jpg");
			list.add(data);
		}
		//////////////////////////////////////
		categorys.clear();
		categorys.addAll(list);
		adapter.notifyDataSetChanged();
	}
	
}
