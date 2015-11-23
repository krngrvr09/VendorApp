package com.example.krngrvr09.vendorapp.Events;

/**
 * Created by siddharth on 11/18/15.
 */
public class OrderDownloadDoneEvent {
    boolean state;

    public OrderDownloadDoneEvent(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}
