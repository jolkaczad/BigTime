package com.example.w.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String TAG = "LIFESTATES";
    int up;

    public MainActivity(){
        up = 0;
        Log.d(TAG, "Constructor " + up);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        up++;
        if (savedInstanceState != null) {
            up = savedInstanceState.getInt("up");
        }
        Log.d(TAG, "OnCreate " + up);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        up++;
        super.onStart();
        Log.d(TAG, "OnStart " + up);
    }

    @Override
    protected void onStop(){
        up++;
        super.onStop();
        Log.d(TAG, "OnStop " + up);
    }

    @Override
    protected void onResume(){
        up++;
        super.onResume();
        Log.d(TAG, "OnResume " + up);
    }

    @Override
    protected void onRestart(){
        up++;
        super.onRestart();
        Log.d(TAG, "OnRestart " + up);
    }

    @Override
    protected void onPause (){
        up++;
        super.onPause();
        Log.d(TAG, "OnPause " + up);
    }

    @Override
    protected void onDestroy(){
        up++;
        super.onDestroy();
        Log.d(TAG, "OnDestroy " + up);
    }

    protected void onRestoreInstanceState (Bundle savedInstanceState){
        up++;
        Log.d(TAG, "OnRestoreInstanceState " + up);
    }

    protected void onSaveInstanceState (Bundle outState) {
        up++;
        Log.d(TAG, "OnSaveInstanceState " + up);
        outState.putInt("up", up);
        super.onSaveInstanceState(outState);
    }

    public void activity2_go (View view){
        Intent intent = new Intent(this, Main2Activity.class);

        startActivity(intent);
    }
}
