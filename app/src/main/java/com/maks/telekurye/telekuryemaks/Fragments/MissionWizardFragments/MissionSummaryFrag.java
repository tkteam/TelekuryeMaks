package com.maks.telekurye.telekuryemaks.Fragments.MissionWizardFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maks.telekurye.telekuryemaks.R;
import com.maks.telekurye.ui.FragmentBase;

/**
 * Created by yunusemre on 24.07.2015.
 */
public class MissionSummaryFrag extends FragmentBase {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_missionsummary, container, false);
    }

}
