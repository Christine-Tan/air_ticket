package com.edu.nju.se.integration.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by darxan on 2017/2/27.
 */
public class DateFormatter {

    public static SimpleDateFormat dateFormat ;

    static {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
    }


}
