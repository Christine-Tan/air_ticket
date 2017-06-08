package com.edu.nju.se.integration.logic;

import com.edu.nju.se.integration.service.SearchImpl;
import com.edu.nju.se.integration.service.SearchService;
import com.edu.nju.se.integration.vo.PriceVO;
import com.google.gson.Gson;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * Created by darxan on 2017/6/8.
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
        SearchService service = context.getBean(SearchImpl.class);
        for (PriceVO priceVO:service.getPrices("MU9106")) {
            System.out.println(priceVO.getPrice());
            System.out.println(priceVO.getTicketVO().getFlightNum());
        }
    }
}
