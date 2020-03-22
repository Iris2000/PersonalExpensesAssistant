package com.example.testing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
//        context.deleteDatabase("Login.db");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(id integer primary key autoincrement," +
                                     "username text not null," +
                                     "email text not null," +
                                     "password text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }
    // inserting in database
    public boolean insert(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if (ins == -1)
            return false;
        else
            return true;
    }

    // checking if email exists;
    public Boolean checkMail (String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }
    // checking if username exists;
    public Boolean checkUsername (String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }
    // checking if account exists;
    public Boolean checkAcc (String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ? AND password = ?", new String[]{email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    // get username if login successfully;
    public String getUsername (String email) {
        String username = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email ='" + email + "'", null);
        if (cursor.moveToFirst()) {
            username = cursor.getString(1);
        }
        cursor.close();
        return username;
    }
}
