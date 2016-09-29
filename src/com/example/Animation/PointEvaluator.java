package com.example.Animation;

import android.animation.TypeEvaluator;

import com.example.bean.Point;

public class PointEvaluator implements TypeEvaluator<Point> {

	@Override
	public Point evaluate(float fraction, Point startValue, Point endValue) {
		Point startPoint = startValue;
		Point endPoint = endValue;
		float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
		float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
		Point point = new Point(x, y);
		return point;
	}
}
