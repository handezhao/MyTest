package com.example.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.Animation.PointEvaluator;
import com.example.bean.Point;

public class AnimView extends View {
	
	private static final String TAG = "AnimView";
	
	public static final float RADIUS = 50f;  
	  
    private Point currentPoint;  
  
    private Paint mPaint; 
    private float screenWidth;
    private float screenHeight;

	public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLUE);
		DisplayMetrics display = context.getResources().getDisplayMetrics();
		screenWidth = display.widthPixels;
		screenHeight = display.heightPixels;
	}
	public AnimView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}
	public AnimView(Context context) {
		this(context, null, 0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (currentPoint == null) {  
            currentPoint = new Point(RADIUS, RADIUS);  
            drawCircle(canvas);  
            startAnimation();  
        } else {  
            drawCircle(canvas);  
        }  
	}
	private void startAnimation() {
		Point startPoint = new Point(RADIUS, RADIUS);  
		Log.d(TAG, "getWidth() is " + getWidth() + "getHeight() is " + getHeight());
		
		Point endPoint = new Point(getWidth() - RADIUS , RADIUS);
		
        Point centerPoint = new Point(getWidth() - RADIUS , getHeight() -RADIUS);
        
		ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, centerPoint, endPoint);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				currentPoint = (Point) animation.getAnimatedValue();  
                invalidate(); 
			}
		});
		valueAnimator.setDuration(5000);
		valueAnimator.start();
	}
	private void drawCircle(Canvas canvas) {
		float x = currentPoint.getX();  
        float y = currentPoint.getY();  
        canvas.drawCircle(x, y, RADIUS, mPaint); 
	}
	
}
