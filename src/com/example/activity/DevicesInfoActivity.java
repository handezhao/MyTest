package com.example.activity;

import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.mytest.R;


public class DevicesInfoActivity extends Activity {
	
	private ArrayList<String> list = new ArrayList<String>();
	private ListView lvInfo;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_devices_info);
		lvInfo = (ListView) findViewById(R.id.lv_info);
		initData();
		lvInfo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
	}
	
	@SuppressLint("NewApi")
	private void initData() {
		list.clear();
	    list.add("android.os.Build.BOARD " + android.os.Build.BOARD);
	    list.add("android.os.Build.BOOTLOADER " + android.os.Build.BOOTLOADER);
	    list.add("android.os.Build.BRAND " + android.os.Build.BRAND);
	    list.add("android.os.Build.CPU_ABI " + android.os.Build.CPU_ABI);
	    list.add("android.os.Build.CPU_ABI2 " + android.os.Build.CPU_ABI2);
	    list.add("android.os.Build.DEVICE " + android.os.Build.DEVICE);
	    list.add("android.os.Build.DISPLAY " + android.os.Build.DISPLAY);
	    list.add("android.os.Build.FINGERPRINT " + android.os.Build.FINGERPRINT);
	    list.add("android.os.Build.HARDWARE " + android.os.Build.HARDWARE);
	    list.add("android.os.Build.HOST " + android.os.Build.HOST);
	    list.add("android.os.Build.ID " + android.os.Build.ID);
	    list.add("android.os.Build.MANUFACTURER " + android.os.Build.MANUFACTURER);
	    list.add("android.os.Build.MODEL " + android.os.Build.MODEL);
	    list.add("android.os.Build.PRODUCT " + android.os.Build.PRODUCT);
	    list.add("android.os.Build.RADIO " + android.os.Build.RADIO);
	    list.add("android.os.Build.SERIAL " + android.os.Build.SERIAL);
	    list.add("android.os.Build.TAGS " + android.os.Build.TAGS);
	    list.add("android.os.Build.TIME " + android.os.Build.TIME);
	    list.add("android.os.Build.TYPE " + android.os.Build.TYPE);
	    list.add("android.os.Build.UNKNOWN " + android.os.Build.UNKNOWN);
	    list.add("android.os.Build.USER " + android.os.Build.USER);
	    list.add("android.os.Build.getRadioVersion() " + android.os.Build.getRadioVersion());
	    list.add("android.os.Build.VERSION.SDK_INT " + android.os.Build.VERSION.SDK_INT);
	}

}
