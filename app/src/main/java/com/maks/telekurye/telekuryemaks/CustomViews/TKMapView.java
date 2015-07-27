package com.maks.telekurye.telekuryemaks.CustomViews;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maks.telekurye.telekuryemaks.R;

import com.maks.telekurye.ui.FormBase;
import com.maks.telekurye.ui.ViewBase;

/**
 * Created by yunusemre on 24.07.2015.
 */
public class TKMapView extends ViewBase implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, LocationSource, LocationListener {

	protected static final String		TAG_ERROR_DIALOG_FRAGMENT	= "errorDialog";

	private OnLocationChangedListener	mapLocationListener			= null;
	private LocationManager				locMgr						= null;
	private Criteria					crit						= new Criteria();
	private GoogleMap					map							= null;

	public TKMapView(Context context) {
		super(context);

	}

	public void init() {
		if (readyToGo()) {
			View mapView = getLayout(R.layout.layout_tkmapview);
			if (mapView != null)
				addView(mapView);
		}

		MapFragment mapFragment = (MapFragment) ((Activity) activityContext).getFragmentManager().findFragmentById(R.id.mapAssigmentMission);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap map) {
		/*
		 * LatLng sydney = new LatLng(-33.867, 151.206);
		 *
		 * map.setMyLocationEnabled(true); map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
		 *
		 * map.addMarker(new MarkerOptions() .title("Sydney") .snippet("The most populous city in Australia.") .position(sydney));
		 */

		this.map = map;

		if (((FormBase) activityContext).initWidget) {
			CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(40.76793169992044, -73.98180484771729));
			CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

			map.moveCamera(center);
			map.animateCamera(zoom);
		}

		addMarker(map, 40.748963847316034, -73.96807193756104, R.string.un, R.string.united_nations);
		addMarker(map, 40.76866299974387, -73.98268461227417, R.string.lincoln_center, R.string.lincoln_center_snippet);
		addMarker(map, 40.765136435316755, -73.97989511489868, R.string.carnegie_hall, R.string.practice_x3);
		addMarker(map, 40.70686417491799, -74.01572942733765, R.string.downtown_club, R.string.heisman_trophy);

		// map.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));
		map.setOnInfoWindowClickListener(this);

		locMgr = (LocationManager) ((Activity) activityContext).getSystemService(Activity.LOCATION_SERVICE);
		crit.setAccuracy(Criteria.ACCURACY_FINE);
		locMgr.requestLocationUpdates(0L, 0.0f, crit, this, null);

		map.setLocationSource(this);
		map.setMyLocationEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(false);

	}

	public void onResume() {
		if (locMgr != null) {
			locMgr.requestLocationUpdates(0L, 0.0f, crit, this, null);
		}

		if (map != null) {
			map.setLocationSource(this);
		}
	}

	public void onPause() {
		map.setLocationSource(null);
		locMgr.removeUpdates(this);
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		Toast.makeText(activityContext, marker.getTitle(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void activate(OnLocationChangedListener listener) {
		this.mapLocationListener = listener;
	}

	@Override
	public void deactivate() {
		this.mapLocationListener = null;
	}

	@Override
	public void onLocationChanged(Location location) {
		if (mapLocationListener != null) {
			mapLocationListener.onLocationChanged(location);

			LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
			CameraUpdate cu = CameraUpdateFactory.newLatLng(latlng);

			map.animateCamera(cu);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// unused
	}

	@Override
	public void onProviderEnabled(String provider) {
		// unused
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// unused
	}

	private void addMarker(GoogleMap map, double lat, double lon, int title, int snippet) {
		map.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title(activityContext.getString(title)).snippet(activityContext.getString(snippet)));
	}

	protected boolean readyToGo() {
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(((Activity) activityContext));

		if (status == ConnectionResult.SUCCESS) {
			if (getVersionFromPackageManager(((Activity) activityContext)) >= 2) {
				return (true);
			}
			else {
				Toast.makeText(((Activity) activityContext), com.maks.telekurye.ui.R.string.no_maps, Toast.LENGTH_LONG).show();
				((Activity) activityContext).finish();
			}
		}
		else if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
			ErrorDialogFragment.newInstance(status).show(((Activity) activityContext).getFragmentManager(), TAG_ERROR_DIALOG_FRAGMENT);
		}
		else {
			Toast.makeText(((Activity) activityContext), com.maks.telekurye.ui.R.string.no_maps, Toast.LENGTH_LONG).show();
			((Activity) activityContext).finish();
		}

		return (false);
	}

	public static class ErrorDialogFragment extends DialogFragment {
		static final String	ARG_STATUS	= "status";

		static ErrorDialogFragment newInstance(int status) {
			Bundle args = new Bundle();

			args.putInt(ARG_STATUS, status);

			ErrorDialogFragment result = new ErrorDialogFragment();

			result.setArguments(args);

			return (result);
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Bundle args = getArguments();

			return GooglePlayServicesUtil.getErrorDialog(args.getInt(ARG_STATUS), getActivity(), 0);
		}

		@Override
		public void onDismiss(DialogInterface dlg) {
			if (getActivity() != null) {
				getActivity().finish();
			}
		}
	}

	// following from
	// https://android.googlesource.com/platform/cts/+/master/tests/tests/graphics/src/android/opengl/cts/OpenGlEsVersionTest.java

	/*
	 * Copyright (C) 2010 The Android Open Source Project
	 *
	 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
	 *
	 * http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
	 * express or implied. See the License for the specific language governing permissions and limitations under the License.
	 */

	private static int getVersionFromPackageManager(Context context) {
		PackageManager packageManager = context.getPackageManager();
		FeatureInfo[] featureInfos = packageManager.getSystemAvailableFeatures();
		if (featureInfos != null && featureInfos.length > 0) {
			for (FeatureInfo featureInfo : featureInfos) {
				// Null feature name means this feature is the open
				// gl es version feature.
				if (featureInfo.name == null) {
					if (featureInfo.reqGlEsVersion != FeatureInfo.GL_ES_VERSION_UNDEFINED) {
						return getMajorVersion(featureInfo.reqGlEsVersion);
					}
					else {
						return 1; // Lack of property means OpenGL ES
						// version 1
					}
				}
			}
		}
		return 1;
	}

	/** @see FeatureInfo#getGlEsVersion() */
	private static int getMajorVersion(int glEsVersion) {
		return ((glEsVersion & 0xffff0000) >> 16);
	}

}
