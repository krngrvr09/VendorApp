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

import com.example.krngrvr09.vendorapp.Adapters.OrderVAdapter;
import com.example.krngrvr09.vendorapp.Database.DbSingleton;
import com.example.krngrvr09.vendorapp.Events.RefreshUiEvent;
import com.example.krngrvr09.vendorapp.Models.Order;
import com.example.krngrvr09.vendorapp.R;
import com.example.krngrvr09.vendorapp.VendorApp;
import com.squareup.otto.Subscribe;

import java.util.List;

/**
 * Created by Manan Wason on 11/11/15.
 */
public class OrderListFragment extends Fragment {

    List<Order> mDataList;

    private RecyclerView mVerticalList;
    private OrderVAdapter verticalAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.orders_list, container, false);
        mVerticalList = (RecyclerView) view.findViewById(R.id.rv_orders);
        mVerticalList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        verticalAdapter = new OrderVAdapter();
        DbSingleton mDbSingleton = DbSingleton.getInstance();
        mDataList = mDbSingleton.getPendingOrdersList();
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
    public void RefreshUi(RefreshUiEvent event) {
        Log.i("Refresh", "event");
        verticalAdapter.refresh();

    }

}
