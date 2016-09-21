package com.example.activity;

import com.example.mytest.R;
import com.example.util.TouchEventUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class TouchEventActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         setContentView(R.layout.activity_touch);
	     }

	     public boolean dispatchTouchEvent(MotionEvent ev) {
	         Log.w("sunzn", "TouchEventActivity | dispatchTouchEvent --> " + super.dispatchTouchEvent(ev));
	         return super.dispatchTouchEvent(ev);
	     }

	     public boolean onTouchEvent(MotionEvent event) {
	         Log.w("sunzn", "TouchEventActivity | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()));
	         return super.onTouchEvent(event);
	     }


}
