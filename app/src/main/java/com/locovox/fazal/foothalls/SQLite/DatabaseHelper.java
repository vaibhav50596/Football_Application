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
    public static final String TABLE_NAME = "registerplayer";
    public static final String TABLE_HALLDATA = "registerhall";

    public static final String col_1 = "name";
    public static final String col_2 = "email";
    public static final String col_3 = "age";
    public static final String col_4 = "password";
    //public static final String role="Role";
    public static final String col_5 = "position";
    public static final String col_6 = "hallname";
    public static final String col_7 = "hallemail";
    public static final String col_8 = "address";
    public static final String col_9 = "about";
    public static final String col_10 = "hallpassword";
    private static final String SQL_REGISTER =" Create TABLE " + TABLE_NAME + "(" + col_1 + " TEXT, " + col_2 + " TEXT, " + col_3 + " integer , " + col_4 + " TEXT, " + col_5 + " TEXT  )" ;
    private static final String SQL_REGISTER_HALL =" Create TABLE " + TABLE_HALLDATA + "(" + col_6 + " TEXT, " + col_7 + " TEXT, " + col_8 + " TEXT , " + col_9 + " TEXT, " + col_10 + " TEXT  )" ;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_REGISTER);
        db.execSQL(SQL_REGISTER_HALL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_HALLDATA);
                onCreate(db);

    }

    public boolean insertdata(String name, String email, int age, String pass, String position) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_1,name);
        cv.put(col_2, email);
        cv.put(col_3, age);
        cv.put(col_4, pass);
        cv.put(col_5, position);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertHallData(String name, String email, String address, String about, String password) {
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





    public Cursor checkLoginDetails(String email,String password)
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



}


