//IT19149318
//This DH handler class use to connect to Sqlite DB and handle its methods


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
            db.execSQL("create table " + USER_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, nic TEXT NOT NULL, station_id TEXT NOT NULL, phone TEXT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL)");
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

    //This is the owner registration method
    public boolean registerOwner(StationOwner stationOwner) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nic", stationOwner.getNic());
        values.put("station_id", stationOwner.getStation_id());
        values.put("phone", stationOwner.getPhone());
        values.put("email", stationOwner.getEmail());
        values.put("password", stationOwner.getPassword());

        long result = db.insert(USER_TABLE, null, values);

        if (result == -1) return false;
        else return true;

    }

    //This method will check if NIC is already exists
    public boolean checkNic(StationOwner owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE nic=?", new String[]{owner.getNic()});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    //This method will check if station id is already exists
    public boolean checkStation(StationOwner owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE station_id=?", new String[]{owner.getStation_id()});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    //This method will check if phone is already exists
    public boolean checkPhone(StationOwner owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE phone=?", new String[]{owner.getPhone()});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    //This method will check if email is already exists
    public boolean checkEmail(StationOwner owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE email=?", new String[]{owner.getEmail()});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    //This method will check if the login credentials are correct
    public boolean checkPhoneAndPassword(StationOwner owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE phone=? AND password=?",
                new String[]{owner.getPhone(), owner.getPassword()});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    //This method will update the password
    public boolean resetPassword(StationOwner owner) {
        boolean cPhone = checkPhone(owner);
        boolean cStation = checkStation(owner);
        if (cPhone && cStation) {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("UPDATE " + USER_TABLE + " SET password = "+ owner.getPassword() +" WHERE phone=? AND station_id=?",
                    new String[]{owner.getPhone(), owner.getStation_id()});
            if (cursor.getCount() > 0) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
}
