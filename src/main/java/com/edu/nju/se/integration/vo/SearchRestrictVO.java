package com.edu.nju.se.integration.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by darxan on 2017/6/7.
 */
@Data
public class SearchRestrictVO {

    //排序中只能有一个生效
    //价格排序: true 表示从高到底
    private boolean priceOrder;
    //出发时间排序 true 表示从晚到早
    private boolean departingTimeOrder;
    //到达时间排序 true 表示从晚到早
    private boolean arrivingTimeOrder;
    //准点率排序 true 表示从低到高
    private boolean punctualRateOrder;
    //
    //出发日期
    private Date departingDate ;
    //航空公司
    private String planeType;
    //航班号
    private String flightNum;
    //出发城市
    private String departure;
    //目的城市
    private String destination;

//    页码
//    private Integer page = 1;
//    每页大小
//    private Integer pageSize = 10;

//    private Date departingDateLower ;
//    private Date departingDateUpper ;

//    private Date arrivingDateLower ;
//    private Date arrivingDateUpper ;

//    private String departingAirport;
//    private String arrivingAirport;
//
//    private String punctualRateLower;
//    private String punctualRateUpper;

//    private String planeType;

//    private String dataSource;
//
//    private int priceUpper;
//    private int priceLower;

}
