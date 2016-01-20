package com.example.w.baidumaps_test46;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by WL on 10-sty-2016
 * <p>
 * Version 1.1
 */
public class NpPointsFromXML implements GetNpPoints{

    ArrayList<NpPoint> npPointList = new ArrayList<NpPoint>();

    private ListIterator<NpPoint> litr;
    private int pointCount = 0;

    public int getPointCount() {
        return pointCount;
    }

    public NpPointsFromXML(XmlResourceParser xpp) {
        // TODO: find a way to rewind the XmlResourceParser
        NpPoint npPoint_tmp = new NpPoint();

        litr = npPointList.listIterator();

        /**
         * Import data from XML resource into an ArrayList
         */
        int eventType = 0;
        try {
            eventType = xpp.getEventType();
            String tag_name = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    tag_name = xpp.getName();

                    if (tag_name.equals("np_poi")) {
                        npPoint_tmp = new NpPoint();
                    }

                } else if (eventType == XmlPullParser.TEXT) {

                    if (tag_name.equals("name")) {
                        npPoint_tmp.name = xpp.getText();
                    } else if (tag_name.equals("lat")) {
                        npPoint_tmp.lat = Double.valueOf(xpp.getText());
                    } else if (tag_name.equals("lng")) {
                        npPoint_tmp.lng = Double.valueOf(xpp.getText());
                    } else if (tag_name.equals("description")) {
                        npPoint_tmp.description = xpp.getText();
                        npPoint_tmp.description.replace("\n", "");
                    } else if(tag_name.equals("type")) {
                        npPoint_tmp.type = Integer.valueOf(xpp.getText());
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("np_poi")) {
                        npPointList.add(npPoint_tmp);
                        pointCount++;
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
        litr = npPointList.listIterator();
    }

    @Override
    public NpPoint getNextItem() {
        NpPoint npPoint;

        if (litr.hasNext()) {
            npPoint = litr.next();
        }
        else{
            npPoint = null;
        }
        return npPoint;
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

        NpPoint npPoint_tmp;

        Iterator<NpPoint> itr = npPointList.iterator();
        while (itr.hasNext()) {
            npPoint_tmp = itr.next();
            s = new StringBuilder(s).
                    append("\t" + npPoint_tmp.name + "; ").
                    append("\t" + npPoint_tmp.description + "; ").
                    append("\t" + String.valueOf(npPoint_tmp.lat)+ " ").
                    append("\t" + String.valueOf(npPoint_tmp.lng) + "\n").toString();
        }

        return s;
    }
}
