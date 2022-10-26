//IT19149318
//This Activity is the forgot password dialog


package com.example.station_owner.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.station_owner.R;
import com.example.station_owner.activities.MainActivity;
import com.example.station_owner.model.StationOwner;
import com.example.station_owner.repo.DBHandler;
import com.google.android.material.textfield.TextInputEditText;

public class FpDialog extends AppCompatDialogFragment {

    private TextInputEditText phone, stationId, newPassword;
    private FpDialogListener fpDialogListener;

    public Dialog onCreateDialog(Bundle saveInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fp_dialog, null);

        builder.setView(view).setTitle("").setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("RESET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StationOwner owner = new StationOwner();
                owner.setPhone(phone.getText().toString());
                owner.setStation_id(stationId.getText().toString());
                owner.setPassword(newPassword.getText().toString());

                DBHandler dbHandler = new DBHandler(getContext());

                if (TextUtils.isEmpty(owner.getPhone()) || TextUtils.isEmpty(owner.getPassword()) || TextUtils.isEmpty(owner.getStation_id())) {
                    Toast.makeText(getContext(), "Fields Can not be Empty !", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        boolean isReset = dbHandler.resetPassword(owner);
                        if (isReset) {
                            Toast.makeText(getContext(), "Password Reset Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Password Reset Failed!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLiteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        phone = view.findViewById(R.id.rp_nic_edit);
        stationId = view.findViewById(R.id.rp_si_edit);
        newPassword = view.findViewById(R.id.rp_password_edit);


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            fpDialogListener = (FpDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Must implement FpDialogListener");
        }

    }
}
