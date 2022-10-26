package com.example.fuelq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fuelq.api.FuelStationServices;
import com.example.fuelq.models.Queue;
import com.example.fuelq.models.Shed;

import org.json.JSONException;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

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
    private Button p95JoinBtn;
    private Button p92JoinBtn;
    private Button dieselJoinBtn;
    private Button p95LeaveQueueBtn;
    private Button p95LeaveQueueNoBtn;
    private Button p92LeaveQueueBtn;
    private Button p92LeaveQueueNoBtn;
    private Button dieselLeaveQueueBtn;
    private Button dieselLeaveQueueNoBtn;
    private FuelStationServices services;
    private Instant joinTime;
    private Instant leaveTime;

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

        initAvailibility(shed);
        initQueueLengthAndWaitTime(shed);
        initButtons(shed);
        initButtonEventListeners(shed);
    }

    private void initAvailibility(Shed shed) {
        p95Status.setText(shed.isPetrol95Status() ? "Available" : "Unavailable");
        p92Status.setText(shed.isPetrol92Status() ? "Available" : "Unavailable");
        dieselStatus.setText(shed.isDieselStatus() ? "Available" : "Unavailable");
    }

    private void initQueueLengthAndWaitTime(Shed shed) {
        ArrayList<Queue> queues = shed.getQueues();
        for (int i = 0; i < queues.size(); i++) {
            Queue queue = queues.get(i);

            switch (queue.getFuelType()) {
                case "Petrol95":
                    p95QueueLength.setText(String.valueOf(queue.getNumberOfVehicles()));
                    p95WaitTime.setText(calculateTime(queue.getTotalTime() * queue.getNumberOfVehicles()));
                    break;
                case "Petrol92":
                    p92QueueLength.setText(String.valueOf(queue.getNumberOfVehicles()));
                    p92WaitTime.setText(calculateTime(queue.getTotalTime() * queue.getNumberOfVehicles()));
                    break;
                case "Disel":
                    dieselQueueLength.setText(String.valueOf(queue.getNumberOfVehicles()));
                    dieselWaitTime.setText(calculateTime(queue.getTotalTime() * queue.getNumberOfVehicles()));
                    break;
                default:
                    break;
            }
        }
    }

    private void initButtons(Shed shed) {
        p95JoinBtn = findViewById(R.id.btn_joinQueue_95);
        p92JoinBtn = findViewById(R.id.btn_joinQueue_92);
        dieselJoinBtn = findViewById(R.id.btn_joinQueue_D);
        p95LeaveQueueBtn = findViewById(R.id.btn_leaveQueue_95);
        p95LeaveQueueNoBtn = findViewById(R.id.btn_leaveQueueNotFull_95);
        p92LeaveQueueBtn = findViewById(R.id.btn_leaveQueue_92);
        p92LeaveQueueNoBtn = findViewById(R.id.btn_leaveQueueNotFull_92);
        dieselLeaveQueueBtn = findViewById(R.id.btn_leaveQueue_D);
        dieselLeaveQueueNoBtn = findViewById(R.id.btn_leaveQueueNotFull_D);

        if (!shed.isPetrol95Status()) p95JoinBtn.setEnabled(false);
        if (!shed.isPetrol92Status()) p92JoinBtn.setEnabled(false);
        if (!shed.isDieselStatus()) dieselJoinBtn.setEnabled(false);
    }

    private void initButtonEventListeners(Shed shed) {
        services = new FuelStationServices(this);
        p95JoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p92JoinBtn.setEnabled(false);
                dieselJoinBtn.setEnabled(false);
                p95LeaveQueueBtn.setVisibility(View.VISIBLE);
                p95LeaveQueueNoBtn.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    joinTime = Instant.now();
                }
                p95QueueLength.setText(String.valueOf(Integer.parseInt(p95QueueLength.getText().toString())+1));
                try {
                    services.joinOrLeaveQueue("Petrol95", shed.getShedId(), true, 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        p92JoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p95JoinBtn.setEnabled(false);
                dieselJoinBtn.setEnabled(false);
                p92LeaveQueueBtn.setVisibility(View.VISIBLE);
                p92LeaveQueueNoBtn.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    joinTime = Instant.now();
                }
                p92QueueLength.setText(String.valueOf(Integer.parseInt(p92QueueLength.getText().toString())+1));
                try {
                    services.joinOrLeaveQueue("Petrol92", shed.getShedId(), true, 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        dieselJoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p95JoinBtn.setEnabled(false);
                p92JoinBtn.setEnabled(false);
                dieselLeaveQueueBtn.setVisibility(View.VISIBLE);
                dieselLeaveQueueNoBtn.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    joinTime = Instant.now();
                }
                dieselQueueLength.setText(String.valueOf(Integer.parseInt(dieselQueueLength.getText().toString())+1));
                try {
                    services.joinOrLeaveQueue("Diesel", shed.getShedId(), true, 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        p95LeaveQueueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p92JoinBtn.setEnabled(true);
                dieselJoinBtn.setEnabled(true);
                p95LeaveQueueBtn.setVisibility(View.GONE);
                p95LeaveQueueNoBtn.setVisibility(View.GONE);
                int time = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    leaveTime = Instant.now();
                    Duration duration = Duration.between(joinTime, leaveTime);
                    time = (int) (duration.toMinutes() / 60);
                }
                p95QueueLength.setText(String.valueOf(Integer.parseInt(p95QueueLength.getText().toString())-1));
                try {
                    services.joinOrLeaveQueue("Petrol95", shed.getShedId(), false, time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        p92LeaveQueueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p95JoinBtn.setEnabled(true);
                dieselJoinBtn.setEnabled(true);
                p92LeaveQueueBtn.setVisibility(View.GONE);
                p92LeaveQueueNoBtn.setVisibility(View.GONE);
                int time = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    leaveTime = Instant.now();
                    Duration duration = Duration.between(joinTime, leaveTime);
                    time = (int) (duration.toMinutes() * 60);
                }
                p92QueueLength.setText(String.valueOf(Integer.parseInt(p92QueueLength.getText().toString())-1));
                try {
                    services.joinOrLeaveQueue("Petrol92", shed.getShedId(), false, time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        p95LeaveQueueNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p92JoinBtn.setEnabled(true);
                dieselJoinBtn.setEnabled(true);
                p95LeaveQueueBtn.setVisibility(View.GONE);
                p95LeaveQueueNoBtn.setVisibility(View.GONE);
                int time = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    leaveTime = Instant.now();
                    Duration duration = Duration.between(joinTime, leaveTime);
                    time = (int) (duration.toMinutes() * 60);
                }
                p95QueueLength.setText(String.valueOf(Integer.parseInt(p95QueueLength.getText().toString())-1));
                try {
                    services.joinOrLeaveQueue("Petrol95", shed.getShedId(), false, time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        p92LeaveQueueNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p95JoinBtn.setEnabled(true);
                dieselJoinBtn.setEnabled(true);
                p92LeaveQueueBtn.setVisibility(View.GONE);
                p92LeaveQueueNoBtn.setVisibility(View.GONE);
                int time = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    leaveTime = Instant.now();
                    Duration duration = Duration.between(joinTime, leaveTime);
                    time = (int) (duration.toMinutes() * 60);
                }
                p92QueueLength.setText(String.valueOf(Integer.parseInt(p92QueueLength.getText().toString())-1));
                try {
                    services.joinOrLeaveQueue("Petrol92", shed.getShedId(), false, time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        dieselLeaveQueueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p95JoinBtn.setEnabled(true);
                p92JoinBtn.setEnabled(true);
                dieselLeaveQueueBtn.setVisibility(View.GONE);
                dieselLeaveQueueNoBtn.setVisibility(View.GONE);
                int time = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    leaveTime = Instant.now();
                    Duration duration = Duration.between(joinTime, leaveTime);
                    time = (int) (duration.toMinutes() * 60);
                }
                dieselQueueLength.setText(String.valueOf(Integer.parseInt(dieselQueueLength.getText().toString())-1));
                try {
                    services.joinOrLeaveQueue("Diesel", shed.getShedId(), false, time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        dieselLeaveQueueNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p95JoinBtn.setEnabled(true);
                p92JoinBtn.setEnabled(true);
                dieselLeaveQueueBtn.setVisibility(View.GONE);
                dieselLeaveQueueNoBtn.setVisibility(View.GONE);
                int time = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    leaveTime = Instant.now();
                    Duration duration = Duration.between(joinTime, leaveTime);
                    time = (int) (duration.toMinutes() * 60);
                }
                dieselQueueLength.setText(String.valueOf(Integer.parseInt(dieselQueueLength.getText().toString())-1));
                try {
                    services.joinOrLeaveQueue("Diesel", shed.getShedId(), false, time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String calculateTime(int timeInSeconds) {
        int timeInMin = timeInSeconds / 60;
        int timeInHours = 0;
        if (timeInMin > 60) timeInHours = timeInMin - 60;
        return timeInHours + " h " + timeInMin + " min";
    }
}