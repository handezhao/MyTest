package com.example.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.mytest.R;

public class SlideView extends View {

	private Paint paint;
	private Paint bgPaint;
	private Paint crPaint;

	private String tipText;
	private int tipsTextSize;
	private int tipsTextColor;
	private int radios;

	private float locationX;
	private RectF rectF;
	private RectF rectF2;
	private boolean isDragable = false;
	private OnLockListener lockListener;

	public SlideView(Context context) {
		this(context, null, 0);
	}

	public SlideView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray tp = context.obtainStyledAttributes(attrs, R.styleable.SlideLockView, defStyleAttr, 0);

		tipText = tp.getString(R.styleable.SlideLockView_lock_tips_tx);
		tipsTextSize = tp.getDimensionPixelOffset(R.styleable.SlideLockView_locl_tips_tx_size, 12);
		tipsTextColor = tp.getColor(R.styleable.SlideLockView_lock_tips_tx_color, Color.BLACK);
		radios = tp.getDimensionPixelOffset(R.styleable.SlideLockView_radios, 10);
		tp.recycle();
		init(context);
	}

	private void init(Context context) {
		paint = new Paint();
		paint.setColor(tipsTextColor);
		paint.setAntiAlias(true);
		paint.setTextSize(tipsTextSize);

		bgPaint = new Paint();
		bgPaint.setColor(Color.parseColor("#9c9c9c"));
		bgPaint.setAntiAlias(true);
		bgPaint.setStyle(Paint.Style.STROKE);
		bgPaint.setStrokeWidth(2);

		crPaint = new Paint();
		crPaint.setAntiAlias(true);
		crPaint.setColor(Color.parseColor("#00ff00"));
		crPaint.setStyle(Paint.Style.FILL);

		rectF = new RectF(0, 0, radios * 2, radios * 2);
		rectF2 = new RectF(radios * 6, 0, radios * 8, radios * 2);

		locationX = radios;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// draw the background
		Path path = new Path();
		path.moveTo(0, 0);
		path.addArc(rectF, 90, 180);
		path.moveTo(radios, 0);
		path.lineTo(radios * 7, 0);
		path.addArc(rectF2, -90, 180);
		path.moveTo(radios * 7, radios * 2);
		path.lineTo(radios, radios * 2);
		canvas.drawPath(path, bgPaint);

		float textWidth = paint.measureText(tipText);
		canvas.drawText(tipText, getWidth() / 2 - textWidth / 2, radios + paint.getTextSize() / 2, paint);

		// draw circle
		// int centerX = locationX - radios;
		canvas.drawCircle(locationX, radios, radios, crPaint);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float x = event.getX();
			float y = event.getY();
			if (isTouchLock(x, y)) {
				isDragable = true;
				if (locationX > radios) {
					locationX = x - radios;
				}
			} else {
				isDragable = false;
			}
			invalidate();
			return true;
		case MotionEvent.ACTION_MOVE:
			if (!isDragable)
				return true;
			int rightMax = getWidth() - radios * 2;
			resetLocationX(event.getX(), rightMax);
			invalidate();

			if (locationX >= rightMax) {
				isDragable = false;
				locationX = 0;
				invalidate();
				if (lockListener != null) {
					lockListener.onOpenLockSuccess();
				}
				Log.e("AnimaterListener", "解锁成功");
			}
		case MotionEvent.ACTION_UP:
			if (!isDragable) return true;
            resetLock();
			break;
		}
		return super.onTouchEvent(event);
	}

	private void resetLock() {
		ValueAnimator anim = ValueAnimator.ofFloat(locationX,radios);
        anim.setDuration(500);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                locationX = (Float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        anim.start();
	}

	private void resetLocationX(float x, int rightMax) {
		locationX = x - radios;
		if (locationX < 0) {
			locationX = 0;
		} else if (locationX >= rightMax) {
			locationX = rightMax;
		}
	}

	private boolean isTouchLock(float x, float y) {

		if ((x >= 0 || x <= 2 * radios) && (y >= 0 || y <= 2 * radios)) {
			return true;
		} else {
			return false;
		}
	}

	public void setmLockListener(OnLockListener lockListener) {
		this.lockListener = lockListener;
	}

	public interface OnLockListener {
		void onOpenLockSuccess();
	}

}
