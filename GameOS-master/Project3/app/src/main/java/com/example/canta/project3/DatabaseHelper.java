package com.example.canta.project3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by canta on 5/21/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gameos.db";
    public static final String TABLE_NAME = "quickquiz_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TOPIC";
    public static final String COL_3 = "NUMBER";
    public static final String COL_4 = "QUESTION";

    public static final String TABLE_IMAGE = "image_table";
    public static final String COLIM_1 = "ID";
    public static final String COLIM_2 = "FLAGNO";
    public static final String COLIM_3 = "IMAGE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,TOPIC TEXT,NUMBER INTEGER, QUESTION TEXT UNIQUE)" );
        db.execSQL("CREATE TABLE " + TABLE_IMAGE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,FLAGNO TEXT UNIQUE, IMAGE BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_IMAGE);
        onCreate(db);
    }

    public boolean insertData (String topic, int number, String question){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,topic);
        contentValues.put(COL_3,number);
        contentValues.put(COL_4,question);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }else{
            Log.d("databaseInsert", "insertData: " + question);
            return true;
        }


    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME ,null);
        return result;
    }

    public boolean insertImage(String flagno, Bitmap image){
        SQLiteDatabase db = this.getWritableDatabase();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        byte[] data = outputStream.toByteArray();
        Log.d("databaseInsert",data.toString());
        ContentValues cv = new ContentValues();
        cv.put(COLIM_2, flagno);
        cv.put(COLIM_3, data);
        long result = db.insert(TABLE_IMAGE,null,cv);
        if(result == -1){
            Log.d("databaseInsert", "insertImage failed");
            return false;
        }else{
            Log.d("databaseInsert", "inserted Flag " + flagno);
            return true;
        }
    }

    public Cursor getImage(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_IMAGE ,null);
        return cursor;
    }
}
