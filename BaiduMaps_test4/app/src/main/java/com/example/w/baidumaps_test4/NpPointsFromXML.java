package com.example.w.baidumaps_test4;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by WL on 10-sty-2016
 * <p>
 * Version 1.0
 */
public class NpPointsFromXML implements GetNpPoints{

    ArrayList<NpPoints> npPointsList = new ArrayList<NpPoints>();

    public NpPointsFromXML(XmlResourceParser xpp) {
        // TODO: find a way to reset the XmlResourceParser
        NpPoints npPoints_tmp = new NpPoints();

        /**
         * Import data from XML resource into an ArrayList
         */
        int eventType = 0;
        int line;
        try {
            eventType = xpp.getEventType();
            String tag_name = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                line = xpp.getLineNumber();
                if (eventType == XmlPullParser.START_TAG) {
                    tag_name = xpp.getName();

                    if (tag_name.equals("np_poi")) {
                        npPoints_tmp = new NpPoints();
                    }

                } else if (eventType == XmlPullParser.TEXT) {

                    if (tag_name.equals("name")) {
                        npPoints_tmp.name = xpp.getText();
                    } else if (tag_name.equals("lat")) {
                        npPoints_tmp.lat = Double.valueOf(xpp.getText());
                    } else if (tag_name.equals("lng")) {
                        npPoints_tmp.lng = Double.valueOf(xpp.getText());
                    } else if (tag_name.equals("description")) {
                        npPoints_tmp.description = xpp.getText();
                        npPoints_tmp.description.replace("\n", "");
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("np_poi")) {
                        npPointsList.add(npPoints_tmp);
                    }
                    if (xpp.getName().equals("data")) {
                        ;
                    }
                }
                eventType = xpp.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    ListIterator<NpPoints> litr = npPointsList.listIterator();
    @Override
    public NpPoints getNextItem() {
        NpPoints npPoints = null;
        if (litr.hasNext()) {
            npPoints = litr.next();
        }
        return npPoints;
    }

    @Override
    public void rewind(){
        while(litr.hasPrevious()){
            litr.previous();
        }
    }

    @Override
    public String toString(){
        String s = new String();

        NpPoints npPoints_tmp;

        Iterator<NpPoints> itr = npPointsList.iterator();
        while (itr.hasNext()) {
            npPoints_tmp = itr.next();
            s = new StringBuilder(s).
                    append("\t" + npPoints_tmp.name + "; ").
                    append("\t" + npPoints_tmp.description + "; ").
                    append("\t" + String.valueOf(npPoints_tmp.lat)+ " ").
                    append("\t" + String.valueOf(npPoints_tmp.lng) + "\n").toString();
        }

//        s = "Our house; We live here,,,Our house; We live here,,,Our house; We live here";

        return s;
    }
}
