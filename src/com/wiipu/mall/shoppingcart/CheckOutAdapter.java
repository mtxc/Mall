package com.wiipu.mall.shoppingcart;

import java.util.List;

import com.wiipu.mall.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class CheckOutAdapter extends BaseAdapter {

	private int resourceId;				//视图资源ID
	private List<ShopBean> list;		//选中商品集合
	private LayoutInflater inflater;	//布局填充器
	public CheckOutAdapter(Context context,List<ShopBean> list
			,int resourceId){
		this.list = list;
		this.resourceId = resourceId;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ShopBean bean = list.get(position);
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(resourceId, null);
			holder.storeName = (TextView) convertView.findViewById(R.id.storeName);
			holder.checkOut_price = (TextView) convertView.findViewById(R.id.checkOut_price);
			holder.checkOut_description = 
					(TextView) convertView.findViewById(R.id.checkOut_description);
			holder.checkOut_number = (TextView) convertView.findViewById(R.id.checkOut_number);
			holder.checkOut_singlePrice = 
					(TextView) convertView.findViewById(R.id.checkOut_singlePrice);
			holder.checkout_size = (TextView) convertView.findViewById(R.id.checkout_size);
			holder.leaveWorld = (EditText) convertView.findViewById(R.id.leaveWorld);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		float allPrice = bean.getShopNumber()*bean.getShopPrice();
		holder.storeName.setText(bean.getStoreName());
		holder.checkOut_price.setText("￥"+allPrice);
		holder.checkOut_description.setText(bean.getShopName());
		holder.checkOut_number.setText(bean.getShopNumber()+"件");
		holder.checkout_size.setText(bean.getShopDescription());
		holder.checkOut_singlePrice.setText("￥"+bean.getShopPrice());
		
		return convertView;
	}
	
	@SuppressWarnings("unused")
	private final class ViewHolder{
		public TextView storeName;				//店家名称
		public TextView checkOut_price;			//该商品所需总金额
		public TextView checkOut_description;	//商品描述
		public TextView checkOut_number;		//商品数量
		public TextView checkout_size;			//商品颜色和尺码
		public TextView checkOut_singlePrice;	//单间商品的价格
		public EditText leaveWorld;				//给商家留言
	}

}
