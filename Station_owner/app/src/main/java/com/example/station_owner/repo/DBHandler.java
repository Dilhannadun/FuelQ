package com.example.station_owner.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.station_owner.model.StationOwner;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DBNAME = "stationOwner.db";
    public DBHandler(Context context) {
        super(context, "stationOwner.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table stationOwner(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " staion_id TEXT, email TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists stationOwner");
    }

    public boolean insertDate(StationOwner stationOwner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", stationOwner.getId());
        values.put("nic", stationOwner.getNic());
        values.put("email", stationOwner.getEmail());
        values.put("password", stationOwner.getPassword());

        long result = db.insert("stationOwner",null,values);

        if(result == -1) return false;
        else return true;

    }

    public boolean checkEmail(StationOwner owner){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM stationOwner WHERE email=?", new String[] {owner.getEmail()});
        if(cursor.getCount() > 0) {
            return true;
        }
            return false;
    }

    public boolean checkEmailAndPassword(StationOwner owner){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM stationOwner WHERE email=? AND password=?",
                new String[] {owner.getEmail(), owner.getPassword()});
        if(cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
}
