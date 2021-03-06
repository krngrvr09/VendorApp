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


    public ArrayList<Order> getCompletedOrderList(SQLiteDatabase mDb) {
        String selection = DbContract.Orders.IS_ORDER_COMPLETED + EQUAL + " '1'" + " and " +
                DbContract.Orders.IS_PAYMENT_DONE + EQUAL + " '1'";

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

        ArrayList<Item> itemsInOrder = new ArrayList<>();

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            String[] itemIdsStringArray = cur.getString(cur.getColumnIndex(DbContract.Orders.ITEMS)).split(",");
            for (String s : itemIdsStringArray) {
                String selectionItem = DbContract.Items.ITEM_ID + EQUAL + Integer.valueOf(s);
                Log.d("abc3", selectionItem);

                String sortItem = DbContract.Items.ITEM_ID + ASCENDING;
                Cursor curItem = mDb.query(
                        DbContract.Items.TABLE_NAME,
                        DbContract.Items.FULL_PROJECTION,
                        selectionItem,
                        null,
                        null,
                        null,
                        sortItem
                );
                Log.d("abc2", itemsInOrder.size() + " " + curItem.getCount());

                curItem.moveToFirst();
                while (!curItem.isAfterLast()) {
                    Log.d("abc2", itemsInOrder.size() + "");

                    Item item;
                    item = new Item(
                            curItem.getString(curItem.getColumnIndex(DbContract.Items.ITEM_NAME)),
                            curItem.getInt(curItem.getColumnIndex(DbContract.Items.ITEM_ID)),
                            curItem.getString(curItem.getColumnIndex(DbContract.Items.CONTENTS)),
                            curItem.getInt(curItem.getColumnIndex(DbContract.Items.PRICE)),
                            curItem.getInt(curItem.getColumnIndex(DbContract.Items.QUANTITY_ORDERED)),
                            curItem.getString(curItem.getColumnIndex(DbContract.Items.IMAGE_URL)),
                            curItem.getInt(curItem.getColumnIndex(DbContract.Items.RATING)));
                    itemsInOrder.add(item);
                    Log.d("abc2", itemsInOrder.size() + "");
                    curItem.moveToNext();
                }
                curItem.close();
            }


            temp = new Order(
                    cur.getInt(cur.getColumnIndex(DbContract.Orders.ORDER_ID)),
                    cur.getInt(cur.getColumnIndex(DbContract.Orders.USER_ID)),
                    itemsInOrder,
                    cur.getString(cur.getColumnIndex(DbContract.Orders.TIME)),
                    cur.getInt(cur.getColumnIndex(DbContract.Orders.COST)),
                    cur.getInt(cur.getColumnIndex(DbContract.Orders.IS_ORDER_COMPLETED)) > 0,
                    cur.getInt(cur.getColumnIndex(DbContract.Orders.IS_PAYMENT_DONE)) > 0);
            orders.add(temp);
            Log.d("abc1", temp.getItems().size() + "");

            cur.moveToNext();
        }
        cur.close();
        return orders;
    }


    public ArrayList<Order> getPendingOrderList(SQLiteDatabase mDb) {
        String selection = DbContract.Orders.IS_PAYMENT_DONE + EQUAL + " '0' or (" + DbContract.Orders.IS_PAYMENT_DONE + EQUAL +
                " '1' and " + DbContract.Orders.IS_ORDER_COMPLETED + EQUAL + " '0')";

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

        ArrayList<Item> itemsInOrder = new ArrayList<>();

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            if ((cur.getString(cur.getColumnIndex(DbContract.Orders.ITEMS))) != null && !(cur.getString(cur.getColumnIndex(DbContract.Orders.ITEMS)).isEmpty())) {
                String[] itemIdsStringArray = cur.getString(cur.getColumnIndex(DbContract.Orders.ITEMS)).split(",");
                Log.d("items abc", itemIdsStringArray.length + "");


                for (String s : itemIdsStringArray) {
                    String selectionItem = DbContract.Items.ITEM_ID + EQUAL + Integer.valueOf(s);

                    String sortItem = DbContract.Items.ITEM_ID + ASCENDING;
                    Cursor curItem = mDb.query(
                            DbContract.Items.TABLE_NAME,
                            DbContract.Items.FULL_PROJECTION,
                            selectionItem,
                            null,
                            null,
                            null,
                            sortItem
                    );

                    curItem.moveToFirst();
                    while (!curItem.isAfterLast()) {

                        Item item;
                        item = new Item(
                                curItem.getString(curItem.getColumnIndex(DbContract.Items.ITEM_NAME)),
                                curItem.getInt(curItem.getColumnIndex(DbContract.Items.ITEM_ID)),
                                curItem.getString(curItem.getColumnIndex(DbContract.Items.CONTENTS)),
                                curItem.getInt(curItem.getColumnIndex(DbContract.Items.PRICE)),
                                curItem.getInt(curItem.getColumnIndex(DbContract.Items.QUANTITY_ORDERED)),
                                curItem.getString(curItem.getColumnIndex(DbContract.Items.IMAGE_URL)),
                                curItem.getInt(curItem.getColumnIndex(DbContract.Items.RATING)));
                        itemsInOrder.add(item);
                        curItem.moveToNext();
                    }
                    Log.d("items abc1", itemsInOrder.size() + "");

                    curItem.close();
                }
            }


            temp = new Order(
                    cur.getInt(cur.getColumnIndex(DbContract.Orders.ORDER_ID)),
                    cur.getInt(cur.getColumnIndex(DbContract.Orders.USER_ID)),
                    itemsInOrder,
                    cur.getString(cur.getColumnIndex(DbContract.Orders.TIME)),
                    cur.getInt(cur.getColumnIndex(DbContract.Orders.COST)),
                    cur.getInt(cur.getColumnIndex(DbContract.Orders.IS_ORDER_COMPLETED)) > 0,
                    cur.getInt(cur.getColumnIndex(DbContract.Orders.IS_PAYMENT_DONE)) > 0);
            orders.add(temp);

            cur.moveToNext();
        }
        cur.close();
        return orders;
    }

    public Item getItemById(SQLiteDatabase mDb, int itemId) {
        String selection = DbContract.Items.ITEM_ID + EQUAL + " '" + itemId + "' ";
        String sortOrder = DbContract.Items.ITEM_ID + ASCENDING;
        Cursor cur = mDb.query(
                DbContract.Items.TABLE_NAME,
                DbContract.Items.FULL_PROJECTION,
                selection,
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
                    itemId,
                    cur.getString(cur.getColumnIndex(DbContract.Items.CONTENTS)),
                    cur.getInt(cur.getColumnIndex(DbContract.Items.PRICE)),
                    cur.getInt(cur.getColumnIndex(DbContract.Items.QUANTITY_AVAILABLE)),
                    cur.getString(cur.getColumnIndex(DbContract.Items.IMAGE_URL)),
                    cur.getInt((cur.getColumnIndex(DbContract.Items.RATING)))
            );
            items.add(temp);
        }
        cur.close();
        if (items.size() > 0)
            return items.get(0);
        else
            return null;
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
