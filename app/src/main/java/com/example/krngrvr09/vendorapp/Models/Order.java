package com.example.krngrvr09.vendorapp.Models;

import android.util.Log;

import com.example.krngrvr09.vendorapp.Database.DbContract;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 * Created by MananWason on 23-11-2015.
 */
public class Order {

    @SerializedName("id")
    int orderId;
    @SerializedName("user_id")
    int userId;
    @SerializedName("items")
    ArrayList<Item> items;
    @SerializedName("created_at")
    String dateOfOrder;
    @SerializedName("cost")
    double costOfOrder;
    @SerializedName("status")
    Boolean isOrderCompleted;
    @SerializedName("payment_status")
    Boolean isPaymentMade;
    String itemsString;

    public Order(int orderId, int userId, ArrayList<Item> items, String dateOfOrder, double costOfOrder, Boolean isOrderCompleted, Boolean isPaymentMade) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.dateOfOrder = dateOfOrder;
        this.costOfOrder = costOfOrder;
        this.isOrderCompleted = isOrderCompleted;
        this.isPaymentMade = isPaymentMade;
    }

    public String getItemsString() {
        return itemsString;
    }

    public void setItemsString(String itemsString) {
        this.itemsString = itemsString;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public double getCostOfOrder() {
        return costOfOrder;
    }

    public void setCostOfOrder(double costOfOrder) {
        this.costOfOrder = costOfOrder;
    }

    public Boolean getIsOrderCompleted() {
        return isOrderCompleted;
    }

    public void setIsOrderCompleted(Boolean isOrderCompleted) {
        this.isOrderCompleted = isOrderCompleted;
    }

    public Boolean getIsPaymentMade() {
        return isPaymentMade;
    }

    public void setIsPaymentMade(Boolean isPaymentMade) {
        this.isPaymentMade = isPaymentMade;
    }

    public String generateSql() {
        String query_normal = "INSERT INTO %s VALUES ('%d', '%d', '%s' , '%s' , '%d', '%d' , '%d');";
        String order_name = "Order demo";
        Gson gson = new Gson();
        String query = String.format(
                query_normal,
                DbContract.Orders.TABLE_NAME,
                orderId,
                userId,
                getItemsString(),        //TODO: fill in actual items names instead of ids, would be easy if we get names not ids from server.
                dateOfOrder,
                (int) costOfOrder,
                (isPaymentMade) ? 1 : 0,
                (isOrderCompleted) ? 1 : 0);

        Log.d("query order", ((isPaymentMade) ? 1 : 0)+"");
        return query;
    }

}

