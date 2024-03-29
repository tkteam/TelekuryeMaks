package com.maks.telekurye.telekuryemaks.Adapters;

/**
 * Created by yunusemre on 23.07.2015.
 */
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.maks.telekurye.telekuryemaks.R;

class PopupAdapter implements InfoWindowAdapter {
	private View			popup		= null;
	private LayoutInflater	inflater	= null;

	PopupAdapter(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		return (null);
	}

	@SuppressLint("InflateParams")
	@Override
	public View getInfoContents(Marker marker) {
		if (popup == null) {
			popup = inflater.inflate(R.layout.layout_mappopup, null);
		}

		TextView tv = (TextView) popup.findViewById(R.id.title);

		tv.setText(marker.getTitle());
		tv = (TextView) popup.findViewById(R.id.snippet);
		tv.setText(marker.getSnippet());

		return (popup);
	}
}