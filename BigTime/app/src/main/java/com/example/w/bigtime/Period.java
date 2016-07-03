package com.example.w.bigtime;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WL on 01-lip-2016
 * <p>
 * Version 1.0
 */

public class Period implements Parcelable {
    /* Types of period */
    public static final int ROUND = 0;
    public static final int REST = 1;

    public String desc;
    public int duration;    // duration in seconds
    public int type;

    public Period(String desc, int duration, int type) {
        this.desc = desc;
        this.duration = duration;
        this.type = type;

        // TODO: add exception when type is invalid
    }

    public Period(int duration, int type) {
        desc = new String();

        this.duration = duration;
        this.type = type;

        // TODO: add exception when type is invalid
    }

    /* Everything below is to support implementation of Parcelable */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(desc);
        dest.writeInt(duration);
        dest.writeInt(type);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Period> CREATOR = new Parcelable.Creator<Period>() {
        public Period createFromParcel(Parcel in) {
            return new Period(in);
        }

        public Period[] newArray(int size) {
            return new Period[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Period(Parcel in) {
        desc = new String();

        desc = in.readString();
        duration = in.readInt();
        type = in.readInt();
    }
}