package com.edu.nju.se.integration.service;

import com.edu.nju.se.integration.dao.FlightDao;
import com.edu.nju.se.integration.model.FlightEntity;
import com.edu.nju.se.integration.model.PriceEntity;
import com.edu.nju.se.integration.vo.PriceVO;
import com.edu.nju.se.integration.vo.SearchRestrictVO;
import com.edu.nju.se.integration.vo.TicketVO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by darxan on 2017/6/8.
 */
@Service
public class SearchImpl implements SearchService {

    @Autowired
    private FlightDao flightDao;

    public List<TicketVO> search(SearchRestrictVO restrict) {
        List<FlightEntity> flights = flightDao.search(restrict);
        List<TicketVO> ticketVOS = new ArrayList<TicketVO>(flights.size());
        Map<String, TicketVO> ticketVOMap = new HashMap<String, TicketVO>(flights.size());
        for (FlightEntity flight: flights) {
            String key = flight.getFlightNum()+flight.getDepartingDate().getTime()+flight.getDepartingTime().getTime();
            TicketVO ticketVO = ticketVOMap.get(key);
            if (ticketVO==null) {
                ticketVO = new TicketVO(flight);
                ticketVOMap.put(key, ticketVO);
                ticketVOS.add(ticketVO);
            } else {
                ticketVO.getDataSource().add(flight.getDataSource());
            }
        }
        return ticketVOS;
    }

    public List<PriceVO> getPrices(String flightNumber) {
        List<FlightEntity> flights = flightDao.getPrices(flightNumber);
        for (FlightEntity flight:flights) {

            System.out.println(flight.getDepartingAirport());
            System.out.println(flight.getArrivingAirport());
            System.out.println(flight.getFlightNum());

        }
        System.out.println("=================");
        List<PriceVO> priceVOS = new ArrayList<PriceVO>(flights.size()*3);

        TicketVO ticketVO = null;
        for (FlightEntity flight : flights) {
            if (ticketVO==null){
                ticketVO = new TicketVO(flight);
                ticketVO.setPriceVOS(new ArrayList<PriceVO>(flight.getPriceEntitySet().size()));
            }else {
                ticketVO.getDataSource().add(flight.getDataSource());
            }

            for(PriceEntity price : flight.getPriceEntitySet()) {
                PriceVO priceVO = new PriceVO(price);
                priceVO.setTicketVO(ticketVO);

                ticketVO.getPriceVOS().add(priceVO);
                priceVOS.add(priceVO);
            }
        }

        return priceVOS;
    }


}
