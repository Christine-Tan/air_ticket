package com.edu.nju.se.integration.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by darxan on 2017/6/7.
 */
@Data
@Entity
@Table(name = "flight", schema = "ticket", catalog = "")
public class FlightEntity {
    @Id
    @Column(name = "flight_id")
    private int flightId;
    @Basic
    @Column(name = "flight_num")
    private String flightNum;
    @Basic
    @Column(name = "departure")
    private String departure;
    @Basic
    @Column(name = "destination")
    private String destination;
    @Basic
    @Column(name = "departing_date")
    private Date departingDate;
    @Basic
    @Column(name = "departing_time")
    private Time departingTime;
    @Basic
    @Column(name = "arriving_time")
    private Time arrivingTime;
    @Basic
    @Column(name = "departing_airport")
    private String departingAirport;
    @Basic
    @Column(name = "arriving_airport")
    private String arrivingAirport;
    @Basic
    @Column(name = "punctual_rate")
    private String punctualRate;
    @Basic
    @Column(name = "plane_type")
    private String planeType;
    @Basic
    @Column(name = "lowest_price")
    private int lowestPrice;
    @Basic
    @Column(name = "data_source")
    private String dataSource;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightEntity that = (FlightEntity) o;

        if (flightId != that.flightId) return false;
        if (lowestPrice != that.lowestPrice) return false;
        if (flightNum != null ? !flightNum.equals(that.flightNum) : that.flightNum != null) return false;
        if (departure != null ? !departure.equals(that.departure) : that.departure != null) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (departingDate != null ? !departingDate.equals(that.departingDate) : that.departingDate != null)
            return false;
        if (departingTime != null ? !departingTime.equals(that.departingTime) : that.departingTime != null)
            return false;
        if (arrivingTime != null ? !arrivingTime.equals(that.arrivingTime) : that.arrivingTime != null) return false;
        if (departingAirport != null ? !departingAirport.equals(that.departingAirport) : that.departingAirport != null)
            return false;
        if (arrivingAirport != null ? !arrivingAirport.equals(that.arrivingAirport) : that.arrivingAirport != null)
            return false;
        if (punctualRate != null ? !punctualRate.equals(that.punctualRate) : that.punctualRate != null) return false;
        if (planeType != null ? !planeType.equals(that.planeType) : that.planeType != null) return false;
        if (dataSource != null ? !dataSource.equals(that.dataSource) : that.dataSource != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flightId;
        result = 31 * result + (flightNum != null ? flightNum.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (departingDate != null ? departingDate.hashCode() : 0);
        result = 31 * result + (departingTime != null ? departingTime.hashCode() : 0);
        result = 31 * result + (arrivingTime != null ? arrivingTime.hashCode() : 0);
        result = 31 * result + (departingAirport != null ? departingAirport.hashCode() : 0);
        result = 31 * result + (arrivingAirport != null ? arrivingAirport.hashCode() : 0);
        result = 31 * result + (punctualRate != null ? punctualRate.hashCode() : 0);
        result = 31 * result + (planeType != null ? planeType.hashCode() : 0);
        result = 31 * result + lowestPrice;
        result = 31 * result + (dataSource != null ? dataSource.hashCode() : 0);
        return result;
    }
}
