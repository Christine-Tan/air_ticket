package com.edu.nju.se.integration.logic;

import com.edu.nju.se.integration.model.FlightEntity;
import com.edu.nju.se.integration.service.PredictService;
import com.edu.nju.se.integration.service.PredictImpl;
import com.edu.nju.se.integration.vo.PlaneVO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

/**
 * Created by darxan on 2017/7/8.
 */
public class TestPredict {

    PredictService predictService;

    public TestPredict() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
        predictService = context.getBean(PredictImpl.class);

//        List<PlaneVO> day = predictService.predictBuyDate(new Date(System.currentTimeMillis()),
//                "HO1191");

        List<PlaneVO> day = predictService.predictBuyDate(new Date(System.currentTimeMillis()),
                "BJS", "SHA");

        System.out.println(day.toString());
    }



    public static void main(String[] args) throws Exception{
        new TestPredict();
    }
}
