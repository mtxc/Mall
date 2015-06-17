package com.wiipu.mall.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.wiipu.mall.R;
import com.wiipu.mall.adapter.CategoryLeftListAdapter;
import com.wiipu.mall.adapter.CategoryRightGridAdapter;
import com.wiipu.mall.model.CategoryData;
import com.wiipu.mall.model.SecondCategoryData;

/**
 * 商品分类Fragment
 */
public class CategoryFragment extends Fragment {
	
	/**
	 * 顶部搜索相关
	 */
	private EditText etSearch;
	private ImageButton ibSearch;
	/**
	 * 左边列表相关
	 */
	private ListView listView;
	private ArrayList<CategoryData> listCategorys;
	private CategoryLeftListAdapter listAdapter;
	private int selectedPosition;
	/**
	 * 右边网格有关
	 */
	private GridView gridView;
	private ArrayList<SecondCategoryData> gridCategorys;
	private CategoryRightGridAdapter gridAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_category, container, false);
		initView(view);
		return view;
	}
	
	/**
	 * 初始化视图
	 * @param view 父视图
	 */
	private void initView(View view){
		// 搜索相关
		etSearch = (EditText) view.findViewById(R.id.top_et_search);
		ibSearch = (ImageButton) view.findViewById(R.id.top_ib_search);
		ibSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//////////////////////////////////////
				///////////向服务器发送搜索请求///////////
				etSearch.getText().toString().trim();
				//////////////////////////////////////
			}
		});
		// 列表相关
		listView = (ListView) view.findViewById(R.id.category_listView);
		listCategorys = new ArrayList<CategoryData>();
		listAdapter = new CategoryLeftListAdapter(getActivity(), listCategorys, R.layout.item_category_left_lv);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				view.setSelected(true);
				if(selectedPosition != position){
					selectedPosition = position;
					refreshSecondCategorys(((CategoryData)parent.getItemAtPosition(position)).getId());
				}
			}
		});
		getCategoryData();
		// 网格相关
		gridView = (GridView) view.findViewById(R.id.category_gridView);
		gridCategorys = new ArrayList<SecondCategoryData>();
		gridAdapter = new CategoryRightGridAdapter(getActivity(), gridCategorys, R.layout.item_category_right_grid);
		gridView.setAdapter(gridAdapter);
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
		listCategorys.clear();
		listCategorys.addAll(list);
		listAdapter.notifyDataSetChanged();
		// 默认二级分类的一级分类id为一级分类列表的第一项
		listView.post(new Runnable() {
			
			@Override
			public void run() {
				if(listView.getChildAt(0)!=null){
					selectedPosition = 0;
					listView.getChildAt(0).setSelected(true);
					refreshSecondCategorys(listCategorys.get(0).getId());
				}
			}
		});
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
		gridCategorys.clear();
		gridCategorys.addAll(list);
		gridAdapter.notifyDataSetChanged();
	}
}
