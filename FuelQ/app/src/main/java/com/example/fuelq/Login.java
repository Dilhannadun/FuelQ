package com.example.fuelq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private TextView resetPassword;
    private Button loginBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        resetPassword = findViewById(R.id.txt_ForgotPassword);
        loginBtn = findViewById(R.id.btn_LoginCustomer);
        registerBtn = findViewById(R.id.btn_RegisterCustomer);

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
        startActivity(intent);
    }

    public void navigateToRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}