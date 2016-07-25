package com.example.adapter;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
	
	private ButtonListener buttonListener;
	
	public void setButtonListener(ButtonListener listener) {
		this.buttonListener = listener;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
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
		holder.button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				if (buttonListener != null) {
					buttonListener.onclick(position);
				}
			}
		});
		
		return convertView;
	}
	
	class ViewHolder {
		TextView tvName, tvInfo;
		Button button;
	}
	
	public interface ButtonListener {
		void onclick(int position);
	}

}
