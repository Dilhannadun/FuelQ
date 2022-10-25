package com.example.station_owner.model;

import java.io.Serializable;

public class StationOwner implements Serializable {

    private String nic;
    private String station_id;
    private String email;
    private String password;

    public StationOwner() {
    }

    public StationOwner(String nic, String station_id, String email, String password) {

        this.nic = nic;
        this.station_id = station_id;
        this.email = email;
        this.password = password;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "StationOwner{" +
                "nic='" + nic + '\'' +
                ", station_id='" + station_id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
