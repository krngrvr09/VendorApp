package com.example.krngrvr09.vendorapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.krngrvr09.vendorapp.Adapters.OrderVAdapter;
import com.example.krngrvr09.vendorapp.R;

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
        View view = inflater.inflate(R.layout.orders_list, container, false);
        mVerticalList = (RecyclerView) view.findViewById(R.id.rv_orders);
        mVerticalList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        OrderVAdapter verticalAdapter = new OrderVAdapter();
        verticalAdapter.setData(mDataList);
        mVerticalList.setAdapter(verticalAdapter);
        return view;
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
