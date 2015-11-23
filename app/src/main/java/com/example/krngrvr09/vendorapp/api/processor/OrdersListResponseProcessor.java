package com.example.krngrvr09.vendorapp.api.processor;

import android.util.Log;

import com.example.krngrvr09.vendorapp.Models.Order;
import com.example.krngrvr09.vendorapp.api.protocol.OrdersResponseList;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Manan Wason on 15/11/15.
 */
public class OrdersListResponseProcessor implements Callback<OrdersResponseList> {
    private static final String TAG = "Orders";

    @Override
    public void success(final OrdersResponseList ordersResponseList, Response response) {
        Log.d("retro", "success");
        ArrayList<String> queries = new ArrayList<String>();

        for (Order order : ordersResponseList.orders) {
            String query = order.generateSql();
            Log.d("retro order", order.getOrderId() + "");
            queries.add(query);
            Log.d(TAG, query);
        }

        DbSingleton dbSingleton = DbSingleton.getInstance();
        dbSingleton.clearDatabase(DbContract.Orders.TABLE_NAME);
        dbSingleton.insertQueries(queries);

        UserApp.postEventOnUIThread(new OrderDownloadDoneEvent(true));

        Log.d("retro", "success");
    }

    @Override
    public void failure(RetrofitError error) {
        UserApp.postEventOnUIThread(new OrderDownloadDoneEvent(false));

        //TODO: PREVENT FROM CRASHING
        Log.d("retro", error.getCause().toString());

    }
}
