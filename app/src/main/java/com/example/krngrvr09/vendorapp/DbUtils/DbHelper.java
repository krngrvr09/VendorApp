package com.example.krngrvr09.vendorapp.DbUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Manan Wason on 15/11/15.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "CReated");
        db.execSQL(DbContract.Items.CREATE_TABLE);
        db.execSQL(DbContract.Orders.CREATE_TABLE);
        db.execSQL(DbContract.Cart.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbContract.Items.DELETE_TABLE);
        db.execSQL(DbContract.Orders.DELETE_TABLE);
        db.execSQL(DbContract.Cart.DELETE_TABLE);
    }
}
