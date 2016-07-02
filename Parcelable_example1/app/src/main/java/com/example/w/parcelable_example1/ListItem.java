package com.example.w.parcelable_example1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WL on 02-lip-2016
 * <p/>
 * Version 1.0
 */
public class ListItem implements Parcelable {
    int x;
    int y;
    int z;

    ListItem(int i){
        x = i * 1;
        y = i * 2;
        z = i * 3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
        dest.writeInt(z);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<ListItem> CREATOR = new Parcelable.Creator<ListItem>() {
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private ListItem(Parcel in) {

        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
    }
}
