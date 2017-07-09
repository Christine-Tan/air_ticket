package com.edu.nju.se.integration.dataservice;

import com.edu.nju.se.integration.dao.FlightDao;
import com.edu.nju.se.integration.logic.CityConverter;
import com.edu.nju.se.integration.logic.CompanyConvertor;
import com.edu.nju.se.integration.logic.PlatformConvert;
import com.edu.nju.se.integration.model.FlightEntity;
import com.edu.nju.se.integration.model.PriceEntity;
import com.edu.nju.se.integration.vo.PlaneVO;
import com.edu.nju.se.integration.vo.PlatformVO;
import com.edu.nju.se.integration.vo.PriceVO;
import com.edu.nju.se.integration.vo.SearchRestrictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by darxan on 2017/6/8.
 */
@Component
public class DBSearchImpl implements DBSearchService {

    @Autowired
    private FlightDao flightDao;

    @Autowired
    private CityConverter cityConverter;

    @Autowired
    private PlatformConvert  platformConvert;

    @Autowired
    private CompanyConvertor companyConvertor;

    public List<PlaneVO> lowestPrice() {
        try {
            return convertToPlane(flightDao.lowestPrice(15));
        }catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<PlaneVO> search(SearchRestrictVO restrict) {
        try {
            validParameters(restrict);
            List<PlaneVO> planeVOS = convertToPlane(flightDao.searchFlights(restrict));
            companyConvertor.convertCode(planeVOS);
            return planeVOS;
        }catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    public List<PlaneVO> search(String flight, Date departure) {
        try {
            List<PlaneVO> planeVOS = convertToPlane(flightDao.uniqueFlight(flight, departure));
            return planeVOS;
        }catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<PlaneVO> predict(String depart, String destination) {
        try {
            List<PlaneVO> planeVOS = convertToPlane(
                    flightDao.getLowestFlightGroupByFlightNum(depart, destination));
            return planeVOS;
        }catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<PlaneVO> predict(String flightNum) {
        try {
            List<PlaneVO> planeVOS = convertToPlane(
                    flightDao.getLowestFlightByFlightNum(flightNum));
            return planeVOS;
        }catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public PlaneVO getPrices(String flightNumber, Date date) {
        try {
            PlaneVO planeVO = convertToPrice(flightDao.uniqueFlight(flightNumber, date));
            return planeVO;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PlaneVO getPrices(SearchRestrictVO restrictVO) {
        try {
            validParameters(restrictVO);
            PlaneVO planeVO = convertToPrice(flightDao.searchFlights(restrictVO));
            return planeVO;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void validParameters(SearchRestrictVO restrict) throws Exception {
        restrict.setDestination(cityConverter.convertCityToCode(restrict.getDestination()));
        restrict.setDeparture(cityConverter.convertCityToCode(restrict.getDeparture()));
    }

    private PlaneVO convertToPrice(List<FlightEntity> flights) throws Exception{

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

        companyConvertor.convertCode(planeVO);
        cityConverter.convertCodeToCity(planeVO);

        return planeVO;
    }

    private List<PlaneVO> convertToPlane(List<FlightEntity> flights) throws Exception{
        List<PlaneVO> planeVOS = new ArrayList<PlaneVO>(flights.size());
        Map<String, PlaneVO> ticketVOMap = new HashMap<String, PlaneVO>(flights.size());

        for (FlightEntity flight: flights) {

            String key = flight.getFlightNum()+flight.getDepartingDate().getTime()
//                    +flight.getDepartingTime().toString().substring(0,2)
                    ;

            PlaneVO planeVO = ticketVOMap.get(key);

            if (planeVO ==null) {
                planeVO = new PlaneVO(flight);
                ticketVOMap.put(key, planeVO);
                planeVOS.add(planeVO);
            }

            planeVO.addPlane(flight, platformConvert);

        }
        companyConvertor.convertCode(planeVOS);
        cityConverter.convertCodeToCity(planeVOS);
        return planeVOS;
    }


}
