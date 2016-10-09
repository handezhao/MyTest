package com.example.service;


import com.example.activity.LockActivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LockService extends Service {
	
	private static final String TAG = "LockService";
	
	private ScreenBroadcastReceiver screenBroadcastReceiver;
	
	private MyBinder mBinder = new MyBinder(); 
	
	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		startForeground(0, null);
		IntentFilter filter = new IntentFilter();  
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(Intent.ACTION_SCREEN_ON);  
		filter.addAction(Intent.ACTION_SCREEN_OFF); 
		filter.addAction(Intent.ACTION_USER_PRESENT);

		if (screenBroadcastReceiver == null) {
			screenBroadcastReceiver = new ScreenBroadcastReceiver();
		}
		
		registerReceiver(screenBroadcastReceiver, filter);
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand");
		Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		unregisterReceiver(screenBroadcastReceiver);
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind");
		return mBinder;
	}
	
	
	public class ScreenBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Log.d(TAG, "action is " + action);
			if (Intent.ACTION_SCREEN_OFF.equals(action)) {
				Log.d(TAG, "screen off");
				startLockActivity();
			} else if (Intent.ACTION_SCREEN_ON.equals(action)) {
				Log.d(TAG, "screen on");
				startLockActivity();
			}
		}
	}
	
	private void startLockActivity() {
		Intent lockIntent = new Intent();
		lockIntent.setClass(LockService.this, LockActivity.class);
		lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(lockIntent);
	}
	
	public class MyBinder extends Binder {
		public void startDownload() {
            Log.d("TAG", "startDownload() executed");  
            // 执行具体的下载任务  
        }  
	}


}
