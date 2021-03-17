package com.example.activityreportingservice;

import java.util.Date;

public class Point {
    Date date;
    double value;
    public Point(Date date, double value){
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }


    public double getValue() {
        return value;
    }

}
