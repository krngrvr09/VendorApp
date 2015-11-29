package com.example.krngrvr09.vendorapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.krngrvr09.vendorapp.Adapters.OrderVAdapter;
import com.example.krngrvr09.vendorapp.Database.DatabaseOperations;
import com.example.krngrvr09.vendorapp.Database.DbSingleton;
import com.example.krngrvr09.vendorapp.Events.OrderDownloadDoneEvent;
import com.example.krngrvr09.vendorapp.R;
import com.example.krngrvr09.vendorapp.VendorApp;
import com.example.krngrvr09.vendorapp.api.APIClient;
import com.example.krngrvr09.vendorapp.api.processor.OrdersListResponseProcessor;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manan Wason on 11/11/15.
 */
public class OrderListFragment extends Fragment{

    List<List<String>> mDataList;

    private RecyclerView mVerticalList;
    private ImageView image1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        prepareData();
        downloadOrders();
        View view = inflater.inflate(R.layout.orders_list, container, false);
        mVerticalList = (RecyclerView) view.findViewById(R.id.rv_orders);
        mVerticalList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        OrderVAdapter verticalAdapter = new OrderVAdapter();
        verticalAdapter.setData(mDataList);
        mVerticalList.setAdapter(verticalAdapter);
        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        VendorApp.getEventBus().unregister(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        VendorApp.getEventBus().register(this);

    }
    @Subscribe
    public void OrderDownloadDone(OrderDownloadDoneEvent event) {
//        Log.d("retro event", eventsDone + " " + counter);

        if (event.isState()) {
//            eventsDone++;
            Log.d("retro event", "is state true");
            Log.d("order list", String.valueOf(DbSingleton.getInstance().getCompletedOrdersList()));

//            if (counter == eventsDone) {
//                syncComplete();
//            }

        } else {
            Log.d("retro event", "is state false");

//            downloadFailed();
        }

    }
    private void downloadOrders(){
        APIClient apiClient = new APIClient();
        apiClient.getmApi().getOrders(true, new OrdersListResponseProcessor());

    }

    private void prepareData() {

        mDataList = new ArrayList<>();
        int vItemCount = 25;
        int hItemCount = 20;
        for (int i = 0; i < vItemCount; i++) {
            List<String> hList = new ArrayList<>();
            for (int j = 0; j < hItemCount; j++) {
                hList.add("Item." + j);
            }
            mDataList.add(hList);
        }
    }


}
