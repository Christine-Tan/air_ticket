package com.edu.nju.se.integration.service;

import com.edu.nju.se.integration.dao.FlightDao;
import com.edu.nju.se.integration.integrate.IntegrateService;
import com.edu.nju.se.integration.model.FlightEntity;
import com.edu.nju.se.integration.service.PredictService;
import com.edu.nju.se.integration.util.XstreamUtil;
import com.edu.nju.se.integration.vo.PlaneVO;
import com.thoughtworks.xstream.XStream;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


/**
 * Created by darxan on 2017/7/8.
 */
@Service
public class PredictImpl implements PredictService {

    @Autowired
    private IntegrateService integrateService;

    private static final long TRANSFORM = 24*60*60*1000;

    private int getDifferenceDate(Date date) {
        long difference = date.getTime() - System.currentTimeMillis()
                + ((long) new Random().nextInt(20))*TRANSFORM;
        return 10 + (int) (difference/TRANSFORM) ;
    }


    private List<PlaneVO> predict(Date departDate, String string) {
        List<PlaneVO> planeVOS = XstreamUtil.toObject(string);
        for (PlaneVO planeVO:planeVOS) {
            planeVO.setShouldMoveUpDays(getDifferenceDate(planeVO.getDepartingDate()));
        }
        return planeVOS;
    }


    public List<PlaneVO> predictBuyDate(Date departDate, String depart, String destination) {
        return predict(departDate, integrateService.predict(depart, destination));
    }

    public List<PlaneVO> predictBuyDate(Date departDate, String flightNum) {
        return predict(departDate, integrateService.predict(flightNum));
    }
}
