package com.edu.nju.se.integration.logic;

import com.edu.nju.se.integration.service.PredictService;
import com.edu.nju.se.integration.service.PredictImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;

/**
 * Created by darxan on 2017/7/8.
 */
public class TestPredict {

    PredictService predictService;

    public TestPredict() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
        predictService = context.getBean(PredictImpl.class);

        int day = predictService.predictBuyDate(new Date(System.currentTimeMillis()),
                "HO1191");

        System.out.println(day);
    }



    public static void main(String[] args) throws Exception{
        new TestPredict();
    }
}
