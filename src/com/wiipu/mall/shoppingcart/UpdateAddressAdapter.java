package com.wiipu.mall.shoppingcart;

import java.util.List;

import com.wiipu.mall.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UpdateAddressAdapter extends BaseAdapter {

	private int resourceId;					//条目视图资源ID
	private List<AddressBean> list;			//地址数据集合
	private LayoutInflater inflater;		//布局填充器
	public UpdateAddressAdapter(Context context,List<AddressBean> list
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
		AddressBean bean = list.get(position);
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(resourceId, null);
			holder.addresseeItemName = (TextView) 
					convertView.findViewById(R.id.addresseeItemName);
			holder.smearedItemAddress = (TextView)
					convertView.findViewById(R.id.smearedItemAddress);
			holder.detailItemAddress = (TextView)
					convertView.findViewById(R.id.detailItemAddress);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.addresseeItemName.setText(bean.getName());
		holder.smearedItemAddress.setText(bean.getSmearedAddress());
		holder.detailItemAddress.setText(bean.getDetailAddress());
		
		return convertView;
	}
	
	private final class ViewHolder{
		public TextView addresseeItemName;	//收件人姓名
		public TextView smearedItemAddress;	//区地址
		public TextView detailItemAddress;	//详细地址
	}

}
