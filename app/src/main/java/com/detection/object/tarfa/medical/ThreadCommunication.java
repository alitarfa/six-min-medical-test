package com.detection.object.tarfa.medical;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by tarfa on 6/14/18.
 */


public class ThreadCommunication extends Thread {

    InputStream connectedinputStream;
    OutputStream connectedoutputStream;
    BluetoothSocket bluetoothSocket;

    public ThreadCommunication(BluetoothSocket connectedbtsocket) {
        //add String op as an argument

        InputStream inputStream = null;
        OutputStream outputStream = null;
        bluetoothSocket = connectedbtsocket;

        try {
            inputStream = connectedbtsocket.getInputStream();
            outputStream = connectedbtsocket.getOutputStream();

        } catch (Exception e) {
            Log.d("Nazim", e.toString());
        }

        connectedinputStream = inputStream;
        connectedoutputStream = outputStream;

    }

    @Override
    public void run() {

        while (true) {
            try {


                InputStreamReader isr = new InputStreamReader(connectedinputStream);
                BufferedReader br = new BufferedReader(isr);
                Log.e("socket","i  get the socket");

                String read="";
                String line="";
                int rec=0;

                final String readMessage=""+(int)br.read();

                    StartTest.myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {

                         //int float_value = Integer.parseInt(readMessage);
                            if(StartTest.codes[StartTest.i]=='1') StartTest.fc_value.setText(readMessage);
                            else if(StartTest.codes[StartTest.i]=='2')StartTest.spo2_value.setText(readMessage);

                        } catch (Exception e) {
                            Log.d("dyabond", "  exeption:");
                        }
                    }
                });


                Thread.sleep(30);
            }
            catch (IOException e) {
                Log.d("dyabond", "Input stream was disconnected", e);
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void write(String op){
        try {
            connectedoutputStream.write(op.getBytes());



        } catch (IOException e) {
            Log.d("Nazim",e.toString());
        }
    }

}