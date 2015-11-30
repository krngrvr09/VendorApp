package com.example.krngrvr09.vendorapp.Events;

/**
 * Created by MananWason on 30/11/2015.
 */
public class CounterEvent {
    int requestsCount;

    public CounterEvent(int count) {
        this.requestsCount = count;
    }

    public int getRequestsCount() {
        return requestsCount;
    }

    public void setRequestsCount(int requestsCount) {
        this.requestsCount = requestsCount;
    }
}
