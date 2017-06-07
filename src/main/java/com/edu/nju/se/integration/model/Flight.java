package com.edu.nju.se.integration.model;

import com.edu.nju.se.integration.model.base.BaseTable;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by darxan on 2017/6/6.
 */
@Entity
@Table(name = "flight", schema = "ticket")
@Data
public class Flight extends BaseTable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "native")
    @GeneratedValue(generator = "idGenerator")
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
