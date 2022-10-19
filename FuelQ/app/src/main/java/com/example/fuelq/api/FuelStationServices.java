package com.example.fuelq.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FuelStationServices {
    private Context context;
    private RequestQueue queue;
    String URL = "http://192.168.1.187/api";

    public FuelStationServices(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(this.context);
    }

    public void getTest() {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject res = response.getJSONObject("user");
                            Log.d("response", res.toString());
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
                });
        this.queue.add(req);
    }


}
