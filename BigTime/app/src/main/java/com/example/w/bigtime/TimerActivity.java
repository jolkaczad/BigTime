package com.example.w.bigtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        Bout bout = b.getParcelable("EXTRA_BOUT");
        TextView tv = (TextView) findViewById(R.id.centerTV);

        tv.setText(String.valueOf(bout.getBoutTime()));
    }
}
