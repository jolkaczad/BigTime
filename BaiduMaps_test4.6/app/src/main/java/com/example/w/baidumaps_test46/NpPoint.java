package com.example.w.baidumaps_test46;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WL on 10-sty-2016
 *
 * Version 1.2
 */
public class NpPoint implements Parcelable {
    public static final int GENERIC = 100;
    public static final int EATERY = 101;
    public static final int SCENIC = 102;
    public static final int TRANSPORT = 103;
    public static final int HOTEL = 104;
    public static final int OFFICE = 105;

    public int id;

    public String name;

    public double lat;
    public double lng;

    public String description;

    public int type;

    public NpPoint(){

    }

    public NpPoint(Parcel source) {
        id = source.readInt();
        name = source.readString();
        lat = source.readDouble();
        lng = source.readDouble();
        description = source.readString();
        type = source.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(description);
        dest.writeInt(type);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public NpPoint createFromParcel(Parcel source) {
            return new NpPoint(source);
        }
        public NpPoint[] newArray(int size) {
            return new NpPoint[size];
        }
    };
}
