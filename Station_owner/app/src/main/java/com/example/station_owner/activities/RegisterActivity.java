package com.example.station_owner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.station_owner.R;
import com.example.station_owner.model.StationOwner;
import com.example.station_owner.repo.DBHandler;
import com.google.android.material.textfield.TextInputEditText;


public class RegisterActivity extends AppCompatActivity {

    TextInputEditText nic_v, station_id_v, email_v, password_v;
    Button registerBtn;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        onClickBackBtn();
        register();
    }

    protected void onClickBackBtn(){
        Button backBtn;
        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(v -> RegisterActivity.super.onBackPressed());
    }

    protected void register(){

        dbHandler = new DBHandler(getApplicationContext());

        nic_v =  findViewById(R.id.owner_nic_edit);
        station_id_v = findViewById(R.id.station_id_edit);
        email_v = findViewById(R.id.register_email_edit);
        password_v = findViewById(R.id.register_password_edit);
        registerBtn = findViewById(R.id.register_register_btn);

        registerBtn.setOnClickListener(v -> {
            StationOwner stationOwner = new StationOwner();
            String nic = nic_v.getText().toString();
            String stationId = station_id_v.getText().toString();
            String email = email_v.getText().toString();
            String password = password_v.getText().toString();

            stationOwner.setNic(nic);
            stationOwner.setStation_id(stationId);
            stationOwner.setEmail(email);
            stationOwner.setPassword(password);

            if(TextUtils.isEmpty(stationOwner.getNic()) || TextUtils.isEmpty(stationOwner.getStation_id()) ||
                    TextUtils.isEmpty(stationOwner.getEmail()) || TextUtils.isEmpty(stationOwner.getPassword())){
                Toast.makeText(RegisterActivity.this, "Fields Can not be Empty !", Toast.LENGTH_SHORT).show();
            }else{
                Log.d("owner1111111111", stationOwner.toString());
                boolean checkEmail = dbHandler.checkEmail(stationOwner);
                if(!checkEmail){
                    boolean insert = dbHandler.insertDate(stationOwner);
                    if(insert){
                        Toast.makeText(RegisterActivity.this, "Station Owner Registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed !", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "User Already Exists! Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}