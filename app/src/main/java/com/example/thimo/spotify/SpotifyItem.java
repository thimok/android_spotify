package com.example.thimo.spotify;

import java.io.Serializable;

public class SpotifyItem implements Serializable {
	private String albumName;
	private String spotifyID;
	private String imageUrl;
	private String imageThumbUrl;
	
	public String getAlbumName() {
		return albumName;
	}
	
	public String getSpotifyID() {
		return spotifyID;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public String getImageThumbUrl() {
		return imageThumbUrl;
	}
	
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public void setSpotifyID(String spotifyID) {
		this.spotifyID = spotifyID;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public void setImageThumbUrl(String imageThumbUrl) {
		this.imageThumbUrl = imageThumbUrl;
	}
	
	@Override
	public String toString() {
		return "SpotifyItem{" +
				"AlbumName='" + albumName + '\'' +
				'}';
	}
}
