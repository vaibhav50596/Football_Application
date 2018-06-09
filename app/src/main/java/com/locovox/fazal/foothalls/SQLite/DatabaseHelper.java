package com.locovox.fazal.foothalls.SQLite;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


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


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_REGISTER_PLAYER);
        db.execSQL(SQL_HALL_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_PLAYER);
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_HALL);
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



    public Cursor checkLoginDetailsForPlayers(String email, String password)
    {
        String columns[]={col_2};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query("registerplayer", columns , "email=? and password=?",new String[]{email,password},null,null,null);
        return res;
    }


}


