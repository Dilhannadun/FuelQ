package com.example.fuelq.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.fuelq.models.User;

// Class to handle SQLite operations
public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "fuelq";
    // below int is our database version
    private static final int DB_VERSION = 1;
    // below variable is for our table name.
    private static final String TABLE_NAME = "users";
    // below variable is for our id column.
    private static final String ID_COL = "_id";

    // below variable is for our course name column
    private static final String EMAIL_COL = "email";

    // below variable id for our course duration column.
    private static final String PASSWORD_COL = "password";

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " TEXT PRIMARY KEY, "
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addNewUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COL, user.getId().toString());
        values.put(EMAIL_COL, user.getEmail().toString());
        values.put(PASSWORD_COL, user.getPassword().toString());

        long val = db.insert(TABLE_NAME, null, values);
        System.out.println(val);
        db.close();
    }
}
