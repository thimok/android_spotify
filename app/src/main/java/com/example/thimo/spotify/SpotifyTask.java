package com.example.thimo.spotify;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SpotifyTask extends AsyncTask<String, Void, String> {
	private SpotifyListener sl;
	
	public SpotifyTask(SpotifyListener sl) {
		this.sl = sl;
	}
	
	@Override
	protected String doInBackground(String... params) {
		InputStream inputStream = null;
		BufferedReader reader = null;
		String urlString = "";
		
		String response = "";
		
		try {
			URL url = new URL(params[0]);
			URLConnection connection = url.openConnection();
			
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			response = reader.readLine().toString();
			String line;
			while ((line = reader.readLine()) != null) {
				response += line;
			}
		} catch (MalformedURLException e) {
			Log.i("SpotifyTask", e.getLocalizedMessage());
			return null;
		} catch (IOException e) {
			Log.i("SpotifyTask", e.getLocalizedMessage());
			return null;
		} catch (Exception e) {
			Log.i("SpotifyTask", e.getLocalizedMessage());
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					Log.i("SpotifyTask", e.getLocalizedMessage());
					return null;
				}
			}
		}
		
		return response;
	}
	
	@Override
	protected void onPostExecute(String response) {
		Log.i("SpotifyTask", response);
		
		try {
			JSONObject jsonObject = new JSONObject(response);
			
			JSONArray albums = jsonObject.getJSONObject("albums").getJSONArray("items");
			
			for (int i = 0; i < albums.length(); i++) {
				SpotifyItem item = new SpotifyItem();
				
				String name = albums.getJSONObject(i).getString("name");
				String id = albums.getJSONObject(i).getString("id");
				
				item.setAlbumName(name);
				item.setSpotifyID(id);
				
				JSONArray images = albums.getJSONObject(i).getJSONArray("images");
				for (int j = 0; j < images.length(); j++) {
					int height = images.getJSONObject(j).getInt("height");
					if (height == 640) {
						String url = images.getJSONObject(j).getString("url");
						item.setImageUrl(url);
					} else if (height == 64) {
						String url = images.getJSONObject(j).getString("url");
						item.setImageThumbUrl(url);
					}
				}
				
				sl.onSpotifyItemAvailable(item);
			}
		} catch (JSONException e) {
			Log.e("JSON", e.getLocalizedMessage());
		}
	}
	
	public interface SpotifyListener {
		void onSpotifyItemAvailable(SpotifyItem item);
	}
}
