package com.detection.object.tarfa.medical;

import java.io.Serializable;

/**
 * Created by tarfa on 6/17/18.
 */

public class Model implements Serializable {
    private int fc;
    private int spo2;


    public Model(int fc, int spo2) {
        this.fc = fc;
        this.spo2 = spo2;
    }

    public int getFc() {
        return fc;
    }

    public void setFc(int fc) {
        this.fc = fc;
    }

    public int getSpo2() {
        return spo2;
    }

    public void setSpo2(int spo2) {
        this.spo2 = spo2;
    }
}
