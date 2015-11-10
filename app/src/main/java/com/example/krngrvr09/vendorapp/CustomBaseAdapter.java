package com.example.krngrvr09.vendorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by krngrvr09 on 26/10/15.
 */
class CustomBaseAdapter extends BaseAdapter {
    Context context;
    ArrayList<Item> itemList;
    public CustomBaseAdapter(Context context, ArrayList<Item> itemList){
        this.context = context;
        this.itemList = itemList;
    }
    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    class ViewHolder {
        TextView name;
        TextView price;
        TextView quantity;
        ImageView image;

        ViewHolder(View v)
        {
            name = (TextView) v.findViewById(R.id.item_name);
            price = (TextView) v.findViewById(R.id.item_price);
            quantity = (TextView) v.findViewById(R.id.item_quantity);
            image = (ImageView) v.findViewById(R.id.item_image);

        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        View row = view;
        ViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_layout, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.name.setText("Name: "+itemList.get(i).name);
        holder.price.setText(String.valueOf(itemList.get(i).price));
        holder.quantity.setText(String.valueOf(itemList.get(i).quantity)
        );
        Picasso.with(context).load(itemList.get(i).image_resource_url).into(holder.image);


        return row;

    }
}
