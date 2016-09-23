package com.example.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import com.example.mytest.R;

public class AnimationActivity extends Activity {
	
	private LinearLayout llPoint1, llPoint2, llPoint3, llPoint4;
	private static final int DELAY = 200;
	private static final int DURATION = 2000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
		llPoint1 = (LinearLayout) findViewById(R.id.ll_point_circle_1);
		llPoint2 = (LinearLayout) findViewById(R.id.ll_point_circle_2);
		llPoint3 = (LinearLayout) findViewById(R.id.ll_point_circle_3);
		llPoint4 = (LinearLayout) findViewById(R.id.ll_point_circle_4);
		statrAnimation();
	}
	
	private void statrAnimation() {
		ObjectAnimator animator1 = ObjectAnimator.ofFloat(llPoint1, "rotation", 0, 360);
		animator1.setDuration(DURATION);
		animator1.setInterpolator(new AccelerateDecelerateInterpolator());
		animator1.setRepeatCount(-1);
		
		ObjectAnimator animator2 = ObjectAnimator.ofFloat(llPoint2, "rotation", 0, 360);
		animator2.setDuration(DURATION);
		animator2.setInterpolator(new AccelerateDecelerateInterpolator());
		animator2.setStartDelay(DELAY);
		animator2.setRepeatCount(-1);
		
		ObjectAnimator animator3 = ObjectAnimator.ofFloat(llPoint3, "rotation", 0, 360);
		animator3.setDuration(DURATION);
		animator3.setInterpolator(new AccelerateDecelerateInterpolator());
		animator3.setStartDelay(DELAY*2);
		animator3.setRepeatCount(-1);
		
		ObjectAnimator animator4 = ObjectAnimator.ofFloat(llPoint4, "rotation", 0, 360);
		animator4.setDuration(DURATION);
		animator4.setRepeatCount(-1);
		animator4.setInterpolator(new AccelerateDecelerateInterpolator());
		animator4.setStartDelay(DELAY*3);
		
		 AnimatorSet animatorSet = new AnimatorSet();
		 animatorSet.play(animator1).with(animator2).with(animator3).with(animator4);
		 animatorSet.setStartDelay(500);
		 animatorSet.start();
	}

}
