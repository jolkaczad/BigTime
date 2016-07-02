package com.example.w.parcelable_example1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WL on 02-lip-2016
 * <p/>
 * Version 1.0
 */
public class Parcel1 implements Parcelable{
    int a;
    double b;
    String c;

    Parcel1 (){
        a = 5;
        b = 10;
        c = String.valueOf("15");
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
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Parcel1> CREATOR = new Parcelable.Creator<Parcel1>() {
        public Parcel1 createFromParcel(Parcel in) {
            return new Parcel1(in);
        }

        public Parcel1[] newArray(int size) {
            return new Parcel1[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Parcel1(Parcel in) {
        a = in.readInt();
        b = in.readDouble();
        c = in.readString();
    }
}