package com.example.krngrvr09.vendorapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.krngrvr09.vendorapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Manan Wason on 11/11/15.
 */
public class OrderHAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static Toast sToast;
    private List<String> mDataList;
    private int mRowIndex = -1;
    private int[] mColors = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.DKGRAY};

    public OrderHAdpater() {
    }

    public void setData(List<String> data) {
        if (mDataList != data) {
            mDataList = data;
            notifyDataSetChanged();
        }
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;


        public ItemViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image1);
            itemView.setOnClickListener(mItemClickListener);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.horizontal_list_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, int position) {
        ItemViewHolder holder = (ItemViewHolder) rawHolder;
        Uri uri = Uri.parse("http://pctechmag.com/wp-content/uploads/2013/09/android-double-down.jpg");
        Picasso.with(holder.image.getContext()).load(uri).error(R.drawable.github).into(holder.image);
//            holder.image.set("ABC");
//            holder.itemView.setBackgroundColor(mColors[position % mColors.length]);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    private View.OnClickListener mItemClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int columnIndex = (int) v.getTag();
            int rowIndex = mRowIndex;

            String text = String.format("rowIndex:%d ,columnIndex:%d", rowIndex, columnIndex);
            showToast(v.getContext(), text);
            Log.d("test", text);
        }
    };

    public static void showToast(Context context, String text) {
        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        sToast.show();
    }

}