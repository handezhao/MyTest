package com.example.activity;

import com.example.mytest.R;
import com.example.service.LockService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ServiceActivity extends Activity implements OnClickListener {
	
	private TextView tvBind, tvStart, tvUnBind, tvStop;
	
	 private LockService.MyBinder myBinder;  
	  
	    private ServiceConnection connection = new ServiceConnection() {  
	  
	        @Override  
	        public void onServiceDisconnected(ComponentName name) {  
	        }  
	  
	        @Override  
	        public void onServiceConnected(ComponentName name, IBinder service) {  
	            myBinder = (LockService.MyBinder) service;  
	            myBinder.startDownload();
	        }  
	    };  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
		tvBind = (TextView) findViewById(R.id.tv_bind);
		tvBind.setOnClickListener(this);
		
		tvStart = (TextView) findViewById(R.id.tv_start);
		tvStart.setOnClickListener(this);
		
		tvUnBind = (TextView) findViewById(R.id.tv_unbind);
		tvUnBind.setOnClickListener(this);
		
		tvStop = (TextView) findViewById(R.id.tv_stop);
		tvStop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_start:
			startService(new Intent(ServiceActivity.this, LockService.class));
			break;
		case R.id.tv_bind:
			bindService(new Intent(ServiceActivity.this, LockService.class), connection, BIND_AUTO_CREATE);
			break;
		case R.id.tv_unbind:
			unbindService(connection);
			break;
		case R.id.tv_stop:
			stopService(new Intent(ServiceActivity.this, LockService.class));
			break;
		}
	}

}
