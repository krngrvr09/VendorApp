package com.example.krngrvr09.vendorapp.api.network;

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
 * Created by MananWason on 14-11-2015.
 */

public interface mAPI {

    @GET("/items.json")
    void getItems(Callback<ItemsResponseList> itemsResponseListCallback);

    @GET("/orders.json")
    void getOrders(Callback<OrdersResponseList> ordersResponseListCallback);

    //TODO: ADD REQUESTS HERE

//    @POST("/orders")
//    void createOrder(@Body Order order, Callback<newOrderResponse> callback);

    @GET("/get_id")
    void getUserId(@Query("name") String name, @Query("email") String email, Callback<UserResponse> userResponseCallback);

}
