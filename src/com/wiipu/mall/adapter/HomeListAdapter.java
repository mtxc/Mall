package com.wiipu.mall.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;
import com.wiipu.mall.R;
import com.wiipu.mall.model.HomeFloorData;
import com.wiipu.mall.model.ProductData;
import com.wiipu.mall.utils.LogType;
import com.wiipu.mall.utils.LogUtil;
import com.wiipu.mall.view.MyGridView;

/**
 * 首页ListView的适配器
 */
public class HomeListAdapter extends UniversalAdapter<HomeFloorData> {

	private int screenWidth;

	public HomeListAdapter(Context context, List<HomeFloorData> datas,
			int itemLayoutId) {
		super(context, datas, itemLayoutId);
	}

	public HomeListAdapter(Context context, List<HomeFloorData> datas,
			int itemLayoutId, int screenWidth) {
		this(context, datas, itemLayoutId);
		this.screenWidth = screenWidth;
	}

	@Override
	public void updateItem(ViewHolder holder, HomeFloorData data) {
		((TextView) holder.getView(R.id.item_home_tv)).setText(data.getFloor());
		if (data.getProducts() != null) {
			MyGridView gridView = (MyGridView) holder
					.getView(R.id.item_home_grid);
			HomeGridAdapter adapter = new HomeGridAdapter(context,
					data.getProducts(), R.layout.item_home_grid, screenWidth);
			gridView.setAdapter(adapter);
			// 设置商品的点击事件
			gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					ProductData product = (ProductData) parent.getItemAtPosition(position);
					LogUtil.log(LogType.DEBUG, getClass(), product.getId()+"");
				}
			});
		}
	}

}