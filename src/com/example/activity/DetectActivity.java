package com.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.example.mytest.R;

public class DetectActivity extends Activity {

	private TextView tvFrontCamera, tvBackCamera;

	private boolean cameraFront = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_detect);
		init();
	}

	private void init() {
		tvFrontCamera = (TextView) findViewById(R.id.tv_front_camera);
		tvFrontCamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				startFrontCamera();
			}

		});

		tvBackCamera = (TextView) findViewById(R.id.tv_back_camera);
		tvBackCamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				startBackCamera();
			}

		});

	}

	private void startBackCamera() {
		// Intent intent = new Intent();
		// intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		// intent.putExtra("autofocus", true);
		// startActivityForResult(intent,0);
		Camera camera = null; 
		
		 int CammeraIndex= findBackFacingCamera();
	        if(CammeraIndex == -1) {
	        	CammeraIndex = findFrontFacingCamera();
	        }
	    	camera = Camera.open(CammeraIndex);
	}

	private void startFrontCamera() {
		// TODO Auto-generated method stub

	}

	private int findFrontFacingCamera() {
		int cameraId = -1;
		// Search for the front facing camera
		int numberOfCameras = Camera.getNumberOfCameras();
		for (int i = 0; i < numberOfCameras; i++) {
			Camera.CameraInfo info = new Camera.CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
				cameraId = i;
				cameraFront = true;
				break;
			}
		}
		return cameraId;
	}

	private int findBackFacingCamera() {
		int cameraId = -1;
		// Search for the back facing camera
		// get the number of cameras
		int numberOfCameras = Camera.getNumberOfCameras();
		// for every camera check
		for (int i = 0; i < numberOfCameras; i++) {
			Camera.CameraInfo info = new Camera.CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
				cameraId = i;
				cameraFront = false;
				break;
			}
		}
		return cameraId;
	}
}
