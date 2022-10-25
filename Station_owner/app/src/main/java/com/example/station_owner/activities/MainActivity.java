package com.example.station_owner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.station_owner.R;
import com.example.station_owner.model.StationOwner;
import com.example.station_owner.repo.DBHandler;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private Button loginRegisterBtn, loginBtn;
    private TextInputEditText phone, password;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openRegisterScreen();
        login();
    }

    public void openRegisterScreen() {
        loginRegisterBtn = findViewById(R.id.login_register_btn);
        loginRegisterBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    public void login() {
        phone = findViewById(R.id.phone_edit);
        password = findViewById(R.id.password_edit);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(v -> {
            StationOwner owner = new StationOwner();
            dbHandler = new DBHandler(getApplicationContext());

            owner.setPhone(phone.getText().toString());
            owner.setPassword(password.getText().toString());

            if (TextUtils.isEmpty(owner.getPhone()) || TextUtils.isEmpty(owner.getPassword())) {
                Toast.makeText(MainActivity.this, "Fields Can not be Empty !", Toast.LENGTH_SHORT).show();
            } else {
                boolean checkCredentials = dbHandler.checkPhoneAndPassword(owner);
                if (checkCredentials) {
                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Credentials !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}