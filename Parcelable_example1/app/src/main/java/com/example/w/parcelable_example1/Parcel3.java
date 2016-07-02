package com.example.w.parcelable_example1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by WL on 02-lip-2016
 * <p/>
 * Version 1.0
 */
public class Parcel3 implements Parcelable{
    public ArrayList<ListItem> dArrayList;


    Parcel3 (){
        dArrayList = new ArrayList<>();

        dArrayList.add(new ListItem(1));
        dArrayList.add(new ListItem(2));
        dArrayList.add(new ListItem(3));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(dArrayList);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Parcel3> CREATOR = new Parcelable.Creator<Parcel3>() {
        public Parcel3 createFromParcel(Parcel in) {
            return new Parcel3(in);
        }

        public Parcel3[] newArray(int size) {
            return new Parcel3[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Parcel3(Parcel in) {
        dArrayList = new ArrayList<>();
        in.readTypedList(dArrayList, ListItem.CREATOR);
    }
}
