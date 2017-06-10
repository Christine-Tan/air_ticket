package com.edu.nju.se.integration.dao;

import com.edu.nju.se.integration.dao.base.BaseDao;
import com.edu.nju.se.integration.model.FlightEntity;
import com.edu.nju.se.integration.vo.SearchRestrictVO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by darxan on 2017/6/6.
 */
@Repository
public class FlightDao extends BaseDao<FlightEntity> {

    /**
     * 必须有departingDate，departure，destination三个字段
     * planeType可选
     * flightNum可选
     * @param searchRestrictVO
     * @return
     */
    public List<FlightEntity> searchFlights(SearchRestrictVO searchRestrictVO) {

        String  queryString = ("from FlightEntity flight " +
                "where flight.departingDate = ? " +
                "and flight.departure = ? " +
                "and flight.destination = ? " );

        String planeType = searchRestrictVO.getPlaneType();
        if (planeType!=null&&planeType.length()>0) {
            queryString+=("and flight.planeType like '%"+planeType+"%' ");
        }

        String flightNum = searchRestrictVO.getFlightNum();
        if (flightNum!=null && flightNum.length()>0) {
            queryString+=("and flight.flightNum = ? ");
        }

        Query query = getSession().createQuery(queryString);
        query.setDate(0, searchRestrictVO.getDepartingDate())
            .setParameter(1, searchRestrictVO.getDeparture())
            .setParameter(2 ,searchRestrictVO.getDestination());

        if (flightNum!=null && flightNum.length()>0) {
            query.setParameter((int)3, flightNum);
        }
        return query.list();

    }

    public List<FlightEntity> uniqueFlight(String planeNumber, Date departureDate) {
        return getSession()
                .createQuery("from FlightEntity where flightNum=? and departingDate=?")
                .setParameter(0, planeNumber)
                .setDate(1, departureDate)
                .list();
    }
}
