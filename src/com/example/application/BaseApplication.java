package com.example.application;

import android.app.Application;

public class BaseApplication extends Application {

	private static BaseApplication instance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = BaseApplication.this;
		
	}

	public static BaseApplication getInstance() {
		return instance;
	}


}
