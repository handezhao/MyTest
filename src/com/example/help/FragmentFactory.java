package com.example.help;

import android.support.v4.app.Fragment;

import com.example.fragment.FourFragment;
import com.example.fragment.OneFragment;
import com.example.fragment.ThreeFragment;
import com.example.fragment.TwoFragment;

public class FragmentFactory {
	
	private OneFragment one;
	private TwoFragment two;
	private ThreeFragment three;
	private FourFragment four;
	
	public Fragment getItem(int index) {
		
		Fragment fragment = null;
		switch (index) {
		case 0:
			if (one == null) {
				one = new OneFragment();
			}
			fragment = one;
			break;
		case 1:
			if (two == null) {
				two = new TwoFragment();
			}
			fragment = two;
			break;
		case 2:
			if (three == null) {
				three = new ThreeFragment();
			}
			fragment = three;
			break;
		case 3:
			if (four == null) {
				four = new FourFragment();
			}
			fragment = four;
			break;

		}
		
		return fragment;
	} 
	

}
