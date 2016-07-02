package com.example.w.parcelable_example1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;

public class SecondActivity extends AppCompatActivity {
    private ArrayList<ListItem> parcel_dArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        Parcel3 parcel3 = b.getParcelable("EXTRA_PARCEL3");

        parcel_dArrayList = parcel3.dArrayList;

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(parcel_dArrayList.get(0).x + " " + parcel_dArrayList.get(0).y + " " + parcel_dArrayList.get(0).z));

        textView = (TextView) findViewById(R.id.textView2);
        textView.setText(String.valueOf(parcel_dArrayList.get(1).x + " " + parcel_dArrayList.get(1).y + " " + parcel_dArrayList.get(1).z));

        textView = (TextView) findViewById(R.id.textView3);
        textView.setText(String.valueOf(parcel_dArrayList.get(2).x + " " + parcel_dArrayList.get(2).y + " " + parcel_dArrayList.get(2).z));
    }
}