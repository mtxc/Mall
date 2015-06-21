package com.wiipu.mall.activity;

import java.util.ArrayList;

import com.wiipu.mall.R;
import com.wiipu.mall.adapter.PdSecondLayerAdapter;
import com.wiipu.mall.model.ProductData;
import com.wiipu.mall.noscrollview.NoScrollListView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class ProductDetailsSecondLayerActivity extends Activity {
	/**
	 * 显示商品信息的listview
	 * */
	private NoScrollListView listView;
	/**
	 * 存储url的容器
	 * */
	private ArrayList<String> urls = new ArrayList<String>();

	/**
	 * listview的数据
	 * */
	private ArrayList<ProductData> list_datas = new ArrayList<ProductData>();
	/**
	 * 二级分类的适配器
	 * */
	private PdSecondLayerAdapter adapter;

	/**
	 * buttons
	 * */
	private ImageButton btn_back;
	private Button btn_total; // 综合
	private Button btn_sales; // 销量
	private Button btn_price; // 价格
	private Button btn_select; // 筛选

	/**
	 * 控制价格图标上下箭头的标志位
	 * */
	private boolean flag_price_transition = false;
	private Drawable[] layers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productdetails_second_layer);
		initView();
		initButtons();
		initAdapter();
	}

	/**
	 * 初始化布局
	 * */
	private void initView() {
		listView = (NoScrollListView) findViewById(R.id.lv_pd_second_layer);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(
						ProductDetailsSecondLayerActivity.this,
						ProductDetailsThirdLayerActivity.class));
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	/**
	 * 得到url
	 * */
	private String getUrls() {
		String urlString = "http://i2.sinaimg.cn/IT/h/2009-12-05/1259942752_UQ03Yv.jpg";
		return urlString;
	}

	/**
	 * 得到listview数据
	 * */
	private void getListData() {
		for (int i = 0; i < 10; i++) {
			ProductData data = new ProductData();
			data.setId(i);
			data.setImgUrl(getUrls());
			data.setInfo("樱桃（Cherry） G80-3060HLCUS-2 红轴黑橙二色键帽 60周年限量版机械键盘");
			data.setPrice(1953);
			list_datas.add(data);
		}
	}

	/**
	 * 初始化 适配器
	 * */
	private void initAdapter() {
		getListData();
		adapter = new PdSecondLayerAdapter(this, list_datas,
				R.layout.item_pd_second_layer);
		listView.setAdapter(adapter);
	}

	/**
	 * 初始化buttons
	 * */
	private void initButtons() {
		btn_back = (ImageButton) findViewById(R.id.btn_pd_second_back);
		btn_back.setFocusable(true);
		btn_back.setFocusableInTouchMode(true);
		btn_back.requestFocus();
		btn_back.requestFocusFromTouch();

		btn_price = (Button) findViewById(R.id.btn_pd2_price);
		btn_sales = (Button) findViewById(R.id.btn_pd2_sales);
		btn_select = (Button) findViewById(R.id.btn_pd2_select);
		btn_total = (Button) findViewById(R.id.btn_pd2_total);

		// 首次进入
		btn_sales.setTextColor(Color.RED);
		layers = new Drawable[3];
		layers[0] = getResources().getDrawable(
				R.drawable.pd_button_price_normal);
		layers[1] = getResources().getDrawable(R.drawable.pd_button_price_up);
		layers[2] = getResources().getDrawable(R.drawable.pd_button_price_down);
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btn_pd2_price: {
					if (!flag_price_transition) {
						btn_price.setCompoundDrawablesWithIntrinsicBounds(null,
								null, layers[1], null);
						flag_price_transition = true;
					} else {
						btn_price.setCompoundDrawablesWithIntrinsicBounds(null,
								null, layers[2], null);
						flag_price_transition = false;
					}
					btn_sales.setTextColor(Color.BLACK);
					btn_price.setTextColor(Color.RED);
					break;
				}
				case R.id.btn_pd2_sales: {
					btn_price.setCompoundDrawablesWithIntrinsicBounds(null,
							null, layers[0], null);
					flag_price_transition = false;
					btn_price.setTextColor(Color.BLACK);
					btn_sales.setTextColor(Color.RED);
					break;
				}
				case R.id.btn_pd_second_back:
					finish();
					break;
				default:
					break;
				}
			}
		};

		btn_back.setOnClickListener(listener);
		btn_price.setOnClickListener(listener);
		btn_sales.setOnClickListener(listener);
		btn_total.setOnClickListener(listener);
		btn_select.setOnClickListener(listener);
	}
}
