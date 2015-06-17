package com.wiipu.mall.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.wiipu.mall.R;

/**
 * 商品分类Fragment
 */
public class CategoryFragment extends Fragment {
	
	private EditText etSearch;
	private ImageButton ibSearch;

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
	}
	
}
