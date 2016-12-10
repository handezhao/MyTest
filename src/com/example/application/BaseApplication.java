package com.example.application;

import android.app.Application;

public class BaseApplication extends Application {
	
	private static final String TAG = "BaseApplication";

	private static BaseApplication instance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = BaseApplication.this;
//		this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
//			
//			@Override
//			public void onActivityStopped(Activity arg0) {
//			}
//			
//			@Override
//			public void onActivityStarted(Activity arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onActivitySaveInstanceState(Activity arg0, Bundle arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onActivityResumed(Activity arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onActivityPaused(Activity arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onActivityDestroyed(Activity arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onActivityCreated(Activity activity, Bundle arg1) {
//				Log.d(TAG, "onActivityCreated");
//				activity.getWindow().setCallback(new Window.Callback() {
//					
//					@Override
//					public ActionMode onWindowStartingActionMode(Callback arg0) {
//						// TODO Auto-generated method stub
//						return null;
//					}
//					
//					@Override
//					public void onWindowFocusChanged(boolean arg0) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public void onWindowAttributesChanged(LayoutParams arg0) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public boolean onSearchRequested() {
//						// TODO Auto-generated method stub
//						return false;
//					}
//					
//					@Override
//					public boolean onPreparePanel(int arg0, View arg1, Menu arg2) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//					
//					@Override
//					public void onPanelClosed(int arg0, Menu arg1) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public boolean onMenuOpened(int arg0, Menu arg1) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//					
//					@Override
//					public boolean onMenuItemSelected(int arg0, MenuItem arg1) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//					
//					@Override
//					public void onDetachedFromWindow() {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public View onCreatePanelView(int arg0) {
//						// TODO Auto-generated method stub
//						return null;
//					}
//					
//					@Override
//					public boolean onCreatePanelMenu(int arg0, Menu arg1) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//					
//					@Override
//					public void onContentChanged() {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public void onAttachedToWindow() {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public void onActionModeStarted(ActionMode arg0) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public void onActionModeFinished(ActionMode arg0) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public boolean dispatchTrackballEvent(MotionEvent arg0) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//					
//					@Override
//					public boolean dispatchTouchEvent(MotionEvent arg0) {
//						Log.d(TAG, "event is "  + arg0);
//						return false;
//					}
//					
//					@Override
//					public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg0) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//					
//					@Override
//					public boolean dispatchKeyShortcutEvent(KeyEvent arg0) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//					
//					@Override
//					public boolean dispatchKeyEvent(KeyEvent arg0) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//					
//					@Override
//					public boolean dispatchGenericMotionEvent(MotionEvent arg0) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//				});
//				
//			}
//		});
//		startService(new Intent(getInstance(), LockService.class));
	}

	public static BaseApplication getInstance() {
		return instance;
	}


}
