package com.example.krngrvr09.vendorapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.krngrvr09.vendorapp.Adapters.OrderVAdapter;
import com.example.krngrvr09.vendorapp.Database.DbSingleton;
import com.example.krngrvr09.vendorapp.Events.RefreshUiEvent;
import com.example.krngrvr09.vendorapp.Models.Order;
import com.example.krngrvr09.vendorapp.R;
import com.example.krngrvr09.vendorapp.VendorApp;
import com.squareup.otto.Subscribe;

import java.util.List;

/**
 * Created by Manan Wason on 12/11/15.
 */
public class CompletedOrderFragment extends Fragment{
    List<Order> mDataList;

    private RecyclerView mVerticalList;
    private ImageView image1;
    private OrderVAdapter verticalAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
//        prepareData();
        View view = inflater.inflate(R.layout.orders_list, container, false);
        mVerticalList = (RecyclerView) view.findViewById(R.id.rv_orders);
        mVerticalList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        verticalAdapter = new OrderVAdapter(1);
        DbSingleton mDbSingleton = DbSingleton.getInstance();
        mDataList = mDbSingleton.getCompletedOrdersList();
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
        verticalAdapter.refresh();

    }
}
