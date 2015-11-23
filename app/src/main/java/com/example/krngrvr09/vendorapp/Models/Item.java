package com.example.krngrvr09.vendorapp.Models;

import android.database.DatabaseUtils;
import android.util.Log;

import com.example.krngrvr09.vendorapp.Database.DbContract;
import com.google.gson.annotations.SerializedName;

/**
 * Created by krngrvr09 on 26/10/15.
 */
public class Item {
    String name;
    @SerializedName("id")
    private int id;
    private String contents;
    int price;
    int quantity;
    @SerializedName("image")
    String image_url;
    int rating;

    public Item(String name, int id, String contents, int price, int quantity, String image_url, int rating) {
        this.name = name;
        this.id = id;
        this.contents = contents;
        this.price = price;
        this.quantity = quantity;
        this.image_url = image_url;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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


    public String generateSql() {
        String query_normal = "INSERT INTO %s VALUES ('%d', %s, '%f', '%s', '%s','%f','%f','%f');";
        //TODO: Change image, contains in query

        String Image = "http://globe-views.com/dcim/dreams/food/food-06.jpg";
        String contents = "Abc";
        String query = String.format(
                query_normal,
                DbContract.Items.TABLE_NAME,
                id,
                DatabaseUtils.sqlEscapeString(name),
                Image,
                contents,
                price,
                rating,
                quantity);
        Log.d("query item", query);
        return query;
    }
}
