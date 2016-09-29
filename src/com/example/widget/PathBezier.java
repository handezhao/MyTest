package com.example.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.Animation.BezierEvaluator;
import com.example.bean.Point;
import com.example.help.Console;

public class PathBezier extends View {

	private static final String TAG = "PathBezier";

	private Paint mPathPaint;
	private Paint mCirclePaint;

	private int mStartPointX;
	private int mStartPointY;
	private int mEndPointX;
	private int mEndPointY;

	private int mMovePointX;
	private int mMovePointY;

	private int mControlPointX;
	private int mControlPointY;

	private Path mPath;
	private Paint linePaint;

	public PathBezier(Context context) {
		super(context);
	}

	public PathBezier(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	        mPathPaint.setStyle(Paint.Style.STROKE);
	        mPathPaint.setStrokeWidth(5);
	        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	        
	        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	        linePaint.setStyle(Paint.Style.STROKE);
	        linePaint.setStrokeWidth(2);
	        linePaint.setColor(Color.parseColor("#9c9c9c"));
	        linePaint.setAntiAlias(true);
	        
	        DisplayMetrics display = context.getResources().getDisplayMetrics();
	        float screenWidth = display.widthPixels;
	        float screenHeight = display.heightPixels;
	        mStartPointX = 100;
	        mStartPointY = 100;
	        
	        mEndPointX = (int) (screenWidth - 60);
	        mEndPointY = (int) (screenHeight - 400);
	        
	        Log.d(TAG, "screenWidth is " + screenWidth + " and screenHeight is " + screenHeight);
	        Log.d(TAG, "mEndPointX is " + mEndPointX + " and mEndPointY is " + mEndPointY);
	        mMovePointX = mStartPointX;
	        mMovePointY = mStartPointY;
	        mControlPointX = (int) (screenWidth/2);
	        mControlPointY = 100;
	        mPath = new Path();
	    }

	public PathBezier(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPath.reset();
		canvas.drawCircle(mStartPointX, mStartPointY, 30, mCirclePaint);
		canvas.drawCircle(mEndPointX, mEndPointY, 30, mCirclePaint);
		mPath.moveTo(mStartPointX, mStartPointY);
		mPath.quadTo(mControlPointX, mControlPointY, mEndPointX, mEndPointY);
		canvas.drawPath(mPath, mPathPaint);
		canvas.drawCircle(mMovePointX, mMovePointY, 30, mCirclePaint);
		canvas.drawCircle(mControlPointX, mControlPointY, 10, mCirclePaint);
		canvas.drawLine(mStartPointX, mStartPointY, mControlPointX, mControlPointY, linePaint);
		canvas.drawLine(mControlPointX, mControlPointY, mMovePointX, mMovePointY, linePaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mControlPointX = (int) event.getX();
			mControlPointY = (int) event.getY();
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			mControlPointX = (int) event.getX();
			mControlPointY = (int) event.getY();
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			BezierEvaluator bezierEvaluator = new BezierEvaluator(new Point(mControlPointX, mControlPointY));
			Point start = new Point(mStartPointX, mStartPointY);
			Point end = new Point(mEndPointX, mEndPointY);
			ValueAnimator anim = ValueAnimator.ofObject(bezierEvaluator, start, end);
			anim.setDuration(3000);
			anim.setInterpolator(new AccelerateDecelerateInterpolator());
			anim.addUpdateListener(new AnimatorUpdateListener() {

				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					Point currentPoint = (Point) animation.getAnimatedValue();
					mMovePointX = (int) currentPoint.getX();
					mMovePointY = (int) currentPoint.getY();
					invalidate();
				}
			});
			anim.start();
			break;
		}
		return true;
	}

}
