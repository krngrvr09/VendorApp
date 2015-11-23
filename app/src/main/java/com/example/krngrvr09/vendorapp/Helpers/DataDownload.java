package com.example.krngrvr09.vendorapp.Helpers;

import com.example.krngrvr09.vendorapp.api.APIClient;
import com.example.krngrvr09.vendorapp.api.processor.ItemsListResponseProcessor;
import com.example.krngrvr09.vendorapp.api.processor.OrdersListResponseProcessor;


/**
 * Created by Manan Wason on 14/11/15.
 */
public class DataDownload {

    APIClient client = new APIClient();

    public void downloadItems() {
        client.getmApi().getItems(new ItemsListResponseProcessor());
    }

    public void downloadOrders(boolean status) {
        client.getmApi().getOrders(status, new OrdersListResponseProcessor());

    }

}
