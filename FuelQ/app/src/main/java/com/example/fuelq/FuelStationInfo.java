package com.example.fuelq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fuelq.models.Shed;

public class FuelStationInfo extends AppCompatActivity {

    private TextView p95Status;
    private TextView p95QueueLength;
    private TextView p95WaitTime;
    private TextView p92Status;
    private TextView p92QueueLength;
    private TextView p92WaitTime;
    private TextView dieselStatus;
    private TextView dieselQueueLength;
    private TextView dieselWaitTime;
    private TextView shedHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_station_info);

        Shed shed = (Shed) getIntent().getSerializableExtra("shed");

        p95Status = findViewById(R.id.val_Petrol95FuelStatus);
        p95QueueLength = findViewById(R.id.val_Petrol95QueueLength);
        p95WaitTime = findViewById(R.id.val_Petrol95WaitTime);
        p92Status = findViewById(R.id.val_Petrol92FuelStatus);
        p92QueueLength = findViewById(R.id.val_Petrol92QueueLength);
        p92WaitTime = findViewById(R.id.val_Petrol92WaitTime);
        dieselStatus = findViewById(R.id.val_DieselFuelStatus);
        dieselQueueLength = findViewById(R.id.val_DieselQueueLength);
        dieselWaitTime = findViewById(R.id.val_DieselWaitTime);
        shedHeader = findViewById(R.id.txt_FuelStationHeader);

        shedHeader.setText(shed.getShedName());
        p95Status.setText(shed.isPetrol95Status() ? "Available" : "Unavailable");
        p92Status.setText(shed.isPetrol92Status() ? "Available" : "Unavailable");
        dieselStatus.setText(shed.isDieselStatus() ? "Available" : "Unavailable");
    }
}