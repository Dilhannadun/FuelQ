package com.example.fuelq.api;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fuelq.models.Shed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FuelStationServices {
    private Context context;
    private RequestQueue queue;
    String URL = "http://192.168.1.187/api/stations/";

    public FuelStationServices(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(this.context);
    }

    public void getNearbySheds(Location location) throws JSONException {
        Log.e("msg1", "Method started");
        String reqUrl = URL + "getNearby";
        ArrayList<Shed> nearbySheds = new ArrayList<>();
        JSONObject userLocation = new JSONObject("{\n" +
                "    \"latitude\": " + 7.273081 + ",\n" +
                "    \"longitude\": " + 80.611952 + "\n" +
                "}");
        System.out.println(userLocation);
        Log.e("url", reqUrl);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,
                reqUrl,
                userLocation,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray sheds = response.getJSONArray("stations");
                            System.out.println(sheds.length());
                            for (int i = 0; i < sheds.length(); i++) {
                                JSONObject shed = sheds.getJSONObject(i);
                                nearbySheds.add(Shed.createShedsFromJSON(shed));
                            }
                            Log.e("sheds..", nearbySheds.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Log.e("sheds here..", nearbySheds.toString());
        this.queue.add(req);
    }

//    public void getTest() {
//        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, URL,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONObject res = response.getJSONObject("user");
//                            Log.d("response", res.toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                });
//        this.queue.add(req);
//    }
}
