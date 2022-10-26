//IT19149318
//This is the Register activity of the application

package com.example.station_owner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.station_owner.R;
import com.example.station_owner.api.RetrofitClient;
import com.example.station_owner.model.StationOwner;
import com.example.station_owner.repo.DBHandler;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    TextInputEditText nic_v, station_id_v, phone_v, email_v, password_v;
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

    //This is the owner registration method
    protected void register(){
        dbHandler = new DBHandler(getApplicationContext());

        nic_v =  findViewById(R.id.owner_nic_edit);
        station_id_v = findViewById(R.id.station_id_edit);
        phone_v = findViewById(R.id.register_phone_edit);
        email_v = findViewById(R.id.register_email_edit);
        password_v = findViewById(R.id.register_password_edit);
        registerBtn = findViewById(R.id.register_register_btn);

        registerBtn.setOnClickListener(v -> {
            StationOwner stationOwner = new StationOwner();
            String nic = nic_v.getText().toString();
            String stationId = station_id_v.getText().toString();
            String phone = phone_v.getText().toString();
            String email = email_v.getText().toString();
            String password = password_v.getText().toString();

            stationOwner.setNic(nic);
            stationOwner.setStation_id(stationId);
            stationOwner.setPhone(phone);
            stationOwner.setEmail(email);
            stationOwner.setPassword(password);

            if(TextUtils.isEmpty(stationOwner.getNic()) || TextUtils.isEmpty(stationOwner.getStation_id()) ||
                    TextUtils.isEmpty(stationOwner.getPhone()) ||
                    TextUtils.isEmpty(stationOwner.getEmail()) || TextUtils.isEmpty(stationOwner.getPassword())){
                Toast.makeText(RegisterActivity.this, "Fields Can not be Empty !", Toast.LENGTH_SHORT).show();
            }else{
                boolean checkNic = dbHandler.checkNic(stationOwner);
                boolean checkStation = dbHandler.checkStation(stationOwner);
                boolean checkPhone = dbHandler.checkPhone(stationOwner);
                boolean checkEmail = dbHandler.checkEmail(stationOwner);

                if(!checkNic) {
                    if(!checkStation) {
                        if(!checkPhone) {
                            if (!checkEmail) {
                                registerData(stationOwner);
                                boolean insert = dbHandler.registerOwner(stationOwner);
                                if (insert) {
                                    Toast.makeText(RegisterActivity.this, "Station Owner Registered", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Registration Failed !", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "Email Already Exists!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "Phone Already Exists!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Station Already Registered!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Nic Already Exists!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //This method will used to send data to mongodb
    protected void registerData(StationOwner owner){
        JsonObject gsonobject = new JsonObject();
        JsonParser jsonParser = new JsonParser();
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nic" , ""+ owner.getNic());
            jsonObject.put("station_id" , ""+ owner.getStation_id());
            jsonObject.put("phone" , ""+ owner.getPhone());
            jsonObject.put("email" , ""+ owner.getEmail());
            gsonobject = (JsonObject) jsonParser.parse(jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }

        Call<StationOwner> addOwnerCall = RetrofitClient.getInstance().getMyApi().registerOwner(gsonobject);
        addOwnerCall.enqueue(new Callback<StationOwner>() {
            @Override
            public void onResponse(Call<StationOwner> call, Response<StationOwner> response) {
                Log.d("result", ""+ response.body());
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<StationOwner> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

}