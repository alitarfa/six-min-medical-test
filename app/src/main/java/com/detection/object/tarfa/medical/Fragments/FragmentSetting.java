package com.detection.object.tarfa.medical.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.detection.object.tarfa.medical.R;

/**
 * Created by tarfa on 6/10/18.
 */

public class FragmentSetting extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.setting_fragment,container,false);
        EditText editText=view.findViewById(R.id.step);
        Button button=view.findViewById(R.id.change_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=editText.getText().toString();
                if (!value.equals("")){
                    int v =Integer.parseInt(value);
                    getContext().getSharedPreferences("setting", Context.MODE_PRIVATE).edit().putInt("step",v).apply();
                    Toast.makeText(getContext(), "value changed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}
