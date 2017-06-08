package com.edu.nju.se.integration.dao;

import com.edu.nju.se.integration.dao.base.BaseDao;
import com.edu.nju.se.integration.dao.base.SQLAppend;
import com.edu.nju.se.integration.model.FlightEntity;
import com.edu.nju.se.integration.model.PriceEntity;
import com.edu.nju.se.integration.vo.SearchRestrictVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by darxan on 2017/6/6.
 */
@Repository
public class FlightDao extends BaseDao<FlightEntity> {

    public List<FlightEntity> search(SearchRestrictVO searchRestrictVO) {

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

    public List<FlightEntity> getPrices(String planeNumber) {
        return getSession()
                .createQuery("from FlightEntity where flightNum=?")
                .setParameter(0, planeNumber).list();
    }
}
