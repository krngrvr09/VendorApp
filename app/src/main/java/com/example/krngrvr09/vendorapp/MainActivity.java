package com.example.krngrvr09.vendorapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity {
    static final int NUM_ITEMS = 3;
    MyAdapter mAdapter;

    ViewPager mPager;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        mAdapter = new MyAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        // Watch for button clicks.
//        Button button = (Button)findViewById(R.id.goto_first);
//        button.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                mPager.setCurrentItem(0);
//            }
//        });
//        button = (Button)findViewById(R.id.goto_last);
//        button.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                mPager.setCurrentItem(NUM_ITEMS-1);
//            }
//        });
    }
    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            return ArrayListFragment.newInstance(position);
        }
    }

    public static class ArrayListFragment extends ListFragment {
        int mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static ArrayListFragment newInstance(int num) {
            ArrayListFragment f = new ArrayListFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;

        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_coordinator_layout, container, false);
            View tv = v.findViewById(R.id.text);
            FloatingActionButton btn = (FloatingActionButton) v.findViewById(R.id.fab);
            if(mNum==0){
                ((TextView)tv).setText("Pending Orders");
                btn.setVisibility(View.INVISIBLE);

            }
            else if(mNum==1){
                ((TextView)tv).setText("Completed Orders");
                btn.setVisibility(View.INVISIBLE);
            }
            else{
                ((TextView)tv).setText("Inventory Items");
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), AddItemActivity.class);
                        startActivity(intent);
                    }
                });
            }
            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            String[] stringlist = {"No Orders Yet"};
            ArrayList<Item> itemList = new ArrayList<>();
            itemList.add(new Item("Burger", 35, 10, R.drawable.burger));
            itemList.add(new Item("Veg Sandwich",35,15,R.drawable.sandwich));
            itemList.add(new Item("Samosa", 10, 5, R.drawable.samosa));
            if(mNum==2) {
                setListAdapter(new CustomBaseAdapter(getActivity(), itemList));
            }
            else{
                setListAdapter(new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, stringlist));

            }
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.i("FragmentList", "Item clicked: " + id);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_item) {
            Intent intent = new Intent(this, AddItemActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
