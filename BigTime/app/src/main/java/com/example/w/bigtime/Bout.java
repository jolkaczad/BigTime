package com.example.w.bigtime;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;
import android.util.Log;

/**
 * Created by WL on 24-cze-2016
 * <p/>
 * Version 1.0
 */
public class Bout {
    static final String TAG = "BIGTIME_LOG";

    private int rounds = 0;
    private ArrayList<Round> roundArrayList;

    public Bout (Context context){
        roundArrayList = new ArrayList<>();
        addRound(context, 0);
    }

    public Round addRound (Context context, int index){
        Round r = new Round(context, String.valueOf(rounds));
        rounds++;

        roundArrayList.add(index, r);

        return r;
    }

    public Round addRound (Context context, View v){
        int index = findIdByAddButton(v);
        Log.d(TAG, "findIdByAddButton: " + String.valueOf(index));

        return addRound(context, index);
    }

    public Round getRound(int index){
        return roundArrayList.get(index);
    }

    public int findIdByAddButton(View v){
        ListIterator<Round> litr = roundArrayList.listIterator();
        while(litr.hasNext()){
            Round round = litr.next();

            if (round.addButton == v){
                return litr.previousIndex();
            }
        }

        return -1;
    }

    public class Round {
        public TextView tv;
        public Button addButton;
        public Button removeButton;
        public int id;

        public Round(Context context, String text){
            tv = new TextView(context);
            tv.setText(text);
            addButton = new Button(context);
            addButton.setText("+");
            removeButton = new Button(context);
            removeButton.setText("-");
        }
    }
}
