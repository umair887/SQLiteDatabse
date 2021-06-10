package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "MySQLite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE Students (id integer primary key autoincrement, name text, rollNumber text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean AddStudent(String name, String rollNumber)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("rollNumber",rollNumber);
        if(db.insert("Students",null,cv)==-1) {
            return false;
        }
        return true;
    }

    public ArrayList<String> GetStudentsData()
    {
        ArrayList<String> studensData = new ArrayList<String>();
        String query = "SELECT * FROM Students";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do {
                String sName = cursor.getString(1);
                String rolno = cursor.getString(2);
                studensData.add("Student Name is " + sName + " and Roll Number is " + rolno);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studensData;
    }
}
