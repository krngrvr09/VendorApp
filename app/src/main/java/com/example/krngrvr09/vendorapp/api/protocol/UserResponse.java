package com.example.krngrvr09.vendorapp.api.protocol;

import com.example.krngrvr09.vendorapp.Models.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Manan Wason on 20/11/15.
 */
public class UserResponse {
    @SerializedName("users")
    public List<User> users;

}
