package com.example.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytest.R;

public class FourFragment extends Fragment {
	
	private TextView tvTest;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_four, container, false);
		tvTest = (TextView) view.findViewById(R.id.tv_test_four);
		tvTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				long start = System.currentTimeMillis();
				init();
				long end = System.currentTimeMillis();
				Toast.makeText(getActivity(), (end - start) + "ms", Toast.LENGTH_LONG).show();
			}
		});
		return view;
	}
	
	private void init() {
		SharedPreferences sp = getActivity().getSharedPreferences("TAG", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		
		for (int i = 0; i < 100; i++) {
			editor.putString("prarm_" + i, "adslkwher lfsdfgljs bdglkdsflkwdf;kdfs kd " + System.currentTimeMillis());
		}
		editor.commit();
	}

}
