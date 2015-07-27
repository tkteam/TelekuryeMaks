package com.maks.telekurye.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.logging.Handler;

/**
 * Created by yunusemre on 22.07.2015.
 */
public class ActivityBase extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);

	}

	@Override
	public void onResume() {
		super.onResume();
		if (Build.VERSION.SDK_INT < 16) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		else {
			View decorView = getWindow().getDecorView();
			// Hide the status bar.
			int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
			decorView.setSystemUiVisibility(uiOptions);
			// Remember that you should never show the action bar if the
			// status bar is hidden, so hide that too if necessary.
			ActionBar actionBar = getActionBar();
			if (actionBar != null)
				actionBar.hide();
		}
	}

	public View getLayout(int resId) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(resId, null);
		return view;
	}

	public void showToastMessage(String msg) {
		Context appContext = getApplicationContext();
		Toast.makeText(appContext, msg, Toast.LENGTH_SHORT).show();
	}

	public void showToastMessage(String msg, int duration) {
		Context appContext = getApplicationContext();
		if (appContext == null)
			return;

		final Toast toast = Toast.makeText(appContext, msg, Toast.LENGTH_LONG);
		toast.show();
		android.os.Handler handler = new android.os.Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				toast.cancel();
			}
		}, duration);
	}

}
