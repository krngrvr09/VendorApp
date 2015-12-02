package com.example.krngrvr09.vendorapp.Helpers;

import android.util.Log;

import com.example.krngrvr09.vendorapp.Events.CounterEvent;
import com.example.krngrvr09.vendorapp.VendorApp;
import com.example.krngrvr09.vendorapp.api.APIClient;
import com.example.krngrvr09.vendorapp.api.processor.ItemsListResponseProcessor;
import com.example.krngrvr09.vendorapp.api.processor.OrdersListResponseProcessor;


/**
 * Created by Manan Wason on 14/11/15.
 */
public class DataDownload {

    APIClient client = new APIClient();
    private int counter;

    public void downloadItems() {
        counter = 0;
        client.getmApi().getItems(new ItemsListResponseProcessor());
        counter++;
        Log.d("download1", counter+"");

        VendorApp.postEventOnUIThread(new CounterEvent(counter));

    }

    public void downloadOrders() {
        counter = 0;
        client.getmApi().getOrders(new OrdersListResponseProcessor());
        counter++;
        Log.d("download", counter+"");
        VendorApp.postEventOnUIThread(new CounterEvent(counter));

    }

    public void downloadAll() {
        counter = 0;
        client.getmApi().getOrders(new OrdersListResponseProcessor());
        client.getmApi().getItems(new ItemsListResponseProcessor());
        counter += 2;
        Log.d("download", counter+"");
        VendorApp.postEventOnUIThread(new CounterEvent(counter));

    }


}
