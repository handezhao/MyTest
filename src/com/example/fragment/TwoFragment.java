package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

import com.example.activity.DevicesInfoActivity;
import com.example.activity.DisplayActivity;
import com.example.activity.SensorActivity;
import com.example.mytest.R;

public class TwoFragment extends Fragment {

	private TextView tvDeviceInfo, tvSensorInfo, tvDisplay, tvElse;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_two, container, false);
		init(view);
		return view;
	}

	private void init(View view) {
		tvDeviceInfo = (TextView) view.findViewById(R.id.tv_toInfo);
		tvDeviceInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), DevicesInfoActivity.class);
				startActivity(intent);
			}
		});

		tvSensorInfo = (TextView) view.findViewById(R.id.tv_senorInfo);
		tvSensorInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), SensorActivity.class);
				startActivity(intent);
			}
		});

		tvDisplay = (TextView) view.findViewById(R.id.tv_display);
		tvDisplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), DisplayActivity.class);
				startActivity(intent);
			}
		});

		tvElse = (TextView) view.findViewById(R.id.tv_else);
		tvElse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), SensorActivity.class);
				startActivity(intent);
			}
		});
	}
}
