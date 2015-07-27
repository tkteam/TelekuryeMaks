package com.sefagurel.simplegps;

import android.app.Application;
import android.content.Context;

/**
 * Created by sefagurel on 24.07.2015.
 */
public class SimpleGPSApp extends Application {

	static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
	}

}
