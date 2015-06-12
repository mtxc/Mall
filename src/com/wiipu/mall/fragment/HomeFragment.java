package com.wiipu.mall.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import com.android.volley.toolbox.NetworkImageView;
import com.viewpagerindicator.CirclePageIndicator;
import com.wiipu.mall.R;
import com.wiipu.mall.utils.LogType;
import com.wiipu.mall.utils.LogUtil;
import com.wiipu.mall.utils.NetworkManager;

/**
 * 首页Fragment
 */
public class HomeFragment extends Fragment {

	/**
	 * 广告自动循环切换的时间间隔
	 */
	private static final int AUTO_SCROLL_INTERVAL = 3000;
	private AutoScrollViewPager viewPager;
	private CirclePageIndicator indicator;
	private PagerAdapter adapter;
	private ArrayList<View> viewContainer;
	/**
	 * 广告图片的url数组
	 */
	private ArrayList<String> urls;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		initView(view);
		LogUtil.log(LogType.DEBUG, getClass(), "onCreateView");
		return view;
	}

	/**
	 * 初始化视图
	 */
	private void initView(View view) {
		viewPager = (AutoScrollViewPager) view
				.findViewById(R.id.home_viewPager);
		getViewImage();
		adapter = new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				((AutoScrollViewPager) container).removeView(viewContainer
						.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				((AutoScrollViewPager) container).addView(viewContainer
						.get(position));
				return viewContainer.get(position);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return viewContainer.size();
			}
		};
		viewPager.setAdapter(adapter);
		indicator = (CirclePageIndicator) view.findViewById(R.id.home_indicator);
		indicator.setViewPager(viewPager);
		viewPager.setInterval(AUTO_SCROLL_INTERVAL);
		viewPager.startAutoScroll();
	}

	/**
	 * 获取ViewPager图片列表
	 */
	private void getViewImage() {
		if(viewContainer == null){
			viewContainer = new ArrayList<View>();
		}
		if(urls == null){
			urls = new ArrayList<String>();
		}
		urls = getImageUrls();
		viewContainer.clear();
		for(String url : urls){
			NetworkImageView iv = new NetworkImageView(getActivity());
			iv.setScaleType(ScaleType.FIT_XY);
			NetworkManager.getInstance().setImageUrl(iv, url);
			viewContainer.add(iv);
		}
	}
	
	/**
	 * 获取广告位的图片资源url数组
	 * 每次从服务器获得url数组先做永久性存储，获取时先从本地显示之前的缓存，等获取成功之后再调用notifyDataSetChanged()
	 * @return url数组
	 */
	private ArrayList<String> getImageUrls(){
		ArrayList<String> list = new ArrayList<String>();
		////////////////////////////////////////
		//////////////////假数据/////////////////
		list.add("http://b.hiphotos.baidu.com/image/pic/item/14ce36d3d539b6006bae3d86ea50352ac65cb79a.jpg");
		list.add("http://c.hiphotos.baidu.com/image/pic/item/37d12f2eb9389b503564d2638635e5dde7116e63.jpg");
		list.add("http://g.hiphotos.baidu.com/image/pic/item/cf1b9d16fdfaaf517578b38e8f5494eef01f7a63.jpg");
		list.add("http://f.hiphotos.baidu.com/image/pic/item/77094b36acaf2eddce917bd88e1001e93901939a.jpg");
		list.add("http://g.hiphotos.baidu.com/image/pic/item/f703738da97739124dd7b750fb198618367ae20a.jpg");
		////////////////////////////////////////
		return list;
	}
	
	/**
	 * 通知ViewPager广告位数据源改变
	 */
	public void notifyDataSetChanged(){
		if(adapter != null)
			adapter.notifyDataSetChanged();
		if(indicator != null)
			indicator.notifyDataSetChanged();
	}

}
