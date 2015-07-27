package com.maks.telekurye.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunusemre on 24.07.2015.
 */
public class WizardBase extends FormBase {

	Button				btnWizardBackward;
	Button				btnWizardForward;
	RelativeLayout		lytFragmentPlace;
	List<FragmentBase>	FragmentList	= new ArrayList<FragmentBase>();
	int					currentFragment	= 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_wizardbase);

		if (initWidget) {
			lytFragmentPlace = (RelativeLayout) findViewById(R.id.lytFragmentPlace);

			btnWizardBackward = (Button) findViewById(R.id.btnWizardBackward);
			btnWizardForward = (Button) findViewById(R.id.btnWizardForward);

			btnWizardBackward.setOnClickListener(btnWizardBackwardListener);
			btnWizardForward.setOnClickListener(btnWizardForwardListener);

		}
	}

	public void addFragment(FragmentBase fragmentBase) {
		FragmentList.add(fragmentBase);
	}

	public void WizardInit() {

		btnWizardBackward.setEnabled(false);
		btnWizardForward.setEnabled(true);

		FragmentBase fr = FragmentList.get(0);

		FragmentManager fm = getFragmentManager();

		FragmentTransaction fragmentTransaction = fm.beginTransaction();

		fragmentTransaction.add(R.id.lytFragmentPlace, fr);

		fragmentTransaction.commit();

	}

	void changeFragment(int val) {
		FragmentBase fr = FragmentList.get(val);

		FragmentManager fm = getFragmentManager();

		FragmentTransaction fragmentTransaction = fm.beginTransaction();

		fragmentTransaction.replace(R.id.lytFragmentPlace, fr);

		fragmentTransaction.commit();
	}

	@Override
	public void initWidgets() {

	}

	View.OnClickListener	btnWizardBackwardListener	= new View.OnClickListener() {
															@Override
															public void onClick(View v) {
																currentFragment--;

																if (currentFragment > 0) {
																	btnWizardBackward.setEnabled(true);
																}
																else {
																	btnWizardBackward.setEnabled(false);
																}
																btnWizardForward.setEnabled(true);
																changeFragment(currentFragment);
															}
														};

	View.OnClickListener	btnWizardForwardListener	= new View.OnClickListener() {
															@Override
															public void onClick(View v) {
																currentFragment++;
																if (currentFragment >= FragmentList.size() - 1) {

																	btnWizardForward.setEnabled(false);
																}
																else {
																	btnWizardForward.setEnabled(true);
																}
																btnWizardBackward.setEnabled(true);
																changeFragment(currentFragment);
															}
														};

	@Override
	public void setContentView(int resLayoutId) {

	}

	@Override
	public void setContentView(View view) {

	}

}
