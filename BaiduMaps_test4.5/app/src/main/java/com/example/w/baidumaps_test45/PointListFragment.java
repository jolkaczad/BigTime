package com.example.w.baidumaps_test45;


import android.app.ActionBar;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PointListFragment extends Fragment {
    static final String TAG = "L1";

    public PointListFragment() {

    }

    TableLayout tl;
    TableRow tr;
    TextView companyTV,valueTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;

        XmlResourceParser xpp = this.getResources().getXml(R.xml.points1);
        NpPointsFromXML npPointsFromXML = new NpPointsFromXML(xpp);

        Log.d(TAG, "LOG:");
        Log.d(TAG, npPointsFromXML.toString());

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_point_list, container, false);

        tl = (TableLayout) view.findViewById(R.id.maintable);

        // Add points row by row
//        TableRow tr;
//        TextView tv1, tv2;
//
//        tr = new TableRow(getActivity());
//        tr.setLayoutParams(new TableLayout.LayoutParams(
//                TableLayout.LayoutParams.FILL_PARENT,
//                TableLayout.LayoutParams.WRAP_CONTENT));
//
//        tv1 = new TextView(getActivity());
//        tv1.setText("Cell #1");
//        tr.addView(tv1);
//
//        tv2 = new TextView(getActivity());
//        tv2.setText("Cell #2");
//        tr.addView(tv2);
//
//        tl.addView(tr);
        TableRow tr;
        TextView tv1, tv2;

        CheckBox checkBox;
        ArrayList<CheckBox> checkBoxArrayList = new ArrayList<>();

        npPointsFromXML.rewind();
        for (int i = 0; i < npPointsFromXML.getPointCount(); i++){
            tr = new TableRow(getActivity());
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            tv1 = new TextView(getActivity());
//            tr.

            NpPoint npPoint = npPointsFromXML.getNextItem();
            if (npPoint != null) {
                tv1.setText(npPoint.name);
                tr.addView(tv1);
//                tv.setw

//                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                params.
//                tv1.setLayoutParams(params);

                tv2 = new TextView(getActivity());
                tv2.setText(npPoint.description);

                tr.addView(tv2);

                checkBox = new CheckBox(getActivity());
                tr.addView(checkBox);
                checkBoxArrayList.add(checkBox);

                tl.addView(tr);
            }
        }

        return view;
    }
}
