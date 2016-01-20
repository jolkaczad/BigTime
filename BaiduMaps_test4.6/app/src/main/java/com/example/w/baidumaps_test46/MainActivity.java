package com.example.w.baidumaps_test46;

import android.content.res.XmlResourceParser;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static class MapFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public MapFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MapFragment newInstance(int sectionNumber) {
            MapFragment fragment = new MapFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_map, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public static class PointsListFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        TableLayout tl;

        ArrayList<CheckBox> checkBoxArrayList;
        View view;
        public NpPointsFromXML npPointsFromXML = null;
        static final String TAG = "L1";


        public PointsListFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PointsListFragment newInstance(int sectionNumber) {
            PointsListFragment fragment = new PointsListFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_pointslist, container, false);
//            return rootView;
            XmlResourceParser xpp = this.getResources().getXml(R.xml.points1);
            npPointsFromXML = new NpPointsFromXML(xpp);

            Log.d(TAG, "LOG:");
            Log.d(TAG, npPointsFromXML.toString());

            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_pointslist, container, false);

            tl = (TableLayout) view.findViewById(R.id.maintable);

            TableRow tr;
            TextView tv1, tv2;

            CheckBox checkBox;

            boolean restored;

            if (checkBoxArrayList == null){
                restored = false;
                checkBoxArrayList = new ArrayList<>();
            }
            else {
                restored = true;
            }
            npPointsFromXML.rewind();
            for (int i = 0; i < npPointsFromXML.getPointCount(); i++){
                tr = new TableRow(getActivity());
                tr.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                tv1 = new TextView(getActivity());

                NpPoint npPoint = npPointsFromXML.getNextItem();
                if (npPoint != null) {
                    tv1.setText(npPoint.name);
                    tr.addView(tv1);

                    tv2 = new TextView(getActivity());
                    tv2.setText(npPoint.description);

                    tr.addView(tv2);

                    if (restored == false){

                        checkBox = new CheckBox(getActivity());
                        checkBoxArrayList.add(checkBox);
                    }
                    else {
                        checkBox = checkBoxArrayList.get(i);
                    }

                    tr.addView(checkBox);
                    tl.addView(tr);
                }
            }

            return view;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch(position){
                case 0:
                    fragment = MapFragment.newInstance(position + 1);
                    break;
                case 1:
                    fragment = PointsListFragment.newInstance(position + 1);
                    break;
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "NAVIGATE";
                case 1:
                    return "SELECT";
            }
            return null;
        }
    }
}
