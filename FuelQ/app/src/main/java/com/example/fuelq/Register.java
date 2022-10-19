package com.example.fuelq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fuelq.api.UserServices;
import com.example.fuelq.models.User;

public class Register extends AppCompatActivity {
    private Button registerBtn;
    private EditText userEmail;
    private EditText userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        UserServices services = new UserServices(this);

        userEmail = findViewById(R.id.edt_RegisterEmail);
        userPassword = findViewById(R.id.edt_RegisterPassword);
        registerBtn = findViewById(R.id.btn_RegisterRegisterBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(userEmail.getText().toString());
                services.register(user);
            }
        });
    }
}