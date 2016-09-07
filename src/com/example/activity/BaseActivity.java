package com.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 Log.i("han", "*****onCreate()方法******");
	        Log.i("han", "onCreate：" + getClass().getSimpleName() + " TaskId: " + getTaskId() + " hasCode:" + this.hashCode());
	        dumpTaskAffinity();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		 Log.i("han", "*****onNewIntent()方法*****");
	        Log.i("han", "onNewIntent：" + getClass().getSimpleName() + " TaskId: " + getTaskId() + " hasCode:" + this.hashCode());
	        dumpTaskAffinity();
	}

	protected void dumpTaskAffinity() {
		try {
			ActivityInfo info = this.getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
			Log.i("han", "taskAffinity:" + info.taskAffinity);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
	}

}
