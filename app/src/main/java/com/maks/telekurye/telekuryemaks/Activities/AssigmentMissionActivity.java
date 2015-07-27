package com.maks.telekurye.telekuryemaks.Activities;

import android.app.Fragment;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maks.telekurye.telekuryemaks.CustomViews.TKMapView;
import com.maks.telekurye.telekuryemaks.R;
import com.maks.telekurye.ui.AbstractMapActivity;
import com.maks.telekurye.ui.FormBase;

/**
 * Created by yunusemre on 23.07.2015.
 */
public class AssigmentMissionActivity extends FormBase {

	// region Widgets
	Button			btnGiveMission;
	LinearLayout	lytAssigmentMap;
	TKMapView		tkMapView;

	// endregion

	// region Constructor
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_assigmentmission);
		lytAssigmentMap = (LinearLayout) findViewById(R.id.lytAssigmentMap);
		tkMapView = new TKMapView(AssigmentMissionActivity.this);
		lytAssigmentMap.addView(tkMapView);
		tkMapView.init();
		if (initWidget) {
			initWidgets();
		}

	}

	@Override
	public void initWidgets() {

		btnGiveMission = (Button) findViewById(R.id.btnGiveMission);
		btnGiveMission.setOnClickListener(btnGiveMissionlistener);
	}

	// endregion

	// region Listener
	View.OnClickListener	btnGiveMissionlistener	= new View.OnClickListener() {
														@Override
														public void onClick(View v) {
															if (true) {
																Intent intent = new Intent(AssigmentMissionActivity.this, MissionWizardActivity.class);
																startActivity(intent);
															}
														}
													};

	// endregion

	@Override
	public void onResume() {
		if (tkMapView != null)
			tkMapView.onResume();
		super.onResume();
	}

	@Override
	public void onPause() {
		if (tkMapView != null)
			tkMapView.onPause();
		super.onPause();
	}

}
