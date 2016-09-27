package com.example.Animation;

import android.animation.TypeEvaluator;

import com.example.bean.Point;
import com.example.util.BezierUtil;

public class BezierEvaluator implements TypeEvaluator<Point> {
	
	private Point mControlPoint;

    public BezierEvaluator(Point controlPoint) {
        this.mControlPoint = controlPoint;
    }

	@Override
	public Point evaluate(float fraction, Point start, Point end) {
		return BezierUtil.CalculateBezierPointForQuadratic(fraction, start, mControlPoint, end);
	}

}
