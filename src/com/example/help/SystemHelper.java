package com.example.help;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StatFs;

public class SystemHelper {

	public static boolean isScreenOn(Context context) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		return pm.isScreenOn();
	}

	public static long getSystemAvailableMemory(Context context) {
		try {
			ActivityManager activityManager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
			ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
			activityManager.getMemoryInfo(memoryInfo);
			return memoryInfo.availMem;
		} catch (Exception e) {
		}

		return 0;
	}

	public static int getMemorySize(Context context) {
		try {
			ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			return activityManager.getMemoryClass();
		} catch (Exception e) {
		}

		return 0;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static int getLargeMemorySize(Context context) {
		try {
			ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				return activityManager.getLargeMemoryClass();
			}
		} catch (Exception e) {
		}

		return 0;
	}

	/**
	 * SDCARD是否存
	 */
	public static boolean externalMemoryAvailable() {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	private static DecimalFormat fileIntegerFormat = new DecimalFormat("#0");
	private static DecimalFormat fileDecimalFormat = new DecimalFormat("#0.#");

	/**
	 * 单位换算
	 * 
	 * @param size
	 *            单位为B
	 * @param isInteger
	 *            是否返回取整的单位
	 * @return 转换后的单位
	 */
	public static String formatFileSize(long size, boolean isInteger) {
		DecimalFormat df = isInteger ? fileIntegerFormat : fileDecimalFormat;
		String fileSizeString = "0M";
		if (size < 1024 && size > 0) {
			fileSizeString = df.format((double) size) + "B";
		} else if (size < 1024 * 1024) {
			fileSizeString = df.format((double) size / 1024) + "K";
		} else if (size < 1024 * 1024 * 1024) {
			fileSizeString = df.format((double) size / (1024 * 1024)) + "M";
		} else {
			fileSizeString = df.format((double) size / (1024 * 1024 * 1024)) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 获取SDCARD总的存储空间
	 * 
	 * @return
	 */
	public static String getTotalExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return formatFileSize(totalBlocks * blockSize, false);
		} else {
			return "0";
		}
	}

	/**
	 * 获取SDCARD剩余存储空间
	 * 
	 * @return
	 */
	public static String getAvailableExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return formatFileSize(availableBlocks * blockSize, false);
		} else {
			return "0";
		}
	}

	/**
	 * 获取手机内部总的存储空间
	 * 
	 * @return
	 */
	public static String getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return formatFileSize(totalBlocks * blockSize, false);
	}

	/**
	 * 获取手机内部剩余存储空间
	 * 
	 * @return
	 */
	public static String getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return formatFileSize(availableBlocks * blockSize, false);
	}

	/**
	 * 获取系统总内存
	 * 
	 * @param context
	 *            可传入应用程序上下文。
	 * @return 总内存大单位为B。
	 */
	public static String getTotalMemorySize(Context context) {
		String dir = "/proc/meminfo";
		try {
			FileReader fr = new FileReader(dir);
			BufferedReader br = new BufferedReader(fr, 2048);
			String memoryLine = br.readLine();
			String subMemoryLine = memoryLine.substring(memoryLine.indexOf("MemTotal:"));
			br.close();
			return formatFileSize(Integer.parseInt(subMemoryLine.replaceAll("\\D+", "")) * 1024l, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "0";
	}

	/**
	 * 获取当前可用内存，返回数据以字节为单位。
	 * 
	 * @param context
	 *            可传入应用程序上下文。
	 * @return 当前可用内存单位为B。
	 */
	public static String getAvailableMemory(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
		am.getMemoryInfo(memoryInfo);
		return formatFileSize(memoryInfo.availMem, false);
	}

	/**
	 * 获取设备是否支持多点触屏
	 * 
	 * @param context
	 *            可传入应用程序上下文。
	 * @return 是否支持
	 * @exception FEATURE_TOUCHSCREEN_MULTITOUCH表示
	 *                :该设备的触摸屏支持多点触控足够的基本两个手指的手势检测。
	 *                FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT表示
	 *                :该设备的触摸屏是能够跟踪两个或两个以上的手指完全独立。
	 *                FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND表示
	 *                :设备的触摸屏是能够跟踪满手的手指完全独立的 - 这是5或更多的同步独立的指针。
	 */
	public static boolean isSupportMultiTouch(Context context) {
		PackageManager pm = context.getPackageManager();
		boolean isSupportMultiTouch = pm.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH);
		return isSupportMultiTouch;
	}

}
