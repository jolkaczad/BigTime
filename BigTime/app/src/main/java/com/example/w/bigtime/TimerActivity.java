package com.example.w.bigtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class TimerActivity extends Activity {
    // TODO: add two text boxes for GO and START. They should be in different colors on opposite screen sides

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

    private int tminus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_timer);

        /* Getting the data from previous intent */
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        final Bout bout = b.getParcelable("EXTRA_BOUT");

        stopwatchTextView = (TextView)findViewById(R.id.stopwatch);

        startTime = System.currentTimeMillis();

        /* Setting up "countdown to start" timer */
        // TODO make t minus time configurable

        tminus = 10;
        Runnable tminusRunnable = new Runnable() {
            @Override
            public void run(){
                stopwatchTextView.setText("-" + String.valueOf(tminus));
                tminus--;

                if (tminus > 0) {
                    roundTimerHandler.postDelayed(this, 1 * 1000);
                }
            }
        };
        roundTimerHandler.postDelayed(tminusRunnable, 0);

        /* Make sure the screen is on when timer is running */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        /* Setting up main "clock" timer */
        clockTimerHandler.postDelayed(clockTimerRunnable, 10 * 1000);

        /* Setting up "round" timer */
        Runnable roundTimerRunnable = new Runnable(){
            @Override
            public void run(){
                Period period = bout.getNextPeriod();

                if (period == null){
                    stop = true;

                    /* it's okay to shut down the screen after timer has stopped */
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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

        roundTimerHandler.postDelayed(roundTimerRunnable, 10 * 1000);

        View v = findViewById(R.id.rectProgressView);
    }
}
