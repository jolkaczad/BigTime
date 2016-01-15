package com.example.w.baidumaps_test45;

/**
 * Created by WL on 10-sty-2016
 *
 * Version 1.1
 */
public class NpPoint {
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

    public void clear()
    {
        id = 0;
        name = "";
        lat = 0;
        lng = 0;
        description = "";
    }
}
