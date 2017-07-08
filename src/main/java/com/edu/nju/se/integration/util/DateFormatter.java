package com.edu.nju.se.integration.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by darxan on 2017/2/27.
 */
public class DateFormatter {

    public static SimpleDateFormat dateFormat ;

    public static SimpleDateFormat timeFormat ;

    static {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        timeFormat = new SimpleDateFormat("hh:MM");
    }

    public static void main(String[] args) {
        System.out.println(dateFormat.format(new Date()));
    }

}
