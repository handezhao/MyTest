package com.example.widget;

import com.example.Animation.DeceAcceInterpolator;
import com.example.Animation.PositionEvaluator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class PositionView extends View {

	public static final float RADIUS = 30f;
	private PositionPoint currentPoint;
	private Paint mPaint;
	private float screenWidth;
	private float screenHeight;

	public PositionView(Context context) {
		this(context, null, 0);
	}

	public PositionView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}

	public PositionView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setColor(Color.RED);
		DisplayMetrics display = context.getResources().getDisplayMetrics();
		screenWidth = display.widthPixels;
		screenHeight = display.heightPixels;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (currentPoint == null) {
			currentPoint = new PositionPoint(RADIUS, RADIUS);
			drawCircle(canvas);
			startPropertyAni();
		} else {
			drawCircle(canvas);
		}
	}

	private void drawCircle(Canvas canvas) {

		float x = currentPoint.getX();
		float y = currentPoint.getY();
		canvas.drawCircle(x, y, RADIUS, mPaint);
	}

	/**
	 * 启动动画
	 */
	private void startPropertyAni() {
		ValueAnimator animator = ValueAnimator.ofObject(new PositionEvaluator(), createPoint(RADIUS, RADIUS), createPoint(getWidth() - RADIUS, getHeight() - RADIUS));
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				currentPoint = (PositionPoint) animation.getAnimatedValue();
				invalidate();
			}
		});
		// 设置自定义的减速加速插值器DeceAcceInterpolator()
		animator.setInterpolator(new DeceAcceInterpolator());
		animator.setDuration(10 * 1000).start();
	}

	/**
	 * createPoint()创建PositionPointView对象
	 */
	public PositionPoint createPoint(float x, float y) {
		return new PositionPoint(x, y);
	}

	/**
	 * 小圆点内部类
	 */
	public class PositionPoint {
		private float x;
		private float y;

		public PositionPoint(float x, float y) {
			this.x = x;
			this.y = y;
		}

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}
	}
}
