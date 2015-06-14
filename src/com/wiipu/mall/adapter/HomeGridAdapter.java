package com.wiipu.mall.adapter;

import java.util.List;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.NetworkImageView;
import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;
import com.wiipu.mall.R;
import com.wiipu.mall.model.ProductData;
import com.wiipu.mall.network.NetworkManager;

/**
 * 首页ListView中GridView的适配器
 */
public class HomeGridAdapter extends UniversalAdapter<ProductData> {

	private int screenWidth;

	public HomeGridAdapter(Context context, List<ProductData> datas,
			int itemLayoutId) {
		super(context, datas, itemLayoutId);
	}

	public HomeGridAdapter(Context context, List<ProductData> datas,
			int itemLayoutId, int screenWidth) {
		this(context, datas, itemLayoutId);
		this.screenWidth = screenWidth;
	}

	@Override
	public void updateItem(ViewHolder holder, ProductData data) {
		//动态修改GridView布局的宽高，解决不显示的问题
		LinearLayout layout = (LinearLayout) holder
				.getView(R.id.item_home_gridlayout);
		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
				screenWidth / 2, (int)(screenWidth * 1.5 / 2));
		layout.setLayoutParams(layoutParams);
		
		NetworkImageView iv = (NetworkImageView) holder
				.getView(R.id.item_home_iv);
		NetworkManager.getInstance().setImageUrl(iv, data.getImgUrls().get(0));
	}

}
