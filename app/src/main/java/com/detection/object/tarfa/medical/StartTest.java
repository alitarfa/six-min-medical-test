package com.detection.object.tarfa.medical;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class StartTest extends AppCompatActivity {
    Chronometer chronometer;
    Button start,stop,restart;
    TextView distanceBtn;
    TextView distanceValue;
    EditText distanceValueAddition;
    public int STEPVALUE;
    public static int valueDistance=0;

    private BluetoothAdapter BA;
    public static  char[] codes={'1','2'};
    public static Handler myHandler=new Handler();
    public static BluetoothSocket socket;
    static BluetoothDevice btdevice;
    private Set<BluetoothDevice> pairedDevices;
    public static boolean connected=false;
    public static final UUID MY_UUID_SECURE =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    Button connect,send,finishBtn;
    static TextView text,text2;
    public static Context context;
    public static int i=0;



    static TextView fc_value,spo2_value;
    public boolean c1=true;
    public boolean c2=true;
    public boolean c3=true;
    public boolean c4=true;
    public boolean c5=true;
    public boolean c6=true;


    ArrayList<Model> listMinitTest;
    public static long time=0;

    public StartTest() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
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

        STEPVALUE=getSharedPreferences("setting",MODE_PRIVATE).getInt("step",15);

        finishBtn=findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();

                String name=  getIntent().getExtras().getString("name");
                String family=getIntent().getExtras().getString("family");
                String number=getIntent().getExtras().getString("number");

                Intent intent=new Intent(getBaseContext(),ResultTest.class);
                intent.putExtra("array",listMinitTest);
                intent.putExtra("name",name);
                intent.putExtra("family",family);
                intent.putExtra("number",number);
                intent.putExtra("time",time);
                intent.putExtra("distance",Integer.parseInt(distanceValueAddition.getText().toString())+Integer.parseInt(distanceValue.getText().toString()));
                startActivity(intent);

            }
        });

        listMinitTest=new ArrayList<>();
        context=this;
        start=findViewById(R.id.start_btn);
        stop=findViewById(R.id.stop_btn);
        restart=findViewById(R.id.restart_btn);
        chronometer=findViewById(R.id.chronometer3);
        distanceBtn=findViewById(R.id.distance_btn);

        distanceValue=findViewById(R.id.distance_text);
        distanceValueAddition=findViewById(R.id.distance_text_edit);
        fc_value=findViewById(R.id.fc_value);
        spo2_value=findViewById(R.id.spo2_value);

        start.setOnClickListener(this::start);
        stop.setOnClickListener(this::stop);
        restart.setOnClickListener(this::restart);
        distanceBtn.setOnClickListener(this::calculateDistance);

        /**
         *
         */



        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

                 long min = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000)/60;
                 StartTest.time=min;

                 if (min==1 && c1) {c1=false ;listMinitTest.add(new Model(Integer.parseInt(fc_value.getText().toString()),Integer.parseInt(spo2_value.getText().toString())));}
                 if (min==2 &&c2 ) { c2=false ; listMinitTest.add(new Model(Integer.parseInt(fc_value.getText().toString()),Integer.parseInt(spo2_value.getText().toString())));}
                 if (min==3 &&c3 ) {c3=false ; listMinitTest.add(new Model(Integer.parseInt(fc_value.getText().toString()),Integer.parseInt(spo2_value.getText().toString())));}
                 if (min==4 &&c4 ) {c4=false ; listMinitTest.add(new Model(Integer.parseInt(fc_value.getText().toString()),Integer.parseInt(spo2_value.getText().toString())));}
                 if (min==5 && c5) { c5=false ; listMinitTest.add(new Model(Integer.parseInt(fc_value.getText().toString()),Integer.parseInt(spo2_value.getText().toString())));}
                 if (min==6 &&c5 ) { c6=false ;listMinitTest.add(new Model(Integer.parseInt(fc_value.getText().toString()),Integer.parseInt(spo2_value.getText().toString())));
                     chronometer.stop();
                  }

            }
        });
    }

    private void calculateDistance(View view) {
        valueDistance = valueDistance+STEPVALUE;
        distanceValue.setText(String.valueOf(valueDistance));
    }

    private void restart(View view) {
       // chronometer.setFormat("0");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
    }

    private void stop(View view) {
        chronometer.stop();
    }

    private void start(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }


    public void saveTimeEachMini(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         int id = item.getItemId();

         if (id == R.id.connect) {

             BA=BluetoothAdapter.getDefaultAdapter();
             if(!connected){
                 if(!BA.isEnabled())turnOnBt();
                   connectToDevice();
             }else

                 turnOffBt();

             return true;
        }

        /**
         * this to start the test by sending the value 1
         */
        if (id==R.id.start){
            if(connected){
                monitor();
            }

            else{
                myToast(this,"No bluetooth");
            }
            return  true;
        }


        return super.onOptionsItemSelected(item);
    }


    /**
     *
     * @param fcValue
     */
    public void setFcValue(String fcValue){
        fc_value.setText(fcValue);
    }


    /**
     *
     * @param spo2VAlue
     */
    public void setSpo2VAlue(String spo2VAlue) {
        spo2_value.setText(spo2VAlue);
    }



    /**
     * --------------------------------------------------------------------------------------------------------
      */
    /**
     * every thing related with the blutooth
     */

    void monitor(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                ThreadCommunication comm=new ThreadCommunication(socket);
                comm.start();

                while(true){

                    if(connected){
                        Log.e("sending","i am sending");
                        comm.write(""+codes[i]);
                        i++;
                        if(i>1)i=0;

                    }else myToast(StartTest.this,"Error");


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

            }
        }).start();


    }

    public static void myToast(Context context,String msg){

        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();

    }
    void connectToDevice(){
        pairedDevices = BA.getBondedDevices();
        ArrayList list = new ArrayList();

        for (BluetoothDevice bt : pairedDevices) {
            list.add(bt.getName());

            if (bt.getName().equals("WameedhBT_2")) {
                btdevice = bt;
                Log.d("Nazim", "detected");
            }

        }
        if (btdevice != null) {
            ThreadConnection connection = new ThreadConnection(btdevice);
            connection.start();

        }
    }
    public void turnOnBt(){
        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Turning On Bluetooth",Toast.LENGTH_LONG).show();
        }
    }
    void turnOffBt(){

        if(BA.isEnabled() && connected){
            connected=false;
            try{
                socket.close();
            }catch (Exception e){
            }
        }

    }

}
