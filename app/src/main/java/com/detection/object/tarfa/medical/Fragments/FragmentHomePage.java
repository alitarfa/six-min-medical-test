package com.detection.object.tarfa.medical.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.detection.object.tarfa.medical.ConsultationPage;
import com.detection.object.tarfa.medical.NewTestPage;
import com.detection.object.tarfa.medical.R;

/**
 * Created by tarfa on 6/10/18.
 */

public class FragmentHomePage extends Fragment {

    CardView newTestBtn;
    CardView consultationBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_fragment,container,false);
        newTestBtn=view.findViewById(R.id.new_test_btn);
        consultationBtn=view.findViewById(R.id.consultation_btn);

        newTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), NewTestPage.class));
            }
        });


        consultationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ConsultationPage.class));
            }
        });

        return view;
    }
}
