package com.example.Animation;

import android.view.animation.Interpolator;

public class DeceAcceInterpolator implements Interpolator {

	@Override
	public float getInterpolation(float input) {
		// TODO Auto-generated method stub
		return ((4*input-2)*(4*input-2)*(4*input-2))/16f + 0.5f;
	}

}
