package com.example.krngrvr09.vendorapp.Models;

/**
 * Created by krngrvr09 on 26/10/15.
 */
public class Item {
    String name;
    int price;
    int quantity;
    String image_url;
    int image_resource_url;

    public Item(String name, int price, int quantity, int image_resource_url) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image_resource_url = image_resource_url;
    }
    public Item(String name, int price, int quantity, String image_url) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getImage_resource_url() {
        return image_resource_url;
    }

    public void setImage_resource_url(int image_resource_url) {
        this.image_resource_url = image_resource_url;
    }
}
