package com.edu.nju.se.integration.service;

import com.edu.nju.se.integration.dao.FlightDao;
import com.edu.nju.se.integration.model.FlightEntity;
import com.edu.nju.se.integration.service.PredictService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;


/**
 * Created by darxan on 2017/7/8.
 */
@Service
public class PredictImpl implements PredictService {

    @Autowired
    private FlightDao flightDao;

    private static final long TRANSFORM = 24*60*60*1000;

    private int getDifferenceDate(Date date) {
        long difference = date.getTime() - System.currentTimeMillis()
                + ((long) new Random().nextInt(20))*TRANSFORM;
        return 10 + (int) (difference/TRANSFORM) ;
    }

    public int predictBuyDate(Date departDate, String depart, String destination) {
        FlightEntity flightEntity = flightDao.getLowestFlight(depart, destination);
        return getDifferenceDate(flightEntity.getDepartingDate());
    }

    public int predictBuyDate(Date departDate, String flightNum) {
        FlightEntity flightEntity = flightDao.getLowestFlight(flightNum);
        return getDifferenceDate(flightEntity.getDepartingDate());
    }
}
