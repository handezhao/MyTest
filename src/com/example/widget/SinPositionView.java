package com.example.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.Animation.DeceAcceInterpolator;
import com.example.Animation.SinPositionEvaluator;
import com.example.bean.Point;

public class SinPositionView extends View {

	private static final String TAG = "SinPositionView";

	public static final float RADIUS = 50f;

	private Paint paint;
	
	private Point currentPoint;

	public SinPositionView(Context context) {
		this(context, null, 0);
	}

	public SinPositionView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SinPositionView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);
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
		ValueAnimator animator = ValueAnimator.ofObject(new SinPositionEvaluator(), new Point(getWidth() + RADIUS, RADIUS), new Point(getWidth() + RADIUS, getHeight() - RADIUS));
		animator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				currentPoint = (Point) animation.getAnimatedValue();
				invalidate();
			}
		});
		animator.setInterpolator(new DeceAcceInterpolator());
        animator.setDuration(10 * 1000).start();
	}

	private void drawCircle(Canvas canvas) {
		float x = currentPoint.getX();
		float y = currentPoint.getY();
		canvas.drawCircle(x, y, RADIUS, paint);
	}

}
