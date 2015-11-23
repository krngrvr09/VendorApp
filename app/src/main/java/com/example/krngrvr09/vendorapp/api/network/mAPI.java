package com.example.krngrvr09.vendorapp.api.network;

import com.example.krngrvr09.vendorapp.Models.Order;
import com.example.krngrvr09.vendorapp.api.protocol.ItemsResponseList;
import com.example.krngrvr09.vendorapp.api.protocol.OrdersResponseList;
import com.example.krngrvr09.vendorapp.api.protocol.UserResponse;
import com.example.krngrvr09.vendorapp.api.protocol.newOrderResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by MananWason on 23-11-2015.
 */

public interface mAPI {

    @GET("/orders")
    void getOrders(@Query("status") boolean status, Callback<OrdersResponseList> ordersResponseListCallback);

    //TODO: ADD REQUESTS HERE

    @POST("/orders")
    void createItem(@Body Order order, Callback<newOrderResponse> callback);

    @GET("/users")
    void getUsers(Callback<UserResponse> userResponseCallback);

    @GET("/items")
    void getItems(Callback<ItemsResponseList> itemsResponseListCallback);

}
