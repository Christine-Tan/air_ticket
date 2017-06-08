package com.edu.nju.se.integration.vo;

import com.edu.nju.se.integration.model.FlightEntity;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darxan on 2017/6/7.
 */
@Data
public class TicketVO {


    public TicketVO(){}

    public TicketVO(FlightEntity flightEntity){
        flightId = flightEntity.getFlightId();
        flightNum = flightEntity.getFlightNum();
        departure = flightEntity.getDeparture();
        destination = flightEntity.getDestination();
        departingDate = flightEntity.getDepartingDate();
        departingTime = flightEntity.getDepartingTime();
        arrivingTime = flightEntity.getArrivingTime();

        departingAirport = flightEntity.getDepartingAirport();
        arrivingAirport = flightEntity.getArrivingAirport();
        punctualRate = flightEntity.getPunctualRate();
        planeType = flightEntity.getPlaneType();
        lowestPrice = flightEntity.getLowestPrice();
        dataSource = new ArrayList<String>();
        dataSource.add(flightEntity.getDataSource());
    }

    //暂未提供数据
    private List<PriceVO> priceVOS;

    private String flightId;

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

    private List<String> dataSource;


}
