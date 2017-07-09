package com.edu.nju.se.integration.util;

import com.edu.nju.se.integration.vo.PlaneVO;
import com.thoughtworks.xstream.XStream;

import java.util.List;

/**
 * Created by darxan on 2017/7/9.
 */
public class XstreamUtil {
    public static  <T> T toObject(String string) {
        XStream xStream = new XStream();
        xStream.alias("list", List.class);
        xStream.alias("plane", PlaneVO.class);
        T a  = (T) xStream.fromXML(string);

        System.out.println(string);
        return a;
    }
}
