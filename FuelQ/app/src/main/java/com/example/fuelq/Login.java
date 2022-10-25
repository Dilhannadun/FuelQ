package com.example.fuelq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fuelq.api.FuelStationServices;
import com.example.fuelq.models.Shed;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private TextView resetPassword;
    private Button loginBtn;
    private Button registerBtn;
    private FusedLocationProviderClient fusedLocationClient;
    private ArrayList<Shed> nearbySheds = new ArrayList<>();
    private final String URL = "http://192.168.1.187/api/";
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FuelStationServices services = new FuelStationServices(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        this.queue = Volley.newRequestQueue(this);

        resetPassword = findViewById(R.id.txt_ForgotPassword);
        loginBtn = findViewById(R.id.btn_LoginCustomer);
        registerBtn = findViewById(R.id.btn_RegisterCustomer);

        this.getNearbySheds(services);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToResetPassword();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToHome();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRegister();
            }
        });
    }

    public void navigateToResetPassword() {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }

    public void navigateToHome() {
        Intent intent = new Intent(this, FuelStationSearch.class);
        intent.putExtra("nearbySheds", this.nearbySheds);
        System.out.println(nearbySheds.size());
        startActivity(intent);
    }

    public void navigateToRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    private void getNearbySheds(FuelStationServices services) {
        System.out.println("In nearby sheds");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            System.out.println("In if");
            return;
        }
        System.out.println("Now out");
        this.fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            try {
                                callShedAPI();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Location null");
                        }
                    }
                });
    }

    private void callShedAPI() throws JSONException {
        String reqUrl = URL + "stations/getNearby";
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
                            Log.e("sheds..", String.valueOf(nearbySheds.size()));
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
        Log.e("sheds here..", "sheds");
        this.queue.add(req);
    }
}