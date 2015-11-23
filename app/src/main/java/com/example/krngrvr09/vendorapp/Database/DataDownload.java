package com.example.krngrvr09.vendorapp.Database;

import com.example.krngrvr09.vendorapp.api.APIClient;
import com.example.krngrvr09.vendorapp.api.processor.ItemsListResponseProcessor;
import com.example.krngrvr09.vendorapp.api.processor.OrdersListResponseProcessor;

/**
 * Created by krngrvr09 on 23/11/15.
 */
public class DataDownload {

    APIClient client = new APIClient();

    public void downloadItems() {
        client.getmApi().getItems(new ItemsListResponseProcessor());
    }

    public void downloadOrders() {
        client.getmApi().getOrders(false,new OrdersListResponseProcessor());
    }

}
