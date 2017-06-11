package com.edu.nju.se.integration.vo;

import com.edu.nju.se.integration.logic.PlatformConvert;
import com.edu.nju.se.integration.model.FlightEntity;
import com.edu.nju.se.integration.tool.MyFunction;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by darxan on 2017/6/7.
 */
@Data
public class PlaneVO {


    public PlaneVO(){}

    public PlaneVO(FlightEntity flightEntity){

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
        dataSource = new ArrayList<PlatformVO>();

    }

    public PlatformVO addPlane(FlightEntity flight, PlatformConvert platformConvert) {
        PlatformVO platform = new PlatformVO(flight, this, platformConvert);
        dataSource.add(platform);
        if (flight.getLowestPrice()<lowestPrice) {
            lowestPrice = flight.getLowestPrice();
        }
        return platform;
    }

    private String company;

    private String flightId;

    private String flightNum;

    private String departure;

    private String destination;

    private String departureCity;

    private String destinationCity;

    private Date departingDate;

    private Time departingTime;

    private Time arrivingTime;

    private String departingAirport;

    private String arrivingAirport;

    private String punctualRate;

    private String planeType;

    private int lowestPrice;

    private List<PlatformVO> dataSource;

}
