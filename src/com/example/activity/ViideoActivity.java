package com.example.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.VideoView.UniversalMediaController;
import com.example.VideoView.UniversalVideoView;
import com.example.mytest.R;

public class ViideoActivity extends Activity implements UniversalVideoView.VideoViewCallback {

	private static final String TAG = "ViideoActivity";
	private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
	// private static final String VIDEO_URL =
	// "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
	private static final String VIDEO_URL = "http://0.s3.envato.com/h264-video-previews/80fad324-9db4-11e3-bf3d-0050569255a8/490527.mp4";

	private UniversalVideoView mVideoView;
	private UniversalMediaController mMediaController;

	private View mBottomLayout;
	private View mVideoLayout;
	private TextView mStart;

	private int mSeekPosition;
	private int cachedHeight;
	private boolean isFullscreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		initView();
		setListener();
	}

	private void setListener() {
		mStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mSeekPosition > 0) {
					mVideoView.seekTo(mSeekPosition);
				}
				mVideoView.start();
				mMediaController.setTitle("Big Buck Bunny");
			}
		});

		mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				Log.d(TAG, "onCompletion ");
			}
		});
	}

	private void initView() {
		mVideoLayout = findViewById(R.id.video_layout);
		mBottomLayout = findViewById(R.id.bottom_layout);
		mVideoView = (UniversalVideoView) findViewById(R.id.videoView);
		mMediaController = (UniversalMediaController) findViewById(R.id.media_controller);
		mVideoView.setMediaController(mMediaController);
		setVideoAreaSize();
		mVideoView.setVideoViewCallback(this);
		mStart = (TextView) findViewById(R.id.start);
	}

	/**
	 * 置视频区域大小
	 */
	private void setVideoAreaSize() {
		mVideoLayout.post(new Runnable() {
			@Override
			public void run() {
				int width = mVideoLayout.getWidth();
//				cachedHeight = (int) (width * 405f / 720f);
				// cachedHeight = (int) (width * 3f / 4f);
				 cachedHeight = (int) (width * 9f / 16f);
				ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
				videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
				videoLayoutParams.height = cachedHeight;
				mVideoLayout.setLayoutParams(videoLayoutParams);
				mVideoView.setVideoPath(VIDEO_URL);
				mVideoView.requestFocus();
			}
		});
	}

	@Override
	public void onScaleChange(boolean isFullscreen) {
		// TODO Auto-generated method stub
		this.isFullscreen = isFullscreen;
		Log.d(TAG, "onScaleChange ");
	}

	@Override
	public void onPause(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onPause ");
	}

	@Override
	public void onStart(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStart ");

	}

	@Override
	public void onBufferingStart(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onBufferingStart ");

	}

	@Override
	public void onBufferingEnd(MediaPlayer mediaPlayer) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onBufferingEnd ");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause ");
		if (mVideoView != null && mVideoView.isPlaying()) {
			mSeekPosition = mVideoView.getCurrentPosition();
			Log.d(TAG, "onPause mSeekPosition=" + mSeekPosition);
			mVideoView.pause();
		}
	}

	@Override
	public void onBackPressed() {
		if (this.isFullscreen) {
			mVideoView.setFullscreen(false);
		} else {
			super.onBackPressed();
		}
	}

}
