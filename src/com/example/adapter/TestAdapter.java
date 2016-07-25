package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.bean.TestBean;
import com.example.mytest.R;

public class TestAdapter extends BaseAdapter {
	
	private List<TestBean> list = new ArrayList<TestBean>();
	private Context context;
	private LayoutInflater inflater;
	
	public TestAdapter(ArrayList<TestBean> list, Context context) {
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(context);
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
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_test_bean, null);
			holder = new ViewHolder();
			holder.button = (Button) convertView.findViewById(R.id.button_item);
			holder.tvInfo = (TextView) convertView.findViewById(R.id.tv_info);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.button.setText("try");
		holder.tvInfo.setText(list.get(position).getInfo());
		holder.tvName.setText(list.get(position).getName());
		
		return convertView;
	}
	
	class ViewHolder {
		TextView tvName, tvInfo;
		Button button;
	}

}
