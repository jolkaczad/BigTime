package com.example.w.bigtime;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import android.util.Log;

/**
 * Created by WL on 24-cze-2016
 * <p/>
 * Version 1.2
 */
public class Bout implements Parcelable {
    static final String TAG = "BIGTIME_LOG";

    private ArrayList<Period> periodArrayList;
    private ListIterator<Period> mainListIterator = null;

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

    public int getTotalTime(){
        int total = 0;

        ListIterator<Period> li = periodArrayList.listIterator();
        while(li.hasNext()){
            Period round = li.next();

            total += round.duration;
        }

        return total;
    }

    public Period getNextPeriod(){
        /* Recreate the iterator when object is rebuild after parceling */
        if (mainListIterator == null){
            mainListIterator = periodArrayList.listIterator();
        }

        if(mainListIterator.hasNext()){
            return mainListIterator.next();
        }
        else {
            return null;
        }
    }

    /* Everything below is to support implementation of Parcelable */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(periodArrayList);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Bout> CREATOR = new Parcelable.Creator<Bout>() {
        public Bout createFromParcel(Parcel in) {
            return new Bout(in);
        }

        public Bout[] newArray(int size) {
            return new Bout[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Bout(Parcel in) {
        periodArrayList = new ArrayList<>();
        in.readTypedList(periodArrayList, Period.CREATOR);
        mainListIterator = null;
    }
}