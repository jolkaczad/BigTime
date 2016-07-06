package com.example.w.bigtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    private TableLayout tl;
    Bout bout;
    Button button;

    static final String TAG = "BIGTIME_LOG";

    // TODO: add a round counter to the main screen
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


                /* Fire the new activity */
                Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                intent.putExtra("EXTRA_BOUT", bout);
                startActivity(intent);
            }
        };

        /* Define the event launched on text change of any EditTexts */
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int roundTime;
                int restTime;
                int roundCount;
                String string;

                string = ((EditText)findViewById(R.id.roundTimeET)).getText().toString();
                if (string.isEmpty()){
                    roundTime = 0;
                }
                else {
                    roundTime = Integer.parseInt(string);
                }

                string = ((EditText)findViewById(R.id.restTimeET)).getText().toString().toString();
                if (string.isEmpty()){
                    restTime = 0;
                }
                else {
                    restTime = Integer.parseInt(string);
                }

                string = ((EditText)findViewById(R.id.roundCountET)).getText().toString();
                if (string.isEmpty()){
                    roundCount = 0;
                }
                else {
                    roundCount = Integer.parseInt(string);
                }

                bout = new Bout(roundTime, restTime, roundCount);

                TextView tv = (EditText)findViewById(R.id.totalET);

                tv.setText(String.valueOf(bout.getTotalTime()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        ((EditText)findViewById(R.id.roundTimeET)).addTextChangedListener(textWatcher);
        ((EditText)findViewById(R.id.restTimeET)).addTextChangedListener(textWatcher);
        ((EditText)findViewById(R.id.roundCountET)).addTextChangedListener(textWatcher);

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
