package com.example.thimo.spotify;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SpotifyTask.SpotifyListener {
	
	private EditText artistInput;
	private ImageButton searchButton;
	private ListView albumsListView;
	private ArrayAdapter arrayAdapter;
	private SpotifyGenerator generator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		generator = new SpotifyGenerator(this);
		
		artistInput = (EditText) findViewById(R.id.aristSearchET);
		artistInput.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
						(keyCode == KeyEvent.KEYCODE_ENTER)) {
					submit();
					return true;
				}
				return false;
			}
		});
		
		searchButton = (ImageButton) findViewById(R.id.searchBTN);
		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				submit();
			}
		});
		
		albumsListView = (ListView) findViewById(R.id.albumsListView);
		
		arrayAdapter = new SpotifyAdapter(this, generator.getItems());
		
		albumsListView.setAdapter(arrayAdapter);
		albumsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent(getApplicationContext(), DetailedActivity.class);
				i.putExtra("item", generator.getItems().get(position));
				startActivity(i);
			}
		});
	}
	
	public void submit() {
		InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromInputMethod(getWindow().getDecorView().getRootView().getWindowToken(), 0);
		String searchQuery = artistInput.getText().toString();
		searchQuery = searchQuery.replaceAll(" ", "+");
		generator.generate(searchQuery);
	}
	
//	public void getSpotifyItems() {
//		SpotifyTask task = new SpotifyTask(this);
//		String[] urls = new String[] {"https://api.spotify.com/v1/search?query=lady+gaga&type=album"};
//		task.execute(urls);
//	}
//	
//	public void getSpotifyItems(String artist) {
//		SpotifyTask task = new SpotifyTask(this);
//		String[] urls = new String[] {"https://api.spotify.com/v1/search?query=" + artist + "&type=album"};
//		task.execute(urls);
//	}
	
	@Override
	public void onSpotifyItemAvailable(SpotifyItem item) {
		Log.i("MainActivity", item.getAlbumName());
		generator.addItem(item);
		arrayAdapter.notifyDataSetChanged();
	}
}
