package com.wiipu.mall.activity;

import com.wiipu.mall.R;
import com.wiipu.mall.shoppingcart.ShoppingCanst;
import com.wiipu.mall.shoppingcart.UpdateAddressAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class UpdateAddressActivity extends Activity {
	
	private TextView title_left;			//左标题
	private TextView title_center;			//中间标题
	private ListView updateAddressListView;	//合计数据显示列表
	private UpdateAddressAdapter adapter;	//自定义适配器
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart_activity_updateaddress);
		initView();
		init();
	}
	
	//初始化UI视图
	private void initView(){
		title_left = (TextView) findViewById(R.id.title_left);
		title_center = (TextView) findViewById(R.id.title_center);
		updateAddressListView = (ListView) findViewById(R.id.updateAddressListView);
		
		title_left.setText(R.string.checkOut);
		title_center.setText(R.string.updateAddress);
		title_left.setOnClickListener(new textViewClickListener());
		updateAddressListView.setOnItemClickListener(new ListViewItemClickListener());
	}
	
	//初始化数据
	private void init(){
		if(ShoppingCanst.addressList != null){
			adapter = new UpdateAddressAdapter(this,ShoppingCanst.addressList
					,R.layout.cart_updateaddress_item);
			updateAddressListView.setAdapter(adapter);
		}
	}
	
	//textView事件点击监听器
	private final class textViewClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.title_left){
				//如果是左标题
				finish();
			}
		}
	}
	
	//ListView条目点击监听器
	private final class ListViewItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long arg3) {
			Intent intent = new Intent();
			intent.putExtra("addressIndex", position);
			setResult(RESULT_OK,intent);
			finish();
		}
	}
}
