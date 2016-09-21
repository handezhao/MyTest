package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.activity.TouchEventActivity;
import com.example.mytest.R;

public class ThreeFragment extends Fragment {
	
	private TextView tvTouch;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_three, container, false);
		
		tvTouch = (TextView) view.findViewById(R.id.tv_touch);
		tvTouch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getActivity(), TouchEventActivity.class));
			}
		});
		return view;
	}

}
