package com.example.Animation;

import android.animation.TypeEvaluator;

import com.example.bean.Point;

public class SinPositionEvaluator implements TypeEvaluator {

	@Override
	public Object evaluate(float fraction, Object start, Object end) {
		Point startPoint = (Point) start;
		float x = forCurrentX(fraction);
		float y = forCurrentY(fraction, startPoint.getY());
		Point result = new Point(x, y);
		return result;
	}

	/**
	 * 计算Y坐标
	 */
	private float forCurrentY(float fraction, float currentY) {
		float resultY = currentY;
		if (fraction != 0f) {
			resultY = fraction * 800f + 20f;
		}
		return resultY;
	}

	/**
	 * 计算X坐标
	 */
	private float forCurrentX(float fraction) {
		float range = 180f;// 振幅
		float resultX = 260f + (float) Math.sin((10 * fraction) * Math.PI) * range;// 周期为5，故为10fraction
		return resultX;
	}

}
