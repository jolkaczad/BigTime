package com.example.w.parcelable_example1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by WL on 02-lip-2016
 * <p/>
 * Version 1.0
 */
public class Parcel2 implements Parcelable {
    int a;
    double b;
    String c;

    public ArrayList<Integer> dArrayList;


    Parcel2 (){
        a = 5;
        b = 10;
        c = String.valueOf("15");

        dArrayList = new ArrayList<>();
        dArrayList.add(16);
        dArrayList.add(32);
        dArrayList.add(64);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(a);
        dest.writeDouble(b);
        dest.writeString(c);
        dest.writeList(dArrayList);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Parcel2> CREATOR = new Parcelable.Creator<Parcel2>() {
        public Parcel2 createFromParcel(Parcel in) {
            return new Parcel2(in);
        }

        public Parcel2[] newArray(int size) {
            return new Parcel2[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Parcel2(Parcel in) {
        a = in.readInt();
        b = in.readDouble();
        c = in.readString();
        dArrayList = in.readArrayList(Integer.class.getClassLoader());
    }
}
