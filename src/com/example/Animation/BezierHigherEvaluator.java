package com.example.Animation;

import android.animation.TypeEvaluator;

import com.example.bean.Point;
import com.example.util.BezierUtil;

public class BezierHigherEvaluator implements TypeEvaluator<Point>{
	
	private Point controlPoint1, controlPoint2;

    public BezierHigherEvaluator(Point controlPoint1, Point controlPoint2) {
        this.controlPoint1 = controlPoint1;
        this.controlPoint2 = controlPoint2;
    }

	@Override
	public Point evaluate(float fraction, Point startValue, Point endValue) {
		return BezierUtil.CalculateBezierPointForCubic(fraction, startValue, controlPoint1, controlPoint2, endValue);
	}

}
