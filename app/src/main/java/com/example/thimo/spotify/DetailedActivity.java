package com.example.thimo.spotify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {
	//private TextView albumName, spotifyID, imageUrl, imageThumbUrl;
	private ImageView albumCover;
	private SpotifyItem item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed);
		
		//albumName = (TextView) findViewById(R.id.albumNameDetailedTV);
		//spotifyID = (TextView) findViewById(R.id.spotifyIdDetailedTV);
		//imageUrl = (TextView) findViewById(R.id.imageUrlDetailedTV);
		//imageThumbUrl = (TextView) findViewById(R.id.imageThumbUrlDetailedTV);
		albumCover = (ImageView) findViewById(R.id.albumCoverDetailedIV); 
		
		Bundle extras = getIntent().getExtras();
		
		item = (SpotifyItem) extras.getSerializable("item");
		
		Picasso.with(getApplicationContext()).load(item.getImageUrl()).into(albumCover);
		
		//albumName.setText(item.getAlbumName());
		//spotifyID.setText(item.getSpotifyID());
		//imageUrl.setText(item.getImageUrl());
		//imageThumbUrl.setText(item.getImageThumbUrl());
	}
}
