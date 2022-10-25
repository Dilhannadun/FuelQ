package com.example.fuelq.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fuelq.models.User;

import org.json.JSONException;
import org.json.JSONObject;

public class UserServices {
    private Context context;
    private RequestQueue queue;
    private DBHandler dbHandler;
    String URL = "http://192.168.1.187/api/users/";

    public UserServices(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(this.context);
        this.dbHandler = new DBHandler(context);
    }

    public void register(User user) {

        JSONObject reqObj = new JSONObject();
        try {
            reqObj.put("email", user.getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("reqObj", reqObj.toString());
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, URL,
                reqObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject res = response.getJSONObject("user");
                            user.setId(res.getString("_id"));
                            dbHandler.addNewUser(user);
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

    public void login() {

    }
}
