package com.wiipu.mall.fragment;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.wiipu.mall.R;
import com.wiipu.mall.adapter.CategoryLeftListAdapter;
import com.wiipu.mall.model.CategoryData;

/**
 * 分类页面左部的fragment
 */
public class CategoryLeftFragment extends ListFragment {
	
	private ArrayList<CategoryData> categorys;
	private CategoryLeftListAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_category_left, container, true);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		categorys = new ArrayList<CategoryData>();
		adapter = new CategoryLeftListAdapter(getActivity(), categorys, R.layout.item_category_left_lv);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				view.setSelected(true);
			}
		});
		getListView().setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				refresh(categorys.get(position).getId());
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		getCategoryData();
	}
	
	/**
	 * 从服务器获取一级分类数据
	 */
	private void getCategoryData(){
		ArrayList<CategoryData> list = new ArrayList<CategoryData>();
		////////////////////////////////////////
		////////////////假数据///////////////////
		for(int i=0; i<10; i++){
			CategoryData data = new CategoryData();
			data.setId(i);
			data.setName("分类" + i);
			list.add(data);
		}
		////////////////////////////////////////
		// 默认二级分类的一级分类id为一级分类列表的第一项
		categorys.clear();
		categorys.addAll(list);
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * 刷新二级分类
	 * @param id 一级分类的id
	 */
	private void refresh(int id){
		CategoryRightFragment fragment = (CategoryRightFragment) getActivity().getFragmentManager().findFragmentById(R.id.category_right_fragment);
		fragment.refreshSecondCategorys(id);
	}
	
}
