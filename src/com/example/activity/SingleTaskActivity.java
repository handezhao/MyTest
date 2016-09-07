package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.mytest.R;

public class SingleTaskActivity extends BaseActivity {
	
	public static final String RESULT = "result"; 
	
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_task);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.putExtra(RESULT, 8);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
	

}
