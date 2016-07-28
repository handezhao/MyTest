package com.example.help;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.bean.Music;

public class MusicHelper {
	
	private static final String TAG = "MusicHelper";
	
	/**
	 * 扫描本地歌曲
	 */

	public static List<Music> scanMusic(Context context) {
		List<Music> list = new ArrayList<Music>();
		list.clear();
		Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, 
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		if (cursor == null) {
			return null;
		}
		while (cursor.moveToNext()) {
			int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
			if (isMusic == 0) {
				continue;
			}
		      long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
		        // 标题
		        String title = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
		        // 艺术家
		        String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
		        // 专辑
		        String album = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
		        // 持续时间
		        long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
		        // 音乐uri
		        String uri = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
		        // 专辑封面id，根据该id可以获得专辑图片uri
		        long albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
		        String coverUri = getCoverUri(context, albumId);
		        // 音乐文件名
		        String fileName = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
		        // 音乐文件大小
		        long fileSize = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
		        // 发行时间
		        String year = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.YEAR)));
		        Music music = new Music();
		        music.setAlbum(album);
		        music.setAlbumId(albumId);
		        music.setArtist(artist);
		        music.setCorverUri(coverUri);
		        music.setDuration(duration);
		        music.setFileName(fileName);
		        music.setFileSize(fileSize);
		        music.setTitle(title);
		        music.setUri(uri);
		        music.setYear(year);
			list.add(music);
		}
		cursor.close();
		return list;
	}
	
	/**
	 * 查询专辑封面图片uri
	 */
	private static String getCoverUri(Context context, long albumId) {
	    String uri = null;
	    Cursor cursor = context.getContentResolver().query(
	            Uri.parse("content://media/external/audio/albums/" + albumId),
	            new String[]{"album_art"}, null, null, null);
	    if (cursor != null) {
	        cursor.moveToNext();
	        uri = cursor.getString(0);
	        cursor.close();
	    }
	    return uri;
	}
}
