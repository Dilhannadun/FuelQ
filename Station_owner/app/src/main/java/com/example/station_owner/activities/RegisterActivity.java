package com.example.station_owner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.station_owner.R;
import com.example.station_owner.model.StationOwner;
import com.example.station_owner.repo.DBHandler;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout nic_v, station_id_v, email_v, password_v;
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
        backBtn = (Button) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.super.onBackPressed();
            }
        });
    }

    protected void register(){
        nic_v = findViewById(R.id.owner_nic);
        station_id_v = findViewById(R.id.station_id);
        email_v = findViewById(R.id.register_email);
        password_v = findViewById(R.id.register_password);
        registerBtn = findViewById(R.id.register_register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StationOwner stationOwner = new StationOwner();
                String nic = nic_v.toString();
                String stationId = station_id_v.toString();
                String email = email_v.toString();
                String password = password_v.toString();

                stationOwner.setNic(nic);
                stationOwner.setStation_id(stationId);
                stationOwner.setEmail(email);
                stationOwner.setPassword(password);

                Log.d("owner1111111111", stationOwner.getEmail());

                if(TextUtils.isEmpty(stationOwner.getNic()) || TextUtils.isEmpty(stationOwner.getStation_id()) ||
                        TextUtils.isEmpty(stationOwner.getEmail()) || TextUtils.isEmpty(stationOwner.getPassword())){
                    Toast.makeText(RegisterActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkEmail = dbHandler.checkEmail(stationOwner);
                    if(checkEmail){
                        Boolean insert = dbHandler.insertDate(stationOwner);
                        if(insert == true){
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
            }
        });
    }

}