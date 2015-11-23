package com.example.krngrvr09.vendorapp.api.protocol;

import com.example.krngrvr09.vendorapp.Models.Order;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Manan Wason on 23/11/15.
 */
public class newOrderResponse {
    @SerializedName("id")
    public Order order;

}


