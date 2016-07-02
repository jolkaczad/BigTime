package com.example.w.parcelable_example1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Parcel3 parcel3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parcel3 = new Parcel3();

        /* Fire the new activity */
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("EXTRA_PARCEL3", parcel3);
        startActivity(intent);
    }
}
