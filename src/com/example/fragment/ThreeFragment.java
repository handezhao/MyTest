package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.activity.AnimationActivity;
import com.example.activity.BezierActivity;
import com.example.activity.BezierPathActivity;
import com.example.activity.PositionAnimationActivity;
import com.example.activity.SinPathActivity;
import com.example.activity.TouchEventActivity;
import com.example.mytest.R;

public class ThreeFragment extends Fragment implements OnClickListener {
	
	private TextView tvTouch, tvAnimation, tvPosition, tvSin, tvBezier, tvBezierAnima;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_three, container, false);
		
		tvTouch = (TextView) view.findViewById(R.id.tv_touch);
		tvTouch.setOnClickListener(this);
		
		tvAnimation = (TextView) view.findViewById(R.id.tv_animation);
		tvAnimation.setOnClickListener(this);
		
		tvPosition = (TextView) view.findViewById(R.id.tv_position);
		tvPosition.setOnClickListener(this);
		
		tvSin = (TextView) view.findViewById(R.id.tv_sin);
		tvSin.setOnClickListener(this);
		
		tvBezier = (TextView) view.findViewById(R.id.tv_bezier);
		tvBezier.setOnClickListener(this);
		
		tvBezierAnima = (TextView) view.findViewById(R.id.tv_bezier_animator);
		tvBezierAnima.setOnClickListener(this);
		
		return view;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_touch:
			startActivity(new Intent(getActivity(), TouchEventActivity.class));
			break;
		case R.id.tv_animation:
			startActivity(new Intent(getActivity(), AnimationActivity.class));
			break;
		case R.id.tv_position:
			startActivity(new Intent(getActivity(), PositionAnimationActivity.class));
			break;
		case R.id.tv_sin:
			startActivity(new Intent(getActivity(), SinPathActivity.class));
			break;
		case R.id.tv_bezier:
			startActivity(new Intent(getActivity(), BezierActivity.class));
			break;
		case R.id.tv_bezier_animator:
			startActivity(new Intent(getActivity(), BezierPathActivity.class));
			break;
		}
	}

}
