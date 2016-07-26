package com.example.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("ClickableViewAccessibility")
public class CountWidget extends View {

	private Paint paint;

	private Rect bounds;

	private int counts;
	
	private long lastTime;

	public CountWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setColor(Color.parseColor("#66CDAA"));
		bounds = new Rect();
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//画方框
		canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
		//画数字
		Paint textPaint = new Paint();
		textPaint.setColor(Color.parseColor("#000000"));
		String text = String.valueOf(counts);
		textPaint.setTextSize(80);
		
		textPaint.getTextBounds(text, 0, text.length(), bounds);
		float textWidth = bounds.width();
		float textHeight = bounds.height();
		canvas.drawText(text, getWidth()/2 - textWidth/2, getHeight()/2 + textHeight/2, textPaint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			paint.setColor(Color.parseColor("#3CB371"));
			counts++;
			lastTime = System.currentTimeMillis();
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			paint.setColor(Color.parseColor("#66CDAA"));
			if ((System.currentTimeMillis() - lastTime) > 1200) {
				counts = 0;
			}
			invalidate();
			break;
		}
		return true;
	}
}
