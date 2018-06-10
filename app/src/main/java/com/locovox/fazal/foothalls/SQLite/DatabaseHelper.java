package com.locovox.fazal.foothalls.SQLite;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.locovox.fazal.foothalls.Models.MD_Hall;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 09-06-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "football.db";

    //player registration table
    public static final String TABLE_PLAYER = "registerplayer";

    public static final String col_1 = "name";
    public static final String col_2 = "email";
    public static final String col_3 = "age";
    public static final String col_4 = "password";
    public static final String col_5 = "position";


    //hall registration table
    public static final String TABLE_HALLDATA = "registerhall";
    public static final String col_6 = "hallname";
    public static final String col_7 = "hallemail";
    public static final String col_8 = "address";
    public static final String col_9 = "about";
    public static final String col_10 = "hallpassword";
    //hall data table
    public static final String TABLE_HALL = "halldata";
    public static final String col_12 = "hallname";
    public static final String col_13 = "halladdress";
    public static final String col_14 = "hallcapacity";
    public static final String col_15 = "hallreview";
    public static final String col_16 = "hallrating";

    //create statements
    private static final String SQL_REGISTER_PLAYER =" Create TABLE " + TABLE_PLAYER + "(" + col_1 + " TEXT, " + col_2 + " TEXT, " + col_3 + " integer , " + col_4 + " TEXT, " + col_5 + " TEXT  )" ;
    private static final String SQL_HALL_DATA =" Create TABLE " + TABLE_HALL + "(" + col_12 + " TEXT, " + col_13 + " TEXT, " + col_14 + " integer , " + col_15 + " integer, " + col_16 + " float  )" ;
    private static final String SQL_REGISTER_HALL =" Create TABLE " + TABLE_HALLDATA + "(" + col_6 + " TEXT, " + col_7 + " TEXT, " + col_8 + " TEXT , " + col_9 + " TEXT, " + col_10 + " TEXT  )" ;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_REGISTER_PLAYER);
        db.execSQL(SQL_HALL_DATA);
        db.execSQL(SQL_REGISTER_HALL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_PLAYER);
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_HALL);
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_HALLDATA);
        onCreate(db);

    }

    //insert query for player registration
    public boolean insertPlayerRegistration(String name, String email, int age, String pass, String position) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_1, name);
        cv.put(col_2, email);
        cv.put(col_3, age);
        cv.put(col_4, pass);
        cv.put(col_5, position);

        long result = db.insert(TABLE_PLAYER, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    //insert query for hall data
    public boolean insertHallData(String name, String address, int capacity, int reviews, float rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_12, name);
        cv.put(col_13, address);
        cv.put(col_14, capacity);
        cv.put(col_15, reviews);
        cv.put(col_16, rating);

        long result = db.insert(TABLE_HALL, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean insertHallRegisterData(String name, String email, String address, String about, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_6,name);
        cv.put(col_7, email);
        cv.put(col_8, address);
        cv.put(col_9, about);
        cv.put(col_10, password);

        long result = db.insert(TABLE_HALLDATA, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor checkLoginDetailsForPlayers(String email, String password)
    {
        String columns[]={col_2};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query("registerplayer", columns , "email=? and password=?",new String[]{email,password},null,null,null);
        return res;
    }

    public Cursor checkHallLoginDetails(String email,String password)
    {
        String columns[]={col_7};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query("registerhall", columns , "hallemail=? and hallpassword=?",new String[]{email,password},null,null,null);
        return res;
    }

    public List<MD_Hall> retrieveHallData(){
        List<MD_Hall> hallsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_HALL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(selectQuery, null);

        while(res.moveToNext()){
            String name = res.getString(res.getColumnIndex(col_12));
            String addr = res.getString(res.getColumnIndex(col_13));
            String cap = res.getString(res.getColumnIndex(col_14));
            String rev = res.getString(res.getColumnIndex(col_15));
            String rate = res.getString(res.getColumnIndex(col_16));
            MD_Hall model = new MD_Hall();
            model.setName(name);
            model.setAddress(addr);
            model.setTotalCapacity(Integer.parseInt(cap));
            model.setReviewCount(Integer.parseInt(rev));
            model.setRating(Float.parseFloat(rate));
            hallsList.add(model);
        }
        return hallsList;
    }



}


