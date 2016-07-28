package com.example.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bean.Music;
import com.example.help.Console;
import com.example.help.MusicHelper;
import com.example.mytest.R;

public class ThreeFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_three, container, false);
		List<Music> list = MusicHelper.scanMusic(getActivity());
		
		
		return view;
	}

}
