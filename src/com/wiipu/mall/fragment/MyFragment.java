package com.wiipu.mall.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wiipu.mall.R;
import com.wiipu.mall.activity.LoginActivity;

/**
 * 我的信息Fragment
 */
public class MyFragment extends Fragment {
	
	private static final int REQUEST_CODE = 15342;
	
	private ImageButton ibLogin;
	private TextView tvUsername;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my, container, false);
		initView(view);
		return view;
	}
	
	/**
	 * 初始化视图
	 * @param view 父视图
	 */
	private void initView(View view){
		ibLogin = (ImageButton) view.findViewById(R.id.my_ib_login);
		tvUsername = (TextView) view.findViewById(R.id.my_tv_username);
		ibLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK){
			if(requestCode == REQUEST_CODE){
				// 登录成功，取出登录数据
			}
		}
	}
	
}
