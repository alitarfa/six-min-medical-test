package com.detection.object.tarfa.medical.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.detection.object.tarfa.medical.R;

/**
 * Created by tarfa on 6/10/18.
 */

public class FragmentAboutUs extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.about_us_fragment,container,false);
        return view;
    }
}
