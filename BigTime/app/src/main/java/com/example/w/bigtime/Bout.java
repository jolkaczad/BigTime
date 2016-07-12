package com.example.w.bigtime;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by WL on 24-cze-2016
 * <p/>
 * Version 1.2
 */
public class Bout implements Parcelable {
    static final String TAG = "BIGTIME_LOG";

    private ArrayList<Period> periodArrayList;
    private ListIterator<Period> mainListIterator = null;

    private int roundCount;

    public Bout (int roundTime, int restTime, int roundCount) {
        periodArrayList = new ArrayList<>();

        for (int i = 0; i < roundCount; i++){
            Period roundPeriod = new Period(roundTime, Period.ROUND);
            Period restPeriod = new Period(restTime, Period.REST);

            periodArrayList.add(roundPeriod);
            periodArrayList.add(restPeriod);
        }

        /* Remove the last REST period */
        if (!periodArrayList.isEmpty()){
            periodArrayList.remove((roundCount * 2 - 1));
        }

        this.roundCount = roundCount;
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

    public static int calcTotalTime(int roundTime, int restTime, int roundCount){
        int total;

        if (roundCount <= 0){
            return 0;
        }
        else if (roundTime <= 0){
            return 0;
        }
        else {
            total = (roundTime * roundCount) + (restTime * (roundCount - 1));
            return total;
        }
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

    public int getRoundCount() {
        return roundCount;
    }

    /* Everything below is to support implementation of Parcelable */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(periodArrayList);
        dest.writeInt(roundCount);
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
        roundCount = in.readInt();
        mainListIterator = null;
    }
}