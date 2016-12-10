package com.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.example.mytest.R;
import com.example.util.TouchEventUtil;
import com.example.widget.TouchEventChilds;

public class TouchEventActivity extends Activity {
	
	private TouchEventChilds childs;
	
	public void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         setContentView(R.layout.activity_touch);
	         childs = (TouchEventChilds) findViewById(R.id.childs);
	         childs.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					
					return true;
				}
			});
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
