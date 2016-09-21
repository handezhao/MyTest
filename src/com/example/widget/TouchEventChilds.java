package com.example.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.util.TouchEventUtil;

public class TouchEventChilds extends LinearLayout {

	  public TouchEventChilds(Context context) {
	         super(context);
	     }

	     public TouchEventChilds(Context context, AttributeSet attrs) {
	         super(context, attrs);
	     }

	     public boolean dispatchTouchEvent(MotionEvent ev) {
	         Log.e("sunzn", "TouchEventChilds | dispatchTouchEvent --> " + super.dispatchTouchEvent(ev));
	         return super.dispatchTouchEvent(ev);
	         // return false;
	     }

	     public boolean onInterceptTouchEvent(MotionEvent ev) {
	         Log.i("sunzn", "TouchEventChilds | onInterceptTouchEvent --> " + super.onInterceptTouchEvent(ev));
	         return super.onInterceptTouchEvent(ev);
	         // return false;
	     }

	     public boolean onTouchEvent(MotionEvent ev) {
	         Log.d("sunzn", "TouchEventChilds | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
	         return super.onTouchEvent(ev);
	     }
}
