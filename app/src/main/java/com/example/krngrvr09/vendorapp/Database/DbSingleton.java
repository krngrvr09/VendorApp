package com.example.krngrvr09.vendorapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.krngrvr09.vendorapp.Models.Item;
import com.example.krngrvr09.vendorapp.Models.Order;

import java.util.ArrayList;


/**
 * Created by Manan Wason on 15/11/15.
 */
public class DbSingleton {

    private static DbSingleton mInstance;

    private static Context mContext;

    private static DbHelper mDbHelper;

    private SQLiteDatabase mDb;
    private DatabaseOperations databaseOperations = new DatabaseOperations();

    private DbSingleton(Context context) {
        mContext = context;
        mDbHelper = new DbHelper(mContext);
        Log.d("download3", "db");


    }

    public static void init(Context context) {
        if (mInstance == null) {
            mInstance = new DbSingleton(context);
            Log.d("download1", "db");
        }
    }

    public static DbSingleton getInstance() {
        return mInstance;
    }

    private void getReadOnlyDatabase() {
        if ((mDb == null) || (!mDb.isReadOnly())) {
            mDb = mDbHelper.getReadableDatabase();
        }
    }



    public ArrayList<Item> getItemsList() {
        getReadOnlyDatabase();
        return databaseOperations.getItemsList(mDb);

    }

    public ArrayList<Order> getCompletedOrdersList() {
        getReadOnlyDatabase();
        return databaseOperations.getCompletedOrderList(mDb); // revisit user id
    }

    public ArrayList<Order> getPendingOrdersList() {
        getReadOnlyDatabase();
        return databaseOperations.getPendingOrderList(mDb, 1);   //revisit user id
    }


    public void insertQueries(ArrayList<String> queries) {
        databaseOperations.insertQueries(queries, mDbHelper);
    }

    public void insertQuery(String query) {
        databaseOperations.insertQuery(query, mDbHelper);
    }

    public void clearDatabase(String table) {
        databaseOperations.clearDatabaseTable(table, mDbHelper);
    }

    public void deleteAllRecords(String tableName) {
        databaseOperations.deleteAllRecords(tableName, mDb);
    }

    public void deleteRecord(String tableName, String condition) {
        databaseOperations.deleteRecord(tableName, condition, mDb);
    }
}
