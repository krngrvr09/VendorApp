package com.example.krngrvr09.vendorapp.Models;

/**
 * Created by krngrvr09 on 23/11/15.
 */
public class User {
    String name;
    String email;

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
    public String getName(){
        return this.name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setName(String name){
        this.name = name;
    }

}
