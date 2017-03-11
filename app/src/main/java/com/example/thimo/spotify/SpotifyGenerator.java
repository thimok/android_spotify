package com.example.thimo.spotify;

import android.util.Log;

import java.util.ArrayList;

public class SpotifyGenerator {
	private ArrayList<SpotifyItem> items;
	private MainActivity activity;
	
	public SpotifyGenerator(MainActivity activity) {
		items = new ArrayList<>();
		this.activity = activity;
	}
	
	public ArrayList<SpotifyItem> getItems() {
		return items;
	}
	
	public void addItem(SpotifyItem item) {
		items.add(item);
	}
	
	public void generate(String query) {
		items.clear();
		Log.i("SpotifyGenerator", query);
		SpotifyTask task = new SpotifyTask(activity);
		String[] urls = new String[] {"https://api.spotify.com/v1/search?query=" + query + "&type=album"};
		task.execute(urls);
	}
}
