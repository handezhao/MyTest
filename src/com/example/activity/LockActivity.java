package com.example.activity;

import com.example.mytest.R;
import com.example.widget.SlideView;
import com.example.widget.SlideView.OnLockListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class LockActivity extends Activity {
	
	private SlideView slideView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lock);
		
		slideView = (SlideView) findViewById(R.id.slide_view);
		slideView.setmLockListener(new OnLockListener() {
			
			@Override
			public void onOpenLockSuccess() {
				Toast.makeText(LockActivity.this, "解锁成功", Toast.LENGTH_SHORT).show();
				finish();
				startActivity(new Intent(LockActivity.this, MainActivity.class));
			}
		});
	}

}
