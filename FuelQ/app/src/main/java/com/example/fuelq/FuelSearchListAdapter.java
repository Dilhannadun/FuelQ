package com.example.fuelq;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelq.models.Shed;

import java.util.List;

public class FuelSearchListAdapter extends RecyclerView.Adapter<FuelSearchListAdapter.ViewHolder> {

    private List<Shed> sheds;

    public FuelSearchListAdapter(List<Shed> shedList) {
        sheds = shedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.fuel_list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shed shed = sheds.get(position);

        TextView shedNameText = holder.shedName;
        TextView shedLocationText = holder.shedLocation;
        Button navToShedBtn = holder.navigateToShedInfo;

        shedNameText.setText(shed.getShedName());
        shedLocationText.setText(shed.getShedLocation());
        navToShedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FuelStationInfo.class);
                intent.putExtra("shed", shed);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sheds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView shedName;
        public TextView shedLocation;
        public Button navigateToShedInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shedName = itemView.findViewById(R.id.txt_ShedName);
            shedLocation = itemView.findViewById(R.id.txt_ShedLocation);
            navigateToShedInfo = itemView.findViewById(R.id.btn_SelectShed);
        }
    }
}
