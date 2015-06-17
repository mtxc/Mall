package com.wiipu.mall.adapter;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup.LayoutParams;
import com.android.volley.toolbox.NetworkImageView;
import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;
import com.wiipu.mall.R;
import com.wiipu.mall.model.SecondCategoryData;
import com.wiipu.mall.network.NetworkManager;

public class CategoryRightGridAdapter extends UniversalAdapter<SecondCategoryData>{
	
	public CategoryRightGridAdapter(Context context,
			List<SecondCategoryData> datas, int itemLayoutId) {
		super(context, datas, itemLayoutId);
	}

	@Override
	public void updateItem(ViewHolder holder, SecondCategoryData data) {
		NetworkImageView iv = holder.getView(R.id.item_category_right_iv);
		NetworkManager.getInstance().setImageUrl(iv, data.getImgUrl());
		holder.setTextViewText(R.id.item_category_right_tv, data.getName());
		// 设置图片宽高相等
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		LayoutParams params = iv.getLayoutParams();
		params.width = metrics.widthPixels/4;
		params.height = metrics.widthPixels/4;
		iv.setLayoutParams(params);
	}

}
