package com.maks.telekurye.telekuryemaks.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.maks.telekurye.telekuryemaks.R;
import com.maks.telekurye.ui.FormBase;

/**
 * Created by yunusemre on 23.07.2015.
 */
public class SettingsActivity extends FormBase {

	// region Widgets
	Button	btnInternetTest;
	Button	btnTelekuryeTest;
	Button	btnSendData;
	Button	btnSendState;

	// endregion

	// region Constructor
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		if (initWidget) {
			initWidgets();
		}

	}

	@Override
	public void initWidgets() {
		btnInternetTest = (Button) findViewById(R.id.btnInternetTest);
		btnTelekuryeTest = (Button) findViewById(R.id.btnTelekuryeTest);
		btnSendData = (Button) findViewById(R.id.btnSendData);
		btnSendState = (Button) findViewById(R.id.btnSendState);

		btnInternetTest.setOnClickListener(btnInternetTestListener);
		btnTelekuryeTest.setOnClickListener(btnTelekuryeTestListener);
		btnSendData.setOnClickListener(btnSendDataListener);
		btnSendState.setOnClickListener(btnSendStateListener);

	}

	// endregion

	// region Listeners

	View.OnClickListener	btnInternetTestListener		= new View.OnClickListener() {
															@Override
															public void onClick(View v) {

															}
														};

	View.OnClickListener	btnTelekuryeTestListener	= new View.OnClickListener() {
															@Override
															public void onClick(View v) {

															}
														};

	View.OnClickListener	btnSendDataListener			= new View.OnClickListener() {
															@Override
															public void onClick(View v) {

															}
														};

	View.OnClickListener	btnSendStateListener		= new View.OnClickListener() {
															@Override
															public void onClick(View v) {

															}
														};

	// endregion
}
