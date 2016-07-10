package com.example.w.bigtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends AppCompatActivity {

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

        /* Define the listener for creating bout and starting timer */
        View.OnClickListener go = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int roundTime;
                int restTime;
                int roundCount;

                try {
                    roundTime = Integer.parseInt (((EditText) findViewById(R.id.roundTimeET)).getText().toString());
                }
                catch (NullPointerException | NumberFormatException e){
                    roundTime = 0;
                }

                try {
                    restTime = Integer.parseInt (((EditText)findViewById(R.id.restTimeET)).getText().toString());
                }
                catch (NullPointerException | NumberFormatException e){
                    restTime = 0;
                }

                try {
                    roundCount = Integer.parseInt (((EditText)findViewById(R.id.roundCountET)).getText().toString());
                }
                catch (NullPointerException | NumberFormatException e){
                    roundCount = 0;
                }

                bout = new Bout(roundTime, restTime, roundCount);

                /* make sure the bout time is correct, i.e. non-zero round number, no negative times */
                if (bout.getTotalTime() > 0) {
                    /* Fire the new activity */
                    Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                    intent.putExtra("EXTRA_BOUT", bout);
                    startActivity(intent);
                }

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

                try {
                    roundTime = Integer.parseInt (((EditText) findViewById(R.id.roundTimeET)).getText().toString());
                }
                catch (NullPointerException | NumberFormatException e){
                    roundTime = 0;
                }

                try {
                    restTime = Integer.parseInt (((EditText)findViewById(R.id.restTimeET)).getText().toString());
                }
                catch (NullPointerException | NumberFormatException e){
                    restTime = 0;
                }

                try {
                    roundCount = Integer.parseInt (((EditText)findViewById(R.id.roundCountET)).getText().toString());
                }
                catch (NullPointerException | NumberFormatException e){
                    roundCount = 0;
                }

                TextView tv = (EditText)findViewById(R.id.totalET);

                int total = Bout.calcTotalTime(roundTime, restTime, roundCount);

                tv.setText(String.valueOf(total));
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
