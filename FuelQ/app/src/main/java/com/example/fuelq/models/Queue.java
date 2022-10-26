package com.example.fuelq.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Queue implements Serializable {
    private String id;
    private String fuelType;
    private int numberOfVehicles;
    private int totalTime;

    public Queue() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public static Queue createQueueFromJSON(JSONObject jsonObject) throws JSONException {
        Queue queue = new Queue();
        queue.setId(jsonObject.getString("_id"));
        queue.setFuelType(jsonObject.getString("fuelType"));
        queue.setNumberOfVehicles(jsonObject.getInt("numberOfVehicles"));
        queue.setTotalTime(jsonObject.getInt("totalTime"));

        return queue;
    }
}
