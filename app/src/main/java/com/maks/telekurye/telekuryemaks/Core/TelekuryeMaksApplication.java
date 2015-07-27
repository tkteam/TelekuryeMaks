package com.maks.telekurye.telekuryemaks.Core;

import android.content.SharedPreferences;
import android.content.res.Configuration;

import com.sefagurel.simplegps.SimpleGPSApp;

/**
 * Created by yunusemre on 23.07.2015.
 */
public class TelekuryeMaksApplication extends SimpleGPSApp {

	private static SharedPreferences	sharedPreferences;

	public static SharedPreferences getSharedPreferences() {
		return sharedPreferences;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		ApplicationContext.getInstance();
		sharedPreferences = getSharedPreferences(Const.PREFS_NAME, 0);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

}
