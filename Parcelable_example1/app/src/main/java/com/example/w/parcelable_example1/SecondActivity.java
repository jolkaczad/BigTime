package com.example.w.parcelable_example1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;

public class SecondActivity extends AppCompatActivity {
    int parcel_a;
    double parcel_b;
    String parcel_c;

    private ArrayList<Integer> parcel_dArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        Parcel2 parcel2 = b.getParcelable("EXTRA_PARCEL2");

        parcel_a = parcel2.a;
        parcel_b = parcel2.b;
        parcel_c = parcel2.c;
        parcel_dArrayList = parcel2.dArrayList;

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(parcel_a));

        textView = (TextView) findViewById(R.id.textView2);
        textView.setText(String.valueOf(parcel_b));

        textView = (TextView) findViewById(R.id.textView3);
        textView.setText(String.valueOf(parcel_c));

        textView = (TextView) findViewById(R.id.textView4);
        textView.setText(String.valueOf(String.valueOf(parcel_dArrayList.get(0)) + " " + String.valueOf(parcel_dArrayList.get(1))));
    }
}