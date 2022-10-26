//IT19149318
//This fragment is the fuel status fragment of the bottom navigation menu


package com.example.station_owner.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.station_owner.R;
import com.example.station_owner.api.RetrofitClient;
import com.example.station_owner.model.Shed;
import com.example.station_owner.model.StationOwner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FuelStatusFragment extends Fragment {
    private RadioGroup rg_95oct, rg_92oct, rg_diesel;
    private RadioButton radioBtn_95oct, radioBtn_92oct, radioBtn_diesel;
    private Button btnUpdate;
    private Shed shed = new Shed();
    private String stationId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fuel_status, container, false);

        rg_95oct = rootView.findViewById(R.id.rbg_1);
        rg_92oct = rootView.findViewById(R.id.rbg_2);
        rg_diesel = rootView.findViewById(R.id.rbg_3);
        btnUpdate = rootView.findViewById(R.id.statusUpdateBtn);

        int oct95SelectedId = rg_95oct.getCheckedRadioButtonId();
        int oct92SelectedId = rg_92oct.getCheckedRadioButtonId();
        int dieselSelectedId = rg_diesel.getCheckedRadioButtonId();

        if(oct95SelectedId == 1) {
            shed.setPetrol95Status(true);
        }else{
            shed.setPetrol95Status(false);
        }

        if(oct92SelectedId == 1) {
            shed.setPetrol92Status(true);
        }else{
            shed.setPetrol92Status(false);
        }

        if(dieselSelectedId == 1) {
            shed.setDieselStatus(true);
        }else{
            shed.setDieselStatus(false);
        }
        btnUpdate.setOnClickListener(v -> {
            updateFuelStatus(shed, stationId);
        });

        return rootView;
    }

    //This method is used to update fuel status of the fuel stations
    protected void updateFuelStatus(Shed shed, String stationId){

        JsonObject gsonobject = new JsonObject();
        JsonParser jsonParser = new JsonParser();
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("petrol95" , ""+ shed.isPetrol92Status());
            jsonObject.put("petrol92" , ""+ shed.isPetrol92Status());
            jsonObject.put("diesel" , ""+ shed.isDieselStatus());
            jsonObject.put("stationId" , ""+ stationId);
            gsonobject = (JsonObject) jsonParser.parse(jsonObject.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }

        Call<Shed> updateFuelStatusCall = RetrofitClient.getInstance().getMyApi().updateFuelAvailability(gsonobject);
        updateFuelStatusCall.enqueue(new Callback<Shed>() {
            @Override
            public void onResponse(Call<Shed> call, Response<Shed> response) {
                Log.d("result", ""+ response.body());
                Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Shed> call, Throwable t) {
                Toast.makeText(getContext(), "Error Updating", Toast.LENGTH_LONG).show();
            }
        });
    }

}