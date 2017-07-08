package com.edu.nju.se.integration.service;

import java.sql.Date;

/**
 * Created by darxan on 2017/7/8.
 */
public interface PredictService {

    /**
     * 预测应当买票的日期
     * @return 返回的一个天数， 指的是在出发日期前预先多久买票
     */
    int predictBuyDate(Date departDate, String depart, String destination);

    /**
     * 预测应当买票的日期
     * @return 返回的一个天数， 指的是在出发日期前预先多久买票
     */
    int predictBuyDate(Date departDate, String flightNum);

}
