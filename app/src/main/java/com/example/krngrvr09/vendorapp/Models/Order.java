package com.example.krngrvr09.vendorapp.Models;

import java.util.ArrayList;

/**
 * Created by krngrvr09 on 23/11/15.
 */
public class Order {
    boolean status;
    boolean payment_status;
    int user_id;
    int cost;
    ArrayList<Item> items;

    public Order(boolean status, boolean payment_status, int user_id, int cost, ArrayList<Item> items){
        this.status = status;
        this.payment_status = payment_status;
        this.user_id = user_id;
        this.cost = cost;
        this.items = items;
    }

    public boolean getStatus(){
        return this.status;
    }
    public boolean getPaymentStatus(){
        return this.payment_status;
    }
    public int getUserId(){
        return this.user_id;
    }
    public int getCost(){
        return this.cost;
    }
    public ArrayList<Item> getItems(){
        return this.items;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public void setPaymentStatus(boolean payment_status){
        this.payment_status = payment_status;
    }
    public void setUserId(int user_id){
        this.user_id = user_id;
    }
    public void setCost(int cost){
        this.cost = cost;
    }
    public void setItems(ArrayList<Item> items){
        this.items = items;
    }


}
