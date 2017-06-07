package com.edu.nju.se.integration.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by darxan on 2017/6/7.
 */
@Data
public class SearchRestrictVO {



    private Integer page = 1;

    private boolean asc = true;

    private Integer pageSize = 10;

    private String order = "reserveTime";

    private Date departingDateLower ;
    private Date departingDateUpper;
    private Date arrivingDateLower ;
    private Date arrivingDateUpper;



    private String flightNum;

    private String departure;

    private String destination;


    private String departingAirport;
    private String arrivingAirport;

    private String punctualRateLower;
    private String punctualRateUpper;

    private String planeType;

    private String dataSource;

    private int priceUpper;
    private int priceLower;

}
