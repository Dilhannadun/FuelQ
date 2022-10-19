package com.example.station_owner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class FuelStatusFragment extends Fragment {
    
    String[] arrived_fuel = {"92 Octane", "98 Octane", "Diesel"};
    String[] finished_fuel = {"92 Octane", "98 Octane", "Diesel"};

    AutoCompleteTextView autoCompleteArrivals, autoCompleteFinished;
    ArrayAdapter<String> adapterItems;

    private TextView arrivalDateTimeDisplay, finishedDateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_fuel_status, container, false);
        autoCompleteArrivals = rootview.findViewById(R.id.arrival_autocomplete);
        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.list_item_1, arrived_fuel);
        autoCompleteArrivals.setAdapter(adapterItems);

        autoCompleteArrivals.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fu = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(),"Item: " + fu, Toast.LENGTH_SHORT).show();
            }
        });


//        autoCompleteFinished = rootview.findViewById(R.id.finished_autocomplete);
//        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.list_item_2, finished_fuel);
//        autoCompleteFinished.setAdapter(adapterItems);
//
//        autoCompleteFinished.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String fu = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getContext(),"Item: " + fu, Toast.LENGTH_SHORT).show();
//            }
//        });


//        arrivalDateTimeDisplay = (TextView) rootview.findViewById(R.id.arrival_time);
//        finishedDateTimeDisplay = (TextView) rootview.findViewById(R.id.finished_time);
//        calendar = Calendar.getInstance();
//        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        date = dateFormat.format(calendar.getTime());
//        arrivalDateTimeDisplay.setText(date);
//        finishedDateTimeDisplay.setText(date);



        return rootview;

    }


}