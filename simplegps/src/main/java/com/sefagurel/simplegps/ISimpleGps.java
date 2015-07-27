package com.sefagurel.simplegps;

import android.location.Location;
import android.location.LocationManager;

/**
 * Created by sefagurel on 24.07.2015.
 */
public interface ISimpleGps {

	void locationChanged(LocationManager locationManager, Location location);

}
