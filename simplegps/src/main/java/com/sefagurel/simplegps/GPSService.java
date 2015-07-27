package com.sefagurel.simplegps;

import java.util.HashSet;
import java.util.Set;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

class GPSService extends Service implements LocationListener, GpsStatus.Listener {

	private LocationManager	locationManager;
	private Set<ISimpleGps>	setGPS	= new HashSet<>();
	private Context			mContext;

	public GPSService() {
		mContext = SimpleGPSApp.context;
		setGPSSettings();
	}

	public void addClient(ISimpleGps igps) {
		setGPS.add(igps);
	}

	public void removeClient(ISimpleGps igps) {
		setGPS.remove(igps);
	}

	public void stopGPS() {
		setGPS.clear();
		locationManager.removeGpsStatusListener(this);
		locationManager.removeUpdates(this);
		this.onDestroy();
	}

	@Override
	public void onLocationChanged(Location location) {
		for (ISimpleGps igps1 : setGPS) {
			igps1.locationChanged(locationManager, location);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onGpsStatusChanged(int event) {
		// switch (event) {
		// case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
		// if (mLastLocation != null)
		// isGPSFix = (SystemClock.elapsedRealtime() - mLastLocationMillis) < 3000;
		//
		// if (isGPSFix) { // A fix has been acquired.
		//
		// }
		// else {
		// UserAccuracy = 5000;
		// }
		//
		// break;
		// }
		//
		// isGPSFix = true;
	}

	public boolean setGPSSettings() {

		try {

			locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
			locationManager.addGpsStatusListener(this);

			if (!SimpleGPS.isGPSEnabled && !SimpleGPS.isNetworkEnabled) {
				return false;
			}

			if (SimpleGPS.isNetworkEnabled) {

				boolean isEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

				if (isEnabled) {
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, SimpleGPS.minTimeForUpdates, SimpleGPS.minDistanceChangeForUpdates, this);
				}

			}

			if (SimpleGPS.isGPSEnabled) {

				boolean isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

				if (isEnabled) {
					locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, SimpleGPS.minTimeForUpdates, SimpleGPS.minDistanceChangeForUpdates, this);
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
