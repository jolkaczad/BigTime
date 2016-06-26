package com.example.w.bigtime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    private TableLayout tl;
    Bout bout;

    static final String TAG = "BIGTIME_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tl = (TableLayout) findViewById(R.id.rounds_list);

        /* Define the listener for adding rounds */
        View.OnClickListener handleAdd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bout.Round round = bout.addRound(getApplicationContext(), v);

                TableRow row = new TableRow(getApplicationContext());
                row.addView(round.tv);

                round.addButton.setOnClickListener(this);
                row.addView(round.addButton);
                tl.addView(row, 0);
            }
        };

        /* Define the listener for removing rounds */
        View.OnClickListener handleRemove = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = new TextView(getApplicationContext());
                tv.setText("Text View");

                Button addButton = new Button(getApplicationContext());
                addButton.setText("Add");
                addButton.setOnClickListener(this);

                TableRow row = new TableRow(getApplicationContext());
                row.addView(tv);
                row.addView(addButton);
                tl.addView(row, tl.getChildCount());
            }
        };

        /* Add initial round row */
        bout = new Bout(this);
        Bout.Round round = bout.getRound(0);

        TableRow row = new TableRow(this);
        row.addView(round.tv);

        round.addButton.setOnClickListener(handleAdd);
        row.addView(round.addButton);
        tl.addView(row, 0);


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
}
