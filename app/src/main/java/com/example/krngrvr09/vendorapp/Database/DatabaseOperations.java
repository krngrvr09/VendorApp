package com.example.krngrvr09.vendorapp.Database;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.krngrvr09.vendorapp.Models.Item;
import com.example.krngrvr09.vendorapp.Models.Order;

import java.util.ArrayList;


/**
 * Created by Manan Wason on 15/11/15.
 */
public class DatabaseOperations {
    private static final String ASCENDING = " ASC";

    private static final String DESCENDING = " DESC";

    private static final String SELECT_ALL = "SELECT * FROM ";

    private static final String WHERE = " WHERE ";

    private static final String EQUAL = " == ";

    public ArrayList<Item> getItemsList(SQLiteDatabase mDb) {

        String sortOrder = DbContract.Items.ITEM_ID + ASCENDING;
        Cursor cur = mDb.query(
                DbContract.Items.TABLE_NAME,
                DbContract.Items.FULL_PROJECTION,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Item> items = new ArrayList<>();
        Item temp;

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            temp = new Item(
                    cur.getString(cur.getColumnIndex(DbContract.Items.ITEM_NAME)),
                    cur.getInt(cur.getColumnIndex(DbContract.Items.ITEM_ID)),
                    cur.getString(cur.getColumnIndex(DbContract.Items.CONTENTS)),
                    cur.getInt(cur.getColumnIndex(DbContract.Items.PRICE)),
                    cur.getInt(cur.getColumnIndex(DbContract.Items.QUANTITY_AVAILABLE)),
                    cur.getString(cur.getColumnIndex(DbContract.Items.IMAGE_URL)),
                    cur.getInt(cur.getColumnIndex(DbContract.Items.RATING))
            );
            items.add(temp);
            cur.moveToNext();
        }
        cur.close();
        return items;
    }


    public ArrayList<Order> getCompletedOrderList(SQLiteDatabase mDb, int userId) {
        String selection = DbContract.Orders.USER_ID + EQUAL + userId + " and " +
                DbContract.Orders.IS_ORDER_COMPLETED + EQUAL + " '1'";

        String sortOrder = DbContract.Orders.ORDER_ID + ASCENDING;
        Cursor cur = mDb.query(
                DbContract.Orders.TABLE_NAME,
                DbContract.Orders.FULL_PROJECTION,
                selection,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Order> orders = new ArrayList<>();
        Order temp;

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
//            temp = new Order(
//                    cur.getInt(cur.getColumnIndex(DbContract.Orders.ORDER_ID)),
//                    cur.getInt(cur.getColumnIndex(DbContract.Orders.USER_ID)),
//                    cur.getString(cur.getColumnIndex(DbContract.Orders.ITEMS)),
//                    cur.getString(cur.getColumnIndex(DbContract.Orders.TIME)),
//                    cur.getInt(cur.getColumnIndex(DbContract.Orders.COST)),
//                    cur.getInt(cur.getColumnIndex(DbContract.Orders.IS_ORDER_COMPLETED))>0 ,
//                    cur.getInt(cur.getColumnIndex(DbContract.Orders.IS_PAYMENT_DONE)) >0);
//            orders.add(temp);
//            cur.moveToNext();
        }
        cur.close();
        return orders;
    }

    public ArrayList<Order> getPendingOrderList(SQLiteDatabase mDb, int userId) {
        String selection = DbContract.Orders.USER_ID + EQUAL + userId + " and " +
                DbContract.Orders.IS_ORDER_COMPLETED + EQUAL + " '1'";

        String sortOrder = DbContract.Orders.ORDER_ID + ASCENDING;
        Cursor cur = mDb.query(
                DbContract.Orders.TABLE_NAME,
                DbContract.Orders.FULL_PROJECTION,
                selection,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Order> orders = new ArrayList<>();
        Order temp;

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
//            temp = new Order(
//                    cur.getInt(cur.getColumnIndex(DbContract.Orders.ORDER_ID)),
//                    cur.getInt(cur.getColumnIndex(DbContract.Orders.USER_ID)),
//                    cur.getString(cur.getColumnIndex(DbContract.Orders.ITEMS)),
//                    cur.getString(cur.getColumnIndex(DbContract.Orders.TIME)),
//                    cur.getInt(cur.getColumnIndex(DbContract.Orders.COST)),
//                    cur.getInt(cur.getColumnIndex(DbContract.Orders.IS_ORDER_COMPLETED))>0,
//                    cur.getInt(cur.getColumnIndex(DbContract.Orders.IS_PAYMENT_DONE))>0);
//            orders.add(temp);
            cur.moveToNext();
        }
        cur.close();
        return orders;
    }


    public void insertQuery(String query, DbHelper mDbHelper) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL(query);

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void deleteAllRecords(String tableName, SQLiteDatabase db) {

        db.execSQL("delete from " + DatabaseUtils.sqlEscapeString(tableName));
    }

    public void deleteRecord(String tableName, String condition, SQLiteDatabase db) {

        db.execSQL("delete from " + DatabaseUtils.sqlEscapeString(tableName) + " where " + condition);
    }


    public void insertQueries(ArrayList<String> queries, DbHelper mDbHelper) {
        Log.d("retro query", "insert");

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.beginTransaction();
        for (String query : queries) {
            Log.d("retro query", query);

            db.execSQL(query);
        }
        Log.d("retro query", "end");

        db.setTransactionSuccessful();
        db.endTransaction();
    }


    public void clearDatabaseTable(String table, DbHelper mDbHelper) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.beginTransaction();
        try {

            db.delete(table, null, null);

            db.setTransactionSuccessful();

        } finally {
            db.endTransaction();


        }
    }

}
