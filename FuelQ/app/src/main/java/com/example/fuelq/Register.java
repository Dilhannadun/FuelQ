package com.example.fuelq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fuelq.api.DBHandler;
import com.example.fuelq.api.UserServices;
import com.example.fuelq.models.User;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {
    private Button registerBtn;
    private TextInputEditText userEmail;
    private TextInputEditText userPassword;
    private TextInputEditText userConfirmPassword;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        UserServices services = new UserServices(this);
        dbHandler = new DBHandler(this);

        userEmail = findViewById(R.id.edt_RegisterEmail);
        userPassword = findViewById(R.id.edt_RegisterPassword);
        userConfirmPassword = findViewById(R.id.edt_RegisterConfirmPassword);
        registerBtn = findViewById(R.id.btn_RegisterRegisterBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(userEmail.getText().toString());
                user.setPassword(userPassword.getText().toString());
                services.register(user);
            }
        });
    }
}