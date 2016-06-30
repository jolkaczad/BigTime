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
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    private TableLayout tl;
    Bout bout;
    Button button;

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

        /* Define the listener for creating bout and starting timer */
        View.OnClickListener go = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int roundTime = Integer.parseInt(((EditText)findViewById(R.id.roundTimeET)).getText().toString());
                int restTime = Integer.parseInt(((EditText)findViewById(R.id.restTimeET)).getText().toString());
                int roundCount = Integer.parseInt(((EditText)findViewById(R.id.roundCountET)).getText().toString());

                bout = new Bout(roundTime, restTime, roundCount);

                TextView tv = (EditText)findViewById(R.id.totalET);

                tv.setText(String.valueOf(bout.getBoutTime()));
            }
        };

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(go);
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
