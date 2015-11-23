package com.example.krngrvr09.vendorapp.api.protocol;

import com.example.krngrvr09.vendorapp.Models.Order;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by Manan Wason on 23/11/15.
 */
public class OrdersResponseList {
    @SerializedName("orders")
    public List<Order> orders;
}
