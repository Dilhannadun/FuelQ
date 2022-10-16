package com.example.fuelq.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Shed implements Serializable {
    private String shedName;
    private String shedLocation;
    private int shedId;

    public Shed(String shedName, String shedLocation, int shedId) {
        this.shedName = shedName;
        this.shedLocation = shedLocation;
        this.shedId = shedId;
    }

    public String getShedName() {
        return shedName;
    }

    public void setShedName(String shedName) {
        this.shedName = shedName;
    }

    public String getShedLocation() {
        return shedLocation;
    }

    public void setShedLocation(String shedLocation) {
        this.shedLocation = shedLocation;
    }

    public int getShedId() {
        return shedId;
    }

    public void setShedId(int shedId) {
        this.shedId = shedId;
    }

    public static ArrayList<Shed> createSheds(int num) {
        ArrayList<Shed> sheds = new ArrayList<Shed>();

        for (int i = 0; i < num; i++) {
            sheds.add(new Shed("Shed " + i, "Location " + i, i));
        }
        return sheds;
    }
}
