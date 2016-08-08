package com.example.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;

import com.example.help.FragmentFactory;
import com.example.mytest.R;

public class MainActivity extends FragmentActivity implements OnClickListener{
	
	private RelativeLayout rlOne, rlTwo, rlThree, rlFour;
	
	private FragmentFactory factory;
	private boolean switchingFragment;
	private int currentFragmentId = -1;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		findViewById();
		setListener();
		factory = new FragmentFactory();
		switchFragment(0);
	}
	
	private void setListener() {
		rlOne.setOnClickListener(this);
		rlTwo.setOnClickListener(this);
		rlThree.setOnClickListener(this);
		rlFour.setOnClickListener(this);

	}
	
	private void findViewById() {
		rlOne = (RelativeLayout) findViewById(R.id.rl_navigate_one);
		rlTwo = (RelativeLayout) findViewById(R.id.rl_navigate_two);
		rlThree = (RelativeLayout) findViewById(R.id.rl_navigate_three);
		rlFour = (RelativeLayout) findViewById(R.id.rl_navigate_four);
	}

	@Override
	public void onClick(View view) {
		int i = 0;
		if (rlOne == view) {
			i = 0;
		} else if (rlTwo == view) {
			i = 1;
		} else if (rlThree == view) {
			i = 2;
		} else if (rlFour == view) {
			i = 3;
		}
		switchFragment(i);
	}
	
	private synchronized void switchFragment(int index) {

		if (switchingFragment) {
			return;
		}
		if (currentFragmentId == index) {
			return;
		}

		switchingFragment = true;
		rlOne.setSelected(false);
		rlTwo.setSelected(false);
		rlThree.setSelected(false);
		rlFour.setSelected(false);

		Fragment from = null;
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction trans = fm.beginTransaction();

		if (currentFragmentId > -1) {
			from = factory.getItem(currentFragmentId);
			trans.hide(from);
		}

		Fragment to = factory.getItem(index);
		if (to.isAdded()) {
			trans.show(to);
		} else {
			trans.add(R.id.fl_fragment_container, to).show(to);
		}
		trans.commit();
		currentFragmentId = index;

		switch (index) {
		case 0:
			rlOne.setSelected(true);
			break;
		case 1:
			rlTwo.setSelected(true);
			break;
		case 2:
			rlThree.setSelected(true);
			break;
		case 3:
			rlFour.setSelected(true);
			break;
		}

		switchingFragment = false;
	
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
//		Toast.makeText(this, "on stop", Toast.LENGTH_LONG).show();
		super.onStop();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
//		Toast.makeText(this, "on pause", Toast.LENGTH_LONG).show();
//		new Thread() {
//			public void run() {
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				MainActivity.this.runOnUiThread(new Runnable() {
//					
//					@Override
//					public void run() {
//						Toast.makeText(MainActivity.this, "start another", Toast.LENGTH_LONG).show();
//					}
//				});
//				
//				Intent intent = new Intent(MainActivity.this, TestActivity.class);
//				MainActivity.this.startActivity(intent);
//			};
//		}.start();
	}
}
