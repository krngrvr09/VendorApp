package com.example.krngrvr09.vendorapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by krngrvr09 on 26/10/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "vendor_database";
    private static final String TABLE_NAME = "INVENTORY";
    private static final String UID = "_id";
    private static final String NAME = "Name";
    private static final String QUANTITY = "Quantity";
    private static final String PRICE = "Price";
    private static final String URL = "Url";



    private static final int DATABASE_VERSION = 1;
     private static final String CREATE_TABLE="CREATE TABLE "+ TABLE_NAME +"("+UID+" INTEGER PRIMARY_KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+QUANTITY+" INTEGER, "+PRICE+" INTEGER, "+URL+" VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
