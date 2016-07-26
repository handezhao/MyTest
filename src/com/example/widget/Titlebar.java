package com.example.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mytest.R;

public class Titlebar extends RelativeLayout {

	private TextView tvLeft, tvTitle;

	public Titlebar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs, defStyleAttr);
	}

	public Titlebar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public Titlebar(Context context) {
		super(context);
		init(context, null, 0);
	}

	private void init(Context context, AttributeSet attrs, int defStyleAttr) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.widget_titlebar, this);
		tvLeft = (TextView) view.findViewById(R.id.tv_left);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);

		if (attrs == null) {
			return;
		}

		TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.Titlebar, defStyleAttr, 0);
		if (a != null) {
			int count = a.getIndexCount();
			int index = 0;

			for (int i = 0; i < count; i++) {
				index = a.getIndex(i);
				switch (index) {
				case R.styleable.Titlebar_left_text:
					String leftText = a.getString(index);
					setLeftText(leftText);
					break;

				case R.styleable.Titlebar_title:
					String title = a.getString(index);
					setTitle(title);
					break;
				}
			}
		}
		a.recycle();
	}

	public void setLeftText(String text) {
		if (TextUtils.isEmpty(text)) {
		} else {
			tvLeft.setVisibility(View.VISIBLE);
			tvLeft.setText(text);
		}
	}

	public void setTitle(String text) {
		if (TextUtils.isEmpty(text)) {
		} else {
			tvTitle.setVisibility(View.VISIBLE);
			tvTitle.setText(text);
		}
	}

	public void setLeftListener(OnClickListener listener) {
		tvLeft.setOnClickListener(listener);
	}
}
