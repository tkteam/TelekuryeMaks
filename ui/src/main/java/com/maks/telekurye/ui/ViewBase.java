package com.maks.telekurye.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yunusemre on 24.07.2015.
 */
public class ViewBase extends ViewGroup {

	public Context	activityContext;

	public ViewBase(Context context) {
		super(context);
		activityContext = context;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// super.onLayout(changed,l,t,r,b);
	}

	public View getLayout(int resId) {
		LayoutInflater inflater = (LayoutInflater) activityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(resId, null);
		return view;
	}
}
