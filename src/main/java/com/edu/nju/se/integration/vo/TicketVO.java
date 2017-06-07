package com.edu.nju.se.integration.vo;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by darxan on 2017/6/7.
 */
@Data
public class TicketVO {


//    private Set<PriceVO> priceVOS;

    private int flightId;

    private String flightNum;

    private String departure;

    private String destination;

    private Date departingDate;

    private Time departingTime;

    private Time arrivingTime;

    private String departingAirport;

    private String arrivingAirport;

    private String punctualRate;

    private String planeType;

    private int lowestPrice;

    private String dataSource;


}
