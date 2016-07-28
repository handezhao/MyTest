package com.example.bean;

public class Music {
	
	
	/**
	 * 标题
	 */
	String title;
	
	/**
	 * 艺术家
	 */
	String artist;
	
	/**
	 * 专辑
	 */
	String album;
	
	/**
	 * 持续时长
	 */
	long duration;
	
	/**
	 * 音乐uri
	 */
	String uri;

	/**
	 * 专辑ID，可以据此获得专辑图片uri
	 */
	long albumId;
	
	/**
	 * 专辑图片uri
	 */
	String corverUri;
	
	/**
	 * 音乐文件名
	 */
	String fileName;
	
	/**
	 * 文件大小
	 */
	long fileSize;
	
	/**
	 * 发行时间
	 */
	String year;
	

	public Music() {
		super();
	}
	

	public Music(String title, String artist, String album, long duration,
			String uri, long albumId, String corverUri, String fileName,
			long fileSize, String year) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.duration = duration;
		this.uri = uri;
		this.albumId = albumId;
		this.corverUri = corverUri;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public String getCorverUri() {
		return corverUri;
	}

	public void setCorverUri(String corverUri) {
		this.corverUri = corverUri;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}
