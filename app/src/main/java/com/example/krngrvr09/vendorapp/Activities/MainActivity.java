package com.example.krngrvr09.vendorapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.krngrvr09.vendorapp.Adapters.CustomBaseAdapter;
import com.example.krngrvr09.vendorapp.Adapters.ViewPagerAdapter;
import com.example.krngrvr09.vendorapp.Database.DbHelper;
import com.example.krngrvr09.vendorapp.Fragments.AddItemsFragment;
import com.example.krngrvr09.vendorapp.Fragments.CompletedOrderFragment;
import com.example.krngrvr09.vendorapp.Fragments.OrderListFragment;
import com.example.krngrvr09.vendorapp.Models.Item;
import com.example.krngrvr09.vendorapp.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    static final int NUM_ITEMS = 3;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(mPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);
//        DataDownload download = new DataDownload();
//        download.downloadItems();
//        download.downloadOrders();

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
            if (mNum == 0) {
                ((TextView) tv).setText("Pending Orders");
                btn.setVisibility(View.INVISIBLE);

            } else if (mNum == 1) {
                ((TextView) tv).setText("Completed Orders");
                btn.setVisibility(View.INVISIBLE);
            } else {
                ((TextView) tv).setText("Inventory Items");
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
            itemList.add(new Item("Burger", 1, "abc", 35, 10, "http://www.mealadvisors.com/files/get/path/original/galleries/burger_large.jpg", 3));
            itemList.add(new Item("Pav bhaji", 2, "abc", 30, 20, "http://www.mealadvisors.com/files/get/path/original/galleries/burger_large.jpg", 2));
            if (mNum == 2) {
                setListAdapter(new CustomBaseAdapter(getActivity(), itemList));
            } else {
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

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OrderListFragment(), "Pending Orders");
        adapter.addFragment(new CompletedOrderFragment(), "Completed Orders");
        adapter.addFragment(new AddItemsFragment(), "Add New Items");
        viewPager.setAdapter(adapter);
    }
}
