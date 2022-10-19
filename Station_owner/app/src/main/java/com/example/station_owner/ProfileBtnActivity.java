package com.example.station_owner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ProfileBtnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_btn);
    }

    public void getProfileMenu (){
        ImageButton button = findViewById(R.id.profile_img_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(getApplicationContext(), button);

                popup.getMenuInflater()
                        .inflate(R.menu.profile_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.profile_item){
                            Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(
                                    ProfileBtnActivity.this,
                                    "Logging Out",
                                    Toast.LENGTH_SHORT
                            ).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }

                        return true;
                    }
                });

                popup.show();
            }
        });
    }
}