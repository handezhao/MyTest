package com.example.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activity.LinearLayoutActivity;
import com.example.activity.ServiceActivity;
import com.example.activity.TextSpanActivity;
import com.example.activity.ViideoActivity;
import com.example.adapter.TestAdapter;
import com.example.adapter.TestAdapter.ButtonListener;
import com.example.bean.TestBean;
import com.example.help.Console;
import com.example.mytest.R;
import com.example.util.ToastUtil;
import com.example.widget.Titlebar;

public class OneFragment extends Fragment implements OnItemClickListener {
	
	private ListView lvTest;
	
	private ArrayList<TestBean> list = new ArrayList<TestBean>();
	
	private TestAdapter adapter;
	private Titlebar titlebar;
	private TextView tvService, tvVideo;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_one, container, false);
		lvTest = (ListView) view.findViewById(R.id.lv_test);
		lvTest.setOnItemClickListener(this);
		titlebar = (Titlebar) view.findViewById(R.id.titlebar);
		titlebar.setLeftListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Console.toast(getActivity(), "hahaha");
			}
		});
		
		tvService = (TextView) view.findViewById(R.id.tv_service);
		tvService.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				startActivity(new Intent(getActivity(), ServiceActivity.class));
			}
		});
		
		tvVideo = (TextView) view.findViewById(R.id.tv_video);
		tvVideo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), ViideoActivity.class));
			}
		});
		
		View toastView = inflater.inflate(R.layout.toast, null);
		ToastUtil toast = new ToastUtil(getActivity(), toastView, 500);
		toast.show();
		initdata();
		return view;
	}

	private void initdata() {
		
		for (int i = 1; i < 6; i++) {
			TestBean bean = new TestBean();
			bean.setName("name " + i);
			bean.setInfo("test info " + i);
			list.add(bean);
		}
		
		if (list.size() > 0) {
			adapter = new TestAdapter(list, getActivity());
			lvTest.setAdapter(adapter);
			adapter.setButtonListener(new ButtonListener() {
				

				@Override
				public void onclick(int position) {
					Console.toast(getActivity(), String.valueOf(position));
				}
			});
		}
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (position == 0) {
			startActivity(new Intent(getActivity(), LinearLayoutActivity.class));
		} else if (position == 1) {
			startActivity(new Intent(getActivity(), TextSpanActivity.class));
		}
//		Toast.makeText(getActivity(), "" + (position + 1), Toast.LENGTH_SHORT).show();
	}
}
