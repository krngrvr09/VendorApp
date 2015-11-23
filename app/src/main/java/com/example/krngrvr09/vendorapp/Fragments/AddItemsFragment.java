package com.example.krngrvr09.vendorapp.Fragments;

/**
 * Created by krngrvr09 on 23/11/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.krngrvr09.vendorapp.Activities.AddItemActivity;
import com.example.krngrvr09.vendorapp.Adapters.CustomBaseAdapter;
import com.example.krngrvr09.vendorapp.Adapters.OrderVAdapter;
import com.example.krngrvr09.vendorapp.Models.Item;
import com.example.krngrvr09.vendorapp.R;

import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.krngrvr09.vendorapp.Adapters.OrderVAdapter;
import com.example.krngrvr09.vendorapp.R;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manan Wason on 11/11/15.
 */
public class AddItemsFragment extends Fragment {

    List<List<String>> mDataList;

    private RecyclerView mVerticalList;

    ArrayList<Item> items;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        prepareData();
        View view = inflater.inflate(R.layout.items_list, container, false);
        ListView items_list = (ListView) view.findViewById(R.id.items_list);
        items = new ArrayList<>();
        items.add(new Item("burger",25,25,R.drawable.burger));
        items.add(new Item("burger", 25, 25, R.drawable.burger));

        items_list.setAdapter(new CustomBaseAdapter(getContext(), items));
        FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(), AddItemActivity.class);
                startActivity(intent);
            }
        });
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
