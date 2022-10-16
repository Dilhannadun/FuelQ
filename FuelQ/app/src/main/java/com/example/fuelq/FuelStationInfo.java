package com.example.fuelq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fuelq.models.Shed;

public class FuelStationInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_station_info);

        Shed shed = (Shed) getIntent().getSerializableExtra("shed");

        TextView shedHeader = findViewById(R.id.txt_FuelStationHeader);
        shedHeader.setText(shed.getShedName());
    }
}