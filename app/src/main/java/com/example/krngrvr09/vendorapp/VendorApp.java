package com.example.krngrvr09.vendorapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.krngrvr09.vendorapp.Database.DbSingleton;
import com.squareup.otto.Bus;

/**
 * Created by Manan Wason on 15/11/15.
 */
public class VendorApp extends Application {
    private static Bus sEventBus;

    public static Bus getEventBus() {
        if (sEventBus == null) {
            sEventBus = new Bus();
        }
        return sEventBus;
    }

    public static void postEventOnUIThread(final Object event) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                getEventBus().post(event);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DbSingleton.init(this);
        Log.d("download", "db");
    }

}