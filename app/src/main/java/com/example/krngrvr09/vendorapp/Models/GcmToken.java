package com.example.krngrvr09.vendorapp.Models;

/**
 * Created by Manan Wason on 29/11/15.
 */
public class GcmToken {
    int user_id;
    String reg_id;

    public GcmToken(int user_id, String reg_id) {
        this.user_id = user_id;
        this.reg_id = reg_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }
}
