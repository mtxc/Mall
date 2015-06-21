package com.wiipu.mall.activity;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import com.android.volley.toolbox.NetworkImageView;
import com.viewpagerindicator.CirclePageIndicator;
import com.wiipu.mall.R;
import com.wiipu.mall.network.NetworkManager;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;

public class ProductDetailsThirdLayerActivity extends Activity {

	/**
	 * 显示商品详情图片的viewpager
	 * */
	private AutoScrollViewPager viewPager;
	private CirclePageIndicator indicator;
	private ArrayList<View> viewContainer;
	private PagerAdapter pagerAdapter;
	private ImageButton btnBack;
	private ImageButton btnCollect;

	/**
	 * 表示该商品是否被收藏
	 * */
	private boolean collected = false;

	/**
	 * 存储图片url地址
	 * */
	private ArrayList<String> urls;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_details_third_layer);
		initView();
	}

	/**
	 * 初始化界面
	 * */
	private void initView() {
		initButtons();
		viewPager = (AutoScrollViewPager) findViewById(R.id.pd_viewPager);
		indicator = (CirclePageIndicator) findViewById(R.id.pd_indicator);
		getViewImage(); // 得到要予以展示的图片
		pagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return viewContainer.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((AutoScrollViewPager) container).removeView(viewContainer
						.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((AutoScrollViewPager) container).addView(viewContainer
						.get(position));
				return viewContainer.get(position);
			}

		};

		viewPager.setAdapter(pagerAdapter);
		indicator.setViewPager(viewPager);
	}

	private void initButtons() {
		btnBack = (ImageButton) findViewById(R.id.pd_btn_back);
		btnCollect = (ImageButton) findViewById(R.id.pd_btn_collect);
		OnClickListener listener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.pd_btn_collect:
					if (!collected) {
						((TransitionDrawable) btnCollect.getDrawable())
								.startTransition(200);
						collected = true;
					} else {
						((TransitionDrawable) btnCollect.getDrawable())
								.reverseTransition(200);
						collected = false;
					}

					break;

				default:
					ProductDetailsThirdLayerActivity.this.finish();
					break;
				}

			}
		};
		btnCollect.setOnClickListener(listener);
		btnBack.setOnClickListener(listener);
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
	 * 获取广告图片列表
	 */
	private void getViewImage() {
		if (viewContainer == null) {
			viewContainer = new ArrayList<View>();
		}
		if (urls == null) {
			urls = new ArrayList<String>();
		}
		urls = getImageUrls();
		viewContainer.clear();
		for (String url : urls) {
			NetworkImageView iv = new NetworkImageView(
					ProductDetailsThirdLayerActivity.this);
			iv.setScaleType(ScaleType.FIT_XY);
			NetworkManager.getInstance().setImageUrl(iv, url);
			viewContainer.add(iv);
		}
	}

	/**
	 * 获取广告位的图片资源url数组
	 * 每次从服务器获得url数组先做永久性存储，获取时先从本地显示之前的缓存，等获取成功之后再调用notifyDataSetChanged()
	 * 
	 * @return url数组
	 */
	private ArrayList<String> getImageUrls() {
		ArrayList<String> list = new ArrayList<String>();
		// //////////////////////////////////////
		// ////////////////假数据/////////////////
		list.add("http://b.hiphotos.baidu.com/image/pic/item/14ce36d3d539b6006bae3d86ea50352ac65cb79a.jpg");
		list.add("http://c.hiphotos.baidu.com/image/pic/item/37d12f2eb9389b503564d2638635e5dde7116e63.jpg");
		list.add("http://g.hiphotos.baidu.com/image/pic/item/cf1b9d16fdfaaf517578b38e8f5494eef01f7a63.jpg");
		list.add("http://f.hiphotos.baidu.com/image/pic/item/77094b36acaf2eddce917bd88e1001e93901939a.jpg");
		list.add("http://g.hiphotos.baidu.com/image/pic/item/f703738da97739124dd7b750fb198618367ae20a.jpg");
		// //////////////////////////////////////
		return list;
	}

}
