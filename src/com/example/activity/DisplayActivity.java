package com.example.activity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.help.SystemHelper;
import com.example.mytest.R;

public class DisplayActivity extends Activity {
	
	private TextView tvMetrics, tvCpu, tvRoom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_display);
		init();
		tvMetrics.setText(getMetrics());
		tvCpu.setText(getCup());
		String roomInfo = "有无SD卡：" +  (SystemHelper.externalMemoryAvailable() ? "有" : "无") + "\n" +
		 "SD内存： " + SystemHelper.getTotalExternalMemorySize() + " / " + SystemHelper.getAvailableExternalMemorySize() + "\n"
		 + "手机内存： " + SystemHelper.getTotalInternalMemorySize() + " / " + SystemHelper.getAvailableInternalMemorySize() + "\n"
		 + "系统总内存/可用内存： " + SystemHelper.getTotalMemorySize(this) + " / " + SystemHelper.getAvailableMemory(this) + "\n"
		 + "是否支持多点触屏：" + SystemHelper.isSupportMultiTouch(this);
		
		tvRoom.setText(roomInfo);
	}

	private void init() {
		tvMetrics = (TextView) findViewById(R.id.tv_metrics);
		tvCpu = (TextView) findViewById(R.id.tv_cpu);
		tvRoom = (TextView) findViewById(R.id.tv_room);
	}
	
	private String getMetrics() {
		WindowManager windowManager = getWindowManager();    
        Display display = windowManager.getDefaultDisplay();    
        int screenWidth = screenWidth = display.getWidth();    
        int screenHeight = screenHeight = display.getHeight();  
		return "屏幕分辨率：" + screenWidth + " * " + screenHeight;
	}
	
	private String getCup() {
		
		 String str1 = "/proc/cpuinfo";  
		    String str2="";  
		    String[] cpuInfo={"",""};  
		    String[] arrayOfString;  
		    try {  
		        FileReader fr = new FileReader(str1);  
		        BufferedReader localBufferedReader = new BufferedReader(fr, 8192);  
		        str2 = localBufferedReader.readLine();  
		        arrayOfString = str2.split("\\s+");  
		        for (int i = 2; i < arrayOfString.length; i++) {  
		            cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";  
		        }  
		        str2 = localBufferedReader.readLine();  
		        arrayOfString = str2.split("\\s+");  
		        cpuInfo[1] += arrayOfString[2];  
		        localBufferedReader.close();  
		    } catch (IOException e) {  
		    } 
		    String info = cpuInfo[0] + cpuInfo[1];
		    return "CPU信息： " + info;  
	}
}
