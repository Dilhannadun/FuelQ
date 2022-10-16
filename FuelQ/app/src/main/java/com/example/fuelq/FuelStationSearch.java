package com.example.fuelq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.fuelq.models.Shed;

import java.util.ArrayList;

public class FuelStationSearch extends AppCompatActivity {
    ArrayList<Shed> sheds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_station_search);

        RecyclerView shedsRecycler = findViewById(R.id.rec_ShedList);
        sheds = Shed.createSheds(15);

        FuelSearchListAdapter adapter = new FuelSearchListAdapter(sheds);
        shedsRecycler.setAdapter(adapter);
        shedsRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}