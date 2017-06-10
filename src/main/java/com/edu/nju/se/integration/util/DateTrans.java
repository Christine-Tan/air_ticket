package com.edu.nju.se.integration.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yyy on 2017/6/10.
 */
public class DateTrans {

    public static Date String2Date(String str,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            System.out.println("date format error!");
        }

        return date;
    }

    public static String Date2String(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
