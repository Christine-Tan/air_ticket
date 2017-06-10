package com.edu.nju.se.integration.service;

import com.edu.nju.se.integration.dao.FlightDao;
import com.edu.nju.se.integration.logic.CityConverter;
import com.edu.nju.se.integration.logic.PlatformConvert;
import com.edu.nju.se.integration.model.FlightEntity;
import com.edu.nju.se.integration.model.PriceEntity;
import com.edu.nju.se.integration.vo.PlaneVO;
import com.edu.nju.se.integration.vo.PlatformVO;
import com.edu.nju.se.integration.vo.PriceVO;
import com.edu.nju.se.integration.vo.SearchRestrictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by darxan on 2017/6/8.
 */
@Service
public class SearchImpl implements SearchService {

    @Autowired
    private FlightDao flightDao;

    @Autowired
    private CityConverter cityConverter;

    @Autowired
    private PlatformConvert  platformConvert;

    public List<PlaneVO> search(SearchRestrictVO restrict) {
        try {
            validParameters(restrict);
            return convertToPlane(flightDao.searchFlights(restrict));
        }catch (Exception e) {
            return Collections.emptyList();
        }

    }

    public List<PlaneVO> search(String flight, Date departure) {
        return convertToPlane(flightDao.uniqueFlight(flight, departure));
    }

    public PlaneVO getPrices(String flightNumber, Date date) {
        return convertToPrice(flightDao.uniqueFlight(flightNumber, date));
    }

    public PlaneVO gerPrices(SearchRestrictVO restrictVO) {
        try {
            validParameters(restrictVO);
            return convertToPrice(flightDao.searchFlights(restrictVO));
        }catch (Exception e) {
            return null;
        }
    }

    private void validParameters(SearchRestrictVO restrict) throws Exception {
        restrict.setDestination(cityConverter.convertCityToCode(restrict.getDestination()));
        restrict.setDeparture(cityConverter.convertCityToCode(restrict.getDeparture()));
    }

    private PlaneVO convertToPrice(List<FlightEntity> flights) {

        if (flights==null||flights.size()==0) {
            return null;
        }

        PlaneVO planeVO = null;
        for (FlightEntity flight : flights) {

            if (planeVO ==null){
                planeVO = new PlaneVO(flight);
            }
            PlatformVO platformVO = planeVO.addPlane(flight, platformConvert);

            for(PriceEntity price : flight.getPriceEntitySet()) {
                PriceVO priceVO = new PriceVO(price);
                priceVO.setPlatformVO(platformVO);
                platformVO.getPriceVOS().add(priceVO);
            }
        }

        return planeVO;
    }

    private List<PlaneVO> convertToPlane(List<FlightEntity> flights) {
        List<PlaneVO> planeVOS = new ArrayList<PlaneVO>(flights.size());
        Map<String, PlaneVO> ticketVOMap = new HashMap<String, PlaneVO>(flights.size());

        for (FlightEntity flight: flights) {

            String key = flight.getFlightNum()+flight.getDepartingDate().getTime()+flight.getDepartingTime().getTime();

            PlaneVO planeVO = ticketVOMap.get(key);
            if (planeVO ==null) {
                planeVO = new PlaneVO(flight);

                ticketVOMap.put(key, planeVO);
                planeVOS.add(planeVO);
            }

            planeVO.addPlane(flight, platformConvert);

        }
        return planeVOS;
    }


}
