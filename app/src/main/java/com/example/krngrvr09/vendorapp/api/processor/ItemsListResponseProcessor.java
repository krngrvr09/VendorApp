package com.example.krngrvr09.vendorapp.api.processor;

import android.util.Log;

import com.example.krngrvr09.vendorapp.Events.ItemDownloadDoneEvent;
import com.example.krngrvr09.vendorapp.Helpers.CommonTaskLoop;
import com.example.krngrvr09.vendorapp.Models.Item;
import com.example.krngrvr09.vendorapp.VendorApp;
import com.example.krngrvr09.vendorapp.api.protocol.ItemsResponseList;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
<<<<<<< HEAD
 * Created by MananWason on 14-11-2015.
=======
 * Created by MananWason on 23-11-2015.
>>>>>>> 5f805bbc85a71856f1679a4b87db1cd5e146ee77
 */
public class ItemsListResponseProcessor implements Callback<ItemsResponseList> {
    private static final String TAG = "Items";

    @Override
    public void success(final ItemsResponseList itemsResponseList, Response response) {

        CommonTaskLoop.getInstance().post(new Runnable() {
            @Override
            public void run() {
                Log.d("retro", "success");
                ArrayList<String> queries = new ArrayList<String>();

                for (Item item : itemsResponseList.items) {
                    String query = item.generateSql();
                    Log.d("retro item", item.getId() + "");
                    queries.add(query);
                    Log.d(TAG, query);
                }

//                DbSingleton dbSingleton = DbSingleton.getInstance();
//                dbSingleton.clearDatabase(DbContract.Items.TABLE_NAME);
//                dbSingleton.insertQueries(queries);

                VendorApp.postEventOnUIThread(new ItemDownloadDoneEvent(true));
            }
        });

    }


    @Override
    public void failure(RetrofitError error) {
        VendorApp.postEventOnUIThread(new ItemDownloadDoneEvent(false));

        //TODO: PREVENT FROM CRASHING
//        Log.d("retro", error.getCause().toString());

    }
}
