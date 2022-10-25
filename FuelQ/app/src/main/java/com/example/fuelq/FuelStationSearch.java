package com.example.fuelq;

import static com.google.android.gms.location.Priority.PRIORITY_LOW_POWER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.fuelq.api.FuelStationServices;
import com.example.fuelq.models.Shed;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;

import java.util.ArrayList;

public class FuelStationSearch extends AppCompatActivity {
    ArrayList<Shed> sheds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FuelStationServices services = new FuelStationServices(this);
        setContentView(R.layout.activity_fuel_station_search);

        RecyclerView shedsRecycler = findViewById(R.id.rec_ShedList);
        sheds = (ArrayList<Shed>) getIntent().getSerializableExtra("nearbySheds");

        FuelSearchListAdapter adapter = new FuelSearchListAdapter(sheds);
        shedsRecycler.setAdapter(adapter);
        shedsRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}