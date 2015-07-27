package com.maks.telekurye.telekuryemaks.Activities;

import android.os.Bundle;

import com.maks.telekurye.telekuryemaks.Fragments.MissionWizardFragments.MissionExplanationFrag;
import com.maks.telekurye.telekuryemaks.Fragments.MissionWizardFragments.MissionSummaryFrag;
import com.maks.telekurye.telekuryemaks.Fragments.MissionWizardFragments.SelectShapeFrag;
import com.maks.telekurye.telekuryemaks.Fragments.MissionWizardFragments.TakeImageFrag;
import com.maks.telekurye.ui.WizardBase;

/**
 * Created by yunusemre on 24.07.2015.
 */
public class MissionWizardActivity extends WizardBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (initWidget) {
			initWidgets();
		}

	}

	@Override
	public void initWidgets() {

		addFragment(new MissionExplanationFrag());
		addFragment(new SelectShapeFrag());
		addFragment(new TakeImageFrag());
		addFragment(new MissionSummaryFrag());
		WizardInit();

	}

}
