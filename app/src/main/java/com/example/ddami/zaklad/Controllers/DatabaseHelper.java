package com.example.ddami.zaklad.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ddami.zaklad.Models.Coupons;
import com.example.ddami.zaklad.Models.Matches;
import com.example.ddami.zaklad.Models.Revenues;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ddami on 26.11.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //database-connection controller

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bookmaker.db";

    private static final String TABLE_NAME = "matches";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_HOME = "home";
    private static final String COLUMN_AWAY = "away";
    private static final String COLUMN_HOMECOURSE = "homecourse";
    private static final String COLUMN_AWAYCOURSE = "awaycourse";
    private static final String COLUMN_DRAWCOURSE = "drawcourse";

    private static final String TABLE_NAME2 = "coupons";
    private static final String COLUMN_ID2 = "id";
    private static final String COLUMN_HOME2 = "home";
    private static final String COLUMN_AWAY2 = "away";
    private static final String COLUMN_COURSE= "course";
    private static final String COLUMN_WINN = "winn";

    private static final String TABLE_NAME3 = "revenues";
    private static final String COLUMN_ID3 = "id";
    private static final String COLUMN_DATA = "data";
    private static final String COLUMN_KWOTA = "kwota";






    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
        + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL," + COLUMN_HOME
        + " TEXT NOT NULL," + COLUMN_AWAY + " TEXT NOT NULL," + COLUMN_HOMECOURSE + " REAL NOT NULL," +COLUMN_AWAYCOURSE + " REAL NOT NULL," +
        COLUMN_DRAWCOURSE + " REAL NOT NULL" +")";

    private static final String TABLE_CREATE2 = "CREATE TABLE IF NOT EXISTS "
        + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL," + COLUMN_HOME
        + " TEXT NOT NULL," + COLUMN_AWAY + " TEXT NOT NULL," + COLUMN_COURSE+ " REAL NOT NULL," +COLUMN_WINN + " REAL NOT NULL" +
        ")";

    private static final String TABLE_CREATE3 = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME3 + "(" + COLUMN_ID3 + " INTEGER PRIMARY KEY NOT NULL," + COLUMN_DATA
            + " NUMERIC NOT NULL," + COLUMN_KWOTA + " NUMERIC NOT NULL" +")";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE2);
        db.execSQL(TABLE_CREATE3);
        this.db = db;
    }

    public void insertMatches(Matches matches){
        db = this.getWritableDatabase();
        ContentValues values3 = new ContentValues();

        String query3 = "select * from matches";
        Cursor cursor3 = db.rawQuery(query3, null);
        int count3 = cursor3.getCount();

        values3.put(COLUMN_ID, count3);
        values3.put(COLUMN_HOME, matches.getHome());
        values3.put(COLUMN_AWAY, matches.getAway());
        values3.put(COLUMN_HOMECOURSE, matches.getCourse_home());
        values3.put(COLUMN_AWAYCOURSE, matches.getCourse_away());
        values3.put(COLUMN_DRAWCOURSE, matches.getCourse_draw());
            db.insert(TABLE_NAME, null, values3);
            db.close();
        }

    public void insertRevenues(Revenues revenues){
        db = this.getWritableDatabase();
        ContentValues values4 = new ContentValues();

        String query4 = "select * from revenues";
        Cursor cursor4 = db.rawQuery(query4, null);
        int count4 = cursor4.getCount();

        values4.put(COLUMN_ID3, count4);
        values4.put(COLUMN_DATA, revenues.getPrzychody());
        values4.put(COLUMN_KWOTA, revenues.getCost());
        db.insert(TABLE_NAME, null, values4);

        long milis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milis);
        if(calendar.get(Calendar.MONTH)>=0){
            int a = calendar.get(Calendar.MONTH);
            int b = a+1;
            values4.put(COLUMN_DATA,calendar.get(Calendar.DAY_OF_MONTH)+"."+b+"."+calendar.get(Calendar.YEAR));

            db.insert(TABLE_NAME3,null,values4);
            db.close();
        }
    }

    public void insertCoupons(Coupons coupons){
        db = this.getWritableDatabase();
        ContentValues values3 = new ContentValues();

        String query3 = "select * from matches";
        Cursor cursor3 = db.rawQuery(query3, null);
        int count3 = cursor3.getCount();

        values3.put(COLUMN_ID, count3);
        values3.put(COLUMN_HOME2, coupons.getHome());
        values3.put(COLUMN_AWAY2, coupons.getAway());
        values3.put(COLUMN_COURSE, coupons.getCourse());
        values3.put(COLUMN_WINN, coupons.getWinn());

        db.insert(TABLE_NAME, null, values3);
        db.close();
    }

    public ArrayList<Matches> getAllMatches() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Matches> matchesArrayList= new ArrayList<Matches>();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME , null);
        while(result.moveToNext()){
            matchesArrayList.add( new Matches(result.getInt(result.getColumnIndex(COLUMN_ID)), result.getString(result.getColumnIndex(COLUMN_HOME)), result.getString(result.getColumnIndex(COLUMN_AWAY)),result.getFloat(result.getColumnIndex(COLUMN_HOMECOURSE)),result.getFloat(result.getColumnIndex(COLUMN_AWAYCOURSE)),result.getFloat(result.getColumnIndex(COLUMN_DRAWCOURSE))));

        }
        return matchesArrayList;
    }

    public ArrayList<Coupons> getAllCoupons() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Coupons> CouponsArrayList = new ArrayList<Coupons>();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME, null);
        while (result.moveToNext()) {
            CouponsArrayList.add(new Coupons(result.getInt(result.getColumnIndex(COLUMN_ID2)), result.getString(result.getColumnIndex(COLUMN_HOME2)), result.getString(result.getColumnIndex(COLUMN_AWAY2)), result.getFloat(result.getColumnIndex(COLUMN_COURSE)), result.getFloat(result.getColumnIndex(COLUMN_WINN))));

        }
        return CouponsArrayList;
    }

        public ArrayList<Revenues> getAllRevenuse() {
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<Revenues> RevenuseArrayList= new ArrayList<Revenues>();
            Cursor result = db.rawQuery("select * from "+TABLE_NAME3 , null);
            while(result.moveToNext()){
                RevenuseArrayList.add( new Revenues(result.getInt(result.getColumnIndex(COLUMN_ID3)),result.getFloat(result.getColumnIndex(COLUMN_KWOTA)),result.getString(result.getColumnIndex(COLUMN_DATA))));

            }
            return RevenuseArrayList;
        }

        public Double getTotalMoney(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select SUM(kwota) FROM "+ TABLE_NAME3,null);
        c.moveToFirst();
        Double i = c.getDouble(0);
        c.close();
        return i;
        }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
            String query2 = "DROP TABLE IF EXISTS" + TABLE_NAME2;
            String query3 = "DROP TABLE IF EXISTS" + TABLE_NAME3;
            db.execSQL(query);
            db.execSQL(query2);
            db.execSQL(query3);
            this.onCreate(db);
        }
    }



}
