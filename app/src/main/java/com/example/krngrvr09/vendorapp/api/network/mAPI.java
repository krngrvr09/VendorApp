package com.example.krngrvr09.vendorapp.api.network;

import com.example.krngrvr09.vendorapp.Models.Item;
import com.example.krngrvr09.vendorapp.api.protocol.ItemsResponseList;
import com.example.krngrvr09.vendorapp.api.protocol.OrdersResponseList;
import com.example.krngrvr09.vendorapp.api.protocol.UserResponse;
import com.example.krngrvr09.vendorapp.api.protocol.newItemResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;


public interface mAPI {

    @GET("/orders")
    void getOrders(Callback<OrdersResponseList> ordersResponseListCallback);

    //TODO: ADD REQUESTS HERE

    @POST("/items")
    void createItem(@Body Item item, Callback<newItemResponse> callback);

    @GET("/users")
    void getUsers(Callback<UserResponse> userResponseCallback);

    @GET("/items")
    void getItems(Callback<ItemsResponseList> itemsResponseListCallback);


}
