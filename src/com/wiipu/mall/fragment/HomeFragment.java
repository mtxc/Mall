package com.wiipu.mall.fragment;

import java.util.ArrayList;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import com.viewpagerindicator.CirclePageIndicator;
import com.wiipu.mall.R;

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
	private int[] resIds = { R.drawable.test1,
			R.drawable.test2, R.drawable.test3,
			R.drawable.test4, R.drawable.test5 };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		initView(view);
		return view;
	}

	/**
	 * 初始化视图
	 */
	private void initView(View view) {
		viewPager = (AutoScrollViewPager) view
				.findViewById(R.id.home_viewPager);
		viewContainer = getViewImage();
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
	 * 
	 * @return
	 */
	private ArrayList<View> getViewImage() {
		ArrayList<View> views = new ArrayList<View>();
		for (int i = 0; i < resIds.length; i++) {
			ImageView iv = new ImageView(getActivity());
			iv.setScaleType(ScaleType.FIT_XY);
			iv.setImageResource(resIds[i]);
			views.add(iv);
		}
		return views;
	}

}
