package com.example.fuelq.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Shed implements Serializable {
    private String shedName;
    private String shedLocation;
    private float shedLatitude;
    private float shedLongitude;
    private String shedId;

    public Shed(String shedName, String shedLocation, String shedId) {
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

    public String getShedId() {
        return shedId;
    }

    public void setShedId(String shedId) {
        this.shedId = shedId;
    }

    public float getShedLatitude() {
        return shedLatitude;
    }

    public void setShedLatitude(float shedLatitude) {
        this.shedLatitude = shedLatitude;
    }

    public float getShedLongitude() {
        return shedLongitude;
    }

    public void setShedLongitude(float shedLongitude) {
        this.shedLongitude = shedLongitude;
    }

    public static Shed createShedsFromJSON(JSONObject jsonObject) throws JSONException {
        Shed shed = new Shed(
                jsonObject.getString("stationName"),
                jsonObject.getString("stationLocation"),
                jsonObject.getString("_id")
        );

        shed.setShedLatitude((float) jsonObject.getInt("stationLatitude"));
        shed.setShedLongitude((float) jsonObject.getInt("stationLongitude"));

        return  shed;
    }

    public static ArrayList<Shed> createSheds(int num) {
        ArrayList<Shed> sheds = new ArrayList<Shed>();

        for (int i = 0; i < num; i++) {
            sheds.add(new Shed("Shed " + i, "Location " + i, "aa"));
        }
        return sheds;
    }
}
