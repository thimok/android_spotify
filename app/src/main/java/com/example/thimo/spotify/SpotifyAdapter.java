package com.example.thimo.spotify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SpotifyAdapter extends ArrayAdapter<SpotifyItem> {
	
	public SpotifyAdapter(Context context, ArrayList<SpotifyItem> items) {
		super(context, 0, items);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SpotifyItem item = getItem(position);
		
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.spotify_row, parent, false);
		}
		
		TextView albumName = (TextView) convertView.findViewById(R.id.albumTitleTV);
		albumName.setText(item.getAlbumName());
		
		TextView spotifyID = (TextView) convertView.findViewById(R.id.spotifyIDTV);
		spotifyID.setText(item.getSpotifyID());
		
		ImageView albumCover = (ImageView) convertView.findViewById(R.id.albumCoverIV);
		Picasso.with(getContext()).load(item.getImageThumbUrl()).into(albumCover);
		
		return convertView;
	}
}
