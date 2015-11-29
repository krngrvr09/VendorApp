package com.example.krngrvr09.vendorapp.Database;

import android.provider.BaseColumns;

/**
 * Created by Manan Wason on 14/11/15.
 */
public class DbContract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Vendor.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String PRIMARY_KEY = " PRIMARY KEY";

    public DbContract() {
        //Empty constructor to prevent object creation.
    }

    public static abstract class Items implements BaseColumns {
        public static final String TABLE_NAME = "items";
        public static final String ITEM_ID = "itemId";
        public static final String ITEM_NAME = "itemName";
        public static final String QUANTITY_ORDERED = "quantityOrdered";
        public static final String IMAGE_URL = "imageUrl";
        public static final String CONTENTS = "contents";
        public static final String PRICE = "price";
        public static final String RATING = "rating";
        public static final String QUANTITY_AVAILABLE = "qtyAvailable";

        public static final String[] FULL_PROJECTION = {
                ITEM_ID,
                ITEM_NAME,
                QUANTITY_ORDERED,
                IMAGE_URL,
                CONTENTS,
                PRICE,
                RATING,
                QUANTITY_AVAILABLE
        };
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME
                        + " ("
                        + ITEM_ID + INT_TYPE + PRIMARY_KEY + COMMA_SEP
                        + ITEM_NAME + TEXT_TYPE + COMMA_SEP
                        + QUANTITY_ORDERED + REAL_TYPE + COMMA_SEP
                        + IMAGE_URL + TEXT_TYPE + COMMA_SEP
                        + CONTENTS + TEXT_TYPE + COMMA_SEP
                        + PRICE + REAL_TYPE + COMMA_SEP
                        + RATING + REAL_TYPE + COMMA_SEP
                        + QUANTITY_AVAILABLE + REAL_TYPE
                        + " );";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public static abstract class Orders implements BaseColumns {
        public static final String TABLE_NAME = "orders";
        public static final String ORDER_ID = "orderId";
        public static final String USER_ID = "userId";
        public static final String ORDER_NAME = "orderName";
        public static final String ITEMS = "items";
        public static final String TIME = "timeOfOrder";
        public static final String COST = "cost";
        public static final String IS_ORDER_COMPLETED = "isOrderCompleted";
        public static final String IS_PAYMENT_DONE = "isPaymentDone";

        public static final String[] FULL_PROJECTION = {
                ORDER_ID,
                USER_ID,
                ITEMS,
                TIME,
                COST,
                IS_ORDER_COMPLETED,
                IS_PAYMENT_DONE
        };
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME
                        + " ("
                        + ORDER_ID + INT_TYPE + PRIMARY_KEY + COMMA_SEP
                        + USER_ID + INT_TYPE + COMMA_SEP
                        + ITEMS + TEXT_TYPE + COMMA_SEP
                        + TIME + TEXT_TYPE + COMMA_SEP
                        + COST + INT_TYPE + COMMA_SEP
                        + IS_PAYMENT_DONE + " BOOLEAN NOT NULL CHECK (" + IS_PAYMENT_DONE + " IN (0,1))"+COMMA_SEP
                        + IS_ORDER_COMPLETED + " BOOLEAN NOT NULL CHECK (" + IS_ORDER_COMPLETED + " IN (0,1))"
                        + " );";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    }

    public static abstract class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String USER_ID = "userId";
        public static final String USER_NAME = "userName";
        public static final String CREATION_TIME = "creationTime";

        public static final String[] FULL_PROJECTION = {
                USER_ID,
                USER_NAME,
                CREATION_TIME
        };
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME
                        + " ("
                        + USER_ID + INT_TYPE + PRIMARY_KEY + COMMA_SEP
                        + USER_NAME + TEXT_TYPE + COMMA_SEP
                        + CREATION_TIME + TEXT_TYPE
                        + " );";

    }
}
