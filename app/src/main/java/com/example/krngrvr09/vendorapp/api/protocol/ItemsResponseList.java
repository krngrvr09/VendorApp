package com.example.krngrvr09.vendorapp.api.protocol;

import com.example.krngrvr09.vendorapp.Models.Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by MananWason on 23-11-2015.
 */
public class ItemsResponseList {
    @SerializedName("items")
    public List<Item> items;
}
