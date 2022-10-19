package com.example.station_owner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button loginRegisterBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        loginRegisterBtn = (Button) findViewById(R.id.login_register_btn);
        loginRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterScreen();
            }
        });
    }

    public void openRegisterScreen(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void login(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}