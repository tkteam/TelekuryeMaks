package com.sefagurel.simplegps;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by sefagurel on 25.07.2015.
 */
public class SimpleGPSActivity extends Activity implements ISimpleGps {

	@Override
	protected void onResume() {
		super.onResume();
		SimpleGPS.tracker().attach(this);

	}

	@Override
	protected void onPause() {
		super.onPause();
		SimpleGPS.tracker().remove(this);
	}

	@Override
	public void locationChanged(LocationManager locationManager, Location location) {

	}
}
