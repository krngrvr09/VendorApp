package com.example.krngrvr09.vendorapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by krngrvr09 on 26/10/15.
 */
public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("download2", "db");
        db.execSQL(DbContract.Items.CREATE_TABLE);
        db.execSQL(DbContract.Orders.CREATE_TABLE);
        Log.d("download", "db");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DbContract.Items.DELETE_TABLE);
        db.execSQL(DbContract.Orders.DELETE_TABLE);

    }
}
