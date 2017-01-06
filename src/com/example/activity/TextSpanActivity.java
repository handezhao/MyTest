package com.example.activity;

import com.example.mytest.R;
import com.example.util.SpannableStringUtils;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.widget.TextView;

public class TextSpanActivity extends BaseActivity {
	
	private TextView tvText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_span);
		tvText = (TextView) findViewById(R.id.tv_text);
		tvText.setText(SpannableStringUtils.getBuilder("测试textSpannString\n").setBold().setForegroundColor(Color.GREEN).setAlign(Layout.Alignment.ALIGN_CENTER)
				.append("http://www.baidu.com").append(" URL ").setUrl("https://www.baidu.com").create());
	}

}
