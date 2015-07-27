package com.maks.telekurye.ui;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.sefagurel.simplegps.ISimpleGps;
import com.sefagurel.simplegps.SimpleGPS;

/**
 * Created by yunusemre on 22.07.2015.
 */
public abstract class FormBase extends ActivityBase implements ISimpleGps {

	ViewGroup		lytformbase_content;
	public boolean	initWidget;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			initWidget = true;
		}

		super.setContentView(R.layout.activity_formbase);
		lytformbase_content = (ViewGroup) findViewById(R.id.lytformbase_content);

	}

	public abstract void initWidgets();

	@Override
	public void setContentView(int layoutResID) {

		View view = getLayout(layoutResID);
		if (view != null && lytformbase_content != null) {
			lytformbase_content.addView(view);
		}
	}

	@Override
	public void setContentView(View view) {
		if (view != null && lytformbase_content != null) {
			lytformbase_content.addView(view);
		}
	}

	@Override
	public void onResume() {
		SimpleGPS.tracker().attach(this);
		super.onResume();
	}

	@Override
	public void onPause() {
		SimpleGPS.tracker().remove(this);
		super.onPause();
	}

	@Override
	public void locationChanged(LocationManager locationManager, Location location) {

	}
}
