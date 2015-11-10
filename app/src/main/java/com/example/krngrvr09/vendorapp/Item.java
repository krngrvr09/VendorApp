package com.example.krngrvr09.vendorapp;

/**
 * Created by krngrvr09 on 26/10/15.
 */
public class Item {
    String name;
    int price;
    int quantity;
    String image_url;
    int image_resource_url;

    public Item(String name, int price, int quantity, String image_url){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image_url = image_url;
    }
    public Item(String name, int price, int quantity, int image_resource_url){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image_resource_url = image_resource_url;
    }


}
