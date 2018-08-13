package com.detection.object.tarfa.medical;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultTest extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model> models;
    public String name,family,number;
    public int time,distance;
    public TextView nameValue,familyValue,NumberValue,DistanceValue,TimeValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_test);
        Toolbar toolbar=findViewById(R.id.toolbar);
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

        recyclerView=findViewById(R.id.recycle);
        Intent intent=getIntent();
        if(intent!=null){

            models= (ArrayList<Model>) getIntent().getSerializableExtra("array");
            name=getIntent().getExtras().getString("name");
            family=getIntent().getExtras().getString("family");
            number=getIntent().getExtras().getString("number");
            time=getIntent().getExtras().getInt("time");
            distance=getIntent().getExtras().getInt("distance");
            Toast.makeText(this, ""+models.size(), Toast.LENGTH_SHORT).show();
        }

        nameValue=findViewById(R.id.name);
        familyValue=findViewById(R.id.family_name);
        NumberValue=findViewById(R.id.number_value);
        DistanceValue=findViewById(R.id.distance_value);
        TimeValue=findViewById(R.id.time_value);
        //  set the value
        nameValue.setText(name);
        familyValue.setText(family);
        NumberValue.setText(number);
        DistanceValue.setText(String.valueOf(distance));
        TimeValue.setText(String.valueOf(time));

        adapter adapter=new adapter(this);
        adapter.setList(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }
}


class adapter extends RecyclerView.Adapter<adapter.viewHolder>{
    ArrayList<Model> list;
    LayoutInflater inflater;
    public adapter(Context context){
        this.inflater=LayoutInflater.from(context);
    }

    public void setList(ArrayList<Model> list) {
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_list,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.fc.setText(String.valueOf(list.get(position).getFc()));
        holder.sp.setText(String.valueOf(list.get(position).getSpo2()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView fc,sp;
        public viewHolder(View itemView) {
            super(itemView);
            fc=itemView.findViewById(R.id.fc);
            sp=itemView.findViewById(R.id.sp);
        }
    }
}