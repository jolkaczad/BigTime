package com.example.w.parcelable_example1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Parcel2 parcel2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parcel2 = new Parcel2();

        /* Fire the new activity */
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("EXTRA_PARCEL2", parcel2);
        startActivity(intent);
    }
}
