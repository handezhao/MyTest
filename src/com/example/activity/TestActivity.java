package com.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mytest.R;

public class TestActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Toast.makeText(this, "on stop2", Toast.LENGTH_LONG).show();
	}
}
