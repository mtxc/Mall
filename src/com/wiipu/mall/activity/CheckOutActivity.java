package com.wiipu.mall.activity;

import java.util.ArrayList;
import java.util.List;

import com.wiipu.mall.R;
import com.wiipu.mall.cartlayout.NoScrollListView;
import com.wiipu.mall.shoppingcart.AddressBean;
import com.wiipu.mall.shoppingcart.CheckOutAdapter;
import com.wiipu.mall.shoppingcart.ShopBean;
import com.wiipu.mall.shoppingcart.ShoppingCanst;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CheckOutActivity extends Activity {
	
	private Button sureCheckOut;		//确认购买
	private TextView addresseeName;		//收货人姓名
	private TextView smearedAddress;	//收货人区地址
	private TextView detailAddress;		//收货人详细地址
	private TextView checkOutAllPrice;	//结算的总金额
	private TextView title_left;		//title左标题,返回
	private TextView title_center;		//title中间标题
	private RelativeLayout addressRelative;	  //显示收货人信息的布局
	private NoScrollListView checkOutListView;//商品列表
	
	private CheckOutAdapter adapter;
	private List<ShopBean> list;			  //结算商品数据集合
	private List<AddressBean> addressList;	  //收货人地址数据集合
	
	private static int REQUESTCODE = 1;		  //跳转请求码
	private float allPrice = 0;				  //购买商品需要的总金额
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart_activity_checkout);
		initView();
		init();
	}
	
	//初始化数据
	private void init(){
		list = new ArrayList<ShopBean>();
		String shopIndex = getIntent().getStringExtra("shopIndex");
		String[] shopIndexs = shopIndex.split(",");
		for(String s : shopIndexs){
			int position = Integer.valueOf(s);
			ShopBean bean = ShoppingCanst.list.get(position);
			allPrice += bean.getShopNumber()*bean.getShopPrice();
			list.add(bean);
		}
		getAddressData();
		addressList = ShoppingCanst.addressList;
		checkOutAllPrice.setText("总共有"+list.size()+"个商品       总价￥"+allPrice);
		showInfo(0);	//默认显示第一条地址信息
		
		adapter = new CheckOutAdapter(this, list, R.layout.cart_checkout_item);
		checkOutListView.setAdapter(adapter);
	}
	
	//初始化UI界面
	private void initView(){
		sureCheckOut = (Button) findViewById(R.id.sureCheckOut);
		addresseeName = (TextView) findViewById(R.id.addresseeName);
		smearedAddress = (TextView) findViewById(R.id.smearedAddress);
		detailAddress = (TextView) findViewById(R.id.detailAddress);
		checkOutAllPrice = (TextView) findViewById(R.id.checkOutAllPrice);
		title_left = (TextView) findViewById(R.id.title_left);
		title_center = (TextView) findViewById(R.id.title_center);
		checkOutListView = (NoScrollListView) findViewById(R.id.checkOutListView);
		addressRelative = (RelativeLayout) findViewById(R.id.addressRelative);
		
		ClickListener cl = new ClickListener();
		title_left.setText(R.string.sureOrder);
		title_center.setText(R.string.checkOut);
		title_left.setOnClickListener(cl);
		sureCheckOut.setOnClickListener(cl);
		addressRelative.setOnClickListener(cl);
	}
	
	//显示收货人姓名地址等信息
	private void showInfo(int index){
		AddressBean bean = addressList.get(index);
		addresseeName.setText(bean.getName());
		smearedAddress.setText(bean.getSmearedAddress());
		detailAddress.setText(bean.getDetailAddress());
	}
	
	//获取收货人地址数据集合
	private void getAddressData(){
		ShoppingCanst.addressList = new ArrayList<AddressBean>();
		AddressBean bean = new AddressBean();
		bean.setName("张骏");
		bean.setSmearedAddress("湖北省武汉市武昌区");
		bean.setDetailAddress("东湖风景区黄家大湾特1号  15527196048");
		ShoppingCanst.addressList.add(bean);
		AddressBean bean2 = new AddressBean();
		bean2.setName("吴威");
		bean2.setSmearedAddress("湖北省武汉市武昌区");
		bean2.setDetailAddress("黄家大湾特1号东湖风景区  18140549110");
		ShoppingCanst.addressList.add(bean2);
	}
	
	//修改地址
	private void updateAddress(){
		Intent intent = new Intent(CheckOutActivity.this,UpdateAddressActivity.class);
		startActivityForResult(intent, REQUESTCODE);
	}
	
	//事件点击监听器
	private final class ClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			int rid = v.getId();
			if(rid == R.id.sureCheckOut){	//确认点击按钮
				Toast.makeText(getApplicationContext(), "结算完成，总共花费￥"+allPrice, Toast.LENGTH_LONG).show();
			}else if(rid == R.id.addressRelative){	//修改地址
				updateAddress();
			}else if(rid == R.id.title_left){		//左标题返回
				finish();
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUESTCODE && resultCode == RESULT_OK){
			Bundle bundle = data.getExtras();
			handler.sendMessage(handler.obtainMessage(1, bundle.getInt("addressIndex")));
		}
	}
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 1){		//异步更改地址	
				int tempIndex = (Integer)msg.obj;
				showInfo(tempIndex);
			}
		}
	};
}
