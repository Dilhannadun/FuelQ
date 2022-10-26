//IT19149318
//This interface will holds all the end points to obtain data from the APIs.

package com.example.station_owner.api;
import com.example.station_owner.model.Shed;
import com.example.station_owner.model.StationOwner;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface APIService {
    String BASE_URL = "https://192.168.154.24/owner/";

    @POST("/register")
    Call<StationOwner> registerOwner(@Body JsonObject jsonObject);

    @PATCH("/update-fuel-status")
    Call<Shed> updateFuelAvailability(@Body JsonObject jsonObject);
}
