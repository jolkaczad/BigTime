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
 * Version 1.1
 */
public class Bout {
    static final String TAG = "BIGTIME_LOG";

    private int rounds = 0;
    private ArrayList<Period> periodArrayList;

    public Bout (int roundTime, int restTime, int roundCount) {
        periodArrayList = new ArrayList<>();

        for (int i = 0; i < (roundCount - 1); i++){
            Period roundPeriod = new Period(roundTime, Period.ROUND);
            Period restPeriod = new Period(restTime, Period.REST);

            periodArrayList.add(roundPeriod);
            periodArrayList.add(restPeriod);
        }
        Period roundPeriod = new Period(roundTime, Period.ROUND);
        periodArrayList.add(roundPeriod);
    }

    public int getBoutTime (){
        int total = 0;

        ListIterator<Period> li = periodArrayList.listIterator();
        while(li.hasNext()){
            Period round = li.next();

            total += round.duration;
        }

        return total;
    }

    public class Period {
        /* Types of period */
        public static final int ROUND = 0;
        public static final int REST = 1;

        public String desc;
        public int duration;    // duration in seconds
        public int type;

        public Period (String desc, int duration, int type){
            this.desc = desc;
            this.duration = duration;
            this.type = type;

            // TODO: add exception when type is invalid
        }

        public Period (int duration, int type){
            this.duration = duration;
            this.type = type;

            // TODO: add exception when type is invalid
        }
    }
}
