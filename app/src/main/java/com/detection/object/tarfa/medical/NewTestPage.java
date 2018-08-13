package com.detection.object.tarfa.medical;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.detection.object.tarfa.medical.Fragments.FragmentStartTest;
import com.detection.object.tarfa.medical.database.DataBase;

public class NewTestPage extends AppCompatActivity {

    Button startTestBtn;
    DataBase dataBase;
    EditText name,family,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test_page);
        Toolbar toolbar=findViewById(R.id.toolbar);

        /**
         * data base
         */

        dataBase=new DataBase(this);

         name =findViewById(R.id.name);
         family=findViewById(R.id.family_name);
         number=findViewById(R.id.number_dossier);


        toolbar.setTitle("New Test");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        startTestBtn=findViewById(R.id.start_test_btn);
        startTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),StartTest.class);
                String nameA=name.getText().toString().trim();
                String familyA=family.getText().toString().trim();
                String numberA=number.getText().toString().trim();
                if (numberA.equals("") || nameA.equals("") || familyA.equals("")){
                    Toast.makeText(NewTestPage.this, "fill the fields", Toast.LENGTH_SHORT).show();
                }else {
                    dataBase.insertNewPerson(nameA,familyA,Integer.parseInt(numberA));
                    intent.putExtra("name",nameA);
                    intent.putExtra("family",familyA);
                    intent.putExtra("number",numberA);
                    startActivity(intent);
                }
            }
        });


    }
}
