package com.example.krngrvr09.vendorapp.Models;

/**
 * Created by Manan Wason on 15/11/15.
 */
public class User {
    private int id;
    private String email;
    private String name;

    public User(int id, String name, String email) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
