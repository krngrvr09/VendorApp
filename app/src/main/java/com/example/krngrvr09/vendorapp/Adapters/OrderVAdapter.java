package com.example.krngrvr09.vendorapp.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krngrvr09.vendorapp.Database.DbSingleton;
import com.example.krngrvr09.vendorapp.Models.Item;
import com.example.krngrvr09.vendorapp.Models.Order;
import com.example.krngrvr09.vendorapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manan Wason on 11/11/15.
 */
public class OrderVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Order> mDataList;
    DbSingleton mDbSingleton;

    public OrderVAdapter() {
    }

    public void setData(List<Order> data) {
        mDataList = data;
        notifyDataSetChanged();
    }

    private class HorizontalListViewHolder extends RecyclerView.ViewHolder {

        //            private TextView title;
        private RecyclerView horizontalList;
        private OrderHAdpater horizontalAdapter;

        public HorizontalListViewHolder(View itemView) {
            super(itemView);
            Context context = itemView.getContext();
//                title = (TextView) itemView.findViewById(R.id.item_title);
            horizontalList = (RecyclerView) itemView.findViewById(R.id.item_horizontal_list);
            horizontalList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            horizontalAdapter = new OrderHAdpater();
            horizontalList.setAdapter(horizontalAdapter);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.vertical_list_item, parent, false);
        HorizontalListViewHolder holder = new HorizontalListViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, int position) {
        HorizontalListViewHolder holder = (HorizontalListViewHolder) rawHolder;
//            holder.title.setText("Horizontal List No." + position);
        //create arraylist of items for the order
        Order current = mDataList.get(position);
        mDbSingleton = DbSingleton.getInstance();
        ArrayList<Item> currentOrderItems = new ArrayList<>();
        String itemIdsString = current.getItemsString();
        if(!itemIdsString.equals("")) {
            String[] itemIdsStringArray = itemIdsString.split(",");
            Log.d("not", "empty");
            for(String s:itemIdsStringArray){
                Item i = mDbSingleton.getItemById(Integer.valueOf(s));
                if(i!=null)
                currentOrderItems.add(i);
            }
            holder.horizontalAdapter.setData(currentOrderItems);
            holder.horizontalAdapter.setRowIndex(position);
        }
        else
            Log.d("is","empty");

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}