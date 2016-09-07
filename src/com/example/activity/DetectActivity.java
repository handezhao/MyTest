package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.example.help.Console;
import com.example.mytest.R;

public class DetectActivity extends BaseActivity {

	private TextView tvToSingleTask, tvBackCamera;

	public static final int REQUEST_CODE_SINGLETASK = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_detect);
		init();
	}

	private void init() {
		tvToSingleTask = (TextView) findViewById(R.id.tv_front_camera);
		tvToSingleTask.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(DetectActivity.this, SingleTaskActivity.class);
				startActivityForResult(intent, REQUEST_CODE_SINGLETASK);
			}

		});

		tvBackCamera = (TextView) findViewById(R.id.tv_back_camera);
		tvBackCamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
			}

		});

	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) {
			Console.toast(this, "result not ok");
			return;
		} 
		if (requestCode == REQUEST_CODE_SINGLETASK) {
			int result = data.getIntExtra(SingleTaskActivity.RESULT, 2);
			Console.toast(this, "回调成功！" + result);
			
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}
