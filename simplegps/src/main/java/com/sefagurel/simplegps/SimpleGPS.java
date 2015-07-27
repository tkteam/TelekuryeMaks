package com.sefagurel.simplegps;

import android.content.Context;
import android.content.Intent;

/**
 * Created by sefagurel on 25.07.2015.
 */
public class SimpleGPS {

	public static boolean	isGPSEnabled				= true;
	public static boolean	isNetworkEnabled			= false;
	public static long		minDistanceChangeForUpdates	= 100;				// meters
	public static long		minTimeForUpdates			= 1000 * 60 * 1;	// minutes

	// ********

	private static SimpleGPS	simpleGps	= null;
	private GPSService			gpsService	= null;
	private Context				ctx			= null;

	private SimpleGPS() {
		ctx = SimpleGPSApp.context;
		gpsService = new GPSService();
	}

	public static SimpleGPS tracker() {
		if (simpleGps == null) {
			simpleGps = new SimpleGPS();
		}
		return simpleGps;
	}

	public void attach(ISimpleGps igps) {
		gpsService.addClient(igps);
	}

	public void remove(ISimpleGps igps) {
		gpsService.removeClient(igps);
	}

	public void start() {
		ctx.startService(new Intent(ctx, GPSService.class));
	}

	public void stop() {
		ctx.stopService(new Intent(ctx, GPSService.class));
		gpsService.stopGPS();
	}

}
