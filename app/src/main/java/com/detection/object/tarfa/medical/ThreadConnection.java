package com.detection.object.tarfa.medical;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.util.UUID;

/**
 * Created by tarfa on 6/14/18.
 */

public class ThreadConnection extends Thread {

    private BluetoothSocket bluetoothSocket = null;
    private BluetoothDevice bluetoothDev;
    private BluetoothAdapter BA;
    String op;

    public static final UUID MY_UUID_SECURE =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public ThreadConnection(BluetoothDevice bD) {
        this.bluetoothDev = bD;
        BluetoothSocket socket=null;

        try{
            BA=BluetoothAdapter.getDefaultAdapter();
            bluetoothDev=BA.getRemoteDevice(bluetoothDev.getAddress());
            socket=bluetoothDev.createInsecureRfcommSocketToServiceRecord(MY_UUID_SECURE);


        }catch (Exception e){
            e.printStackTrace();
        }
        bluetoothSocket=socket;
    }

    @Override
    public void run() {


        try{
            bluetoothSocket.connect();
            StartTest.socket=bluetoothSocket;
            StartTest.connected=true;
            StartTest.myHandler.post(new Runnable() {
                @Override
                public void run() {

                    StartTest.myToast(StartTest.context,"Connected");
                }
            });

        }catch (Exception e){
            StartTest.connected=false;
            StartTest.myHandler.post(new Runnable() {
                @Override
                public void run() {
                    StartTest.myToast(StartTest.context,"Connection Error Try again");
                }
            });

            try{
                bluetoothSocket.close();

            }catch (Exception ex){
                Log.d("Nazim",ex.toString());
            }
        }

    }
}