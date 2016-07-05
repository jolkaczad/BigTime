package com.example.w.bigtime;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
    // TODO: add fixed "preparation time" a countdown between setting up and starting the stopwatch
    // TODO: add two text boxes for GO and START. They should be in different colors on opposite screen sides
    // TODO: Make sure the timer state is saved between screen rotations (or block rotations)

    long startTime = 0;
    TextView stopwatchTextView;
    private boolean stop = false;

    Handler clockTimerHandler = new Handler();
    Runnable clockTimerRunnable = new Runnable(){
        @Override
        public void run(){
            int seconds = (int) ((System.currentTimeMillis() - startTime) / 1000);
            int minutes = seconds / 60;
            int tenths = (int) ((System.currentTimeMillis() - startTime) / 100);

            seconds = seconds % 60;
            tenths = tenths % 10;

            stopwatchTextView.setText(String.format("%d:%02d.%01d", minutes, seconds, tenths));

            if (!stop) {
                clockTimerHandler.postDelayed(this, 50);
            }
        }
    };

    Handler roundTimerHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timer);

        /* Getting the data from previous intent */
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        final Bout bout = b.getParcelable("EXTRA_BOUT");

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            startTime = savedInstanceState.getLong("startTime");
        }
        else {
            startTime = System.currentTimeMillis();
        }
        /* Setting up main "clock" timer */
        stopwatchTextView = (TextView)findViewById(R.id.stopwatch);

        clockTimerHandler.postDelayed(clockTimerRunnable, 0);

        /* Setting up "round" timer */
        Runnable roundTimerRunnable = new Runnable(){
            @Override
            public void run(){
                Period period = bout.getNextPeriod();

                if (period == null){
                    stop = true;
                }
                else {
                    startTime = System.currentTimeMillis();

                    roundTimerHandler.postDelayed(this, period.duration * 1000);

                    if(period.type == Period.REST){
                        TextView textView = (TextView)findViewById(R.id.periodType);
                        textView.setText("REST");
                    }
                    else {
                        TextView textView = (TextView)findViewById(R.id.periodType);
                        textView.setText("GO");
                    }
                }
            }
        };

        roundTimerHandler.postDelayed(roundTimerRunnable, 0);
    }
}
