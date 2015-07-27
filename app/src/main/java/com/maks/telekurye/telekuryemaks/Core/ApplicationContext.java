package com.maks.telekurye.telekuryemaks.Core;

import android.content.SharedPreferences;

/**
 * Created by yunusemre on 23.07.2015.
 */
public class ApplicationContext {

	private static ApplicationContext	instance;

	public static ApplicationContext getInstance() {
		if (instance == null) {
			instance = new ApplicationContext();
		}

		return instance;
	}

	private ApplicationContext() {
	} // Hide Constructor Because Singleton Class

	public String getPref(String key) {
		return TelekuryeMaksApplication.getSharedPreferences().getString(key, null);
	}

	public void setPref(String key, String value) {
		SharedPreferences.Editor editor = TelekuryeMaksApplication.getSharedPreferences().edit();
		editor.putString(key, value);
		editor.commit();
	}

}
