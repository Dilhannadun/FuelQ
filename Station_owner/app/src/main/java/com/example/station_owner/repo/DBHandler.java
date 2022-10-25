package com.example.station_owner.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.station_owner.model.StationOwner;

import java.io.IOException;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DBNAME = "stationOwner.db";
    public static final String USER_TABLE = "User";

    public DBHandler(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table " + USER_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, nic TEXT NOT NULL, station_id TEXT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL)");
        } catch (SQLiteException e) {
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + USER_TABLE);
    }

    public boolean insertDate(StationOwner stationOwner) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nic", stationOwner.getNic());
        values.put("station_id", stationOwner.getStation_id());
        values.put("email", stationOwner.getEmail());
        values.put("password", stationOwner.getPassword());

        long result = db.insert(USER_TABLE, null, values);

        if (result == -1) return false;
        else return true;

    }

    public boolean checkEmail(StationOwner owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE email=?", new String[]{owner.getEmail()});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean checkEmailAndPassword(StationOwner owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE email=? AND password=?",
                new String[]{owner.getEmail(), owner.getPassword()});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
}
