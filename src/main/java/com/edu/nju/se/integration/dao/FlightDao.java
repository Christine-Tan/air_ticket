package com.edu.nju.se.integration.dao;

import com.edu.nju.se.integration.dao.base.BaseDao;
import com.edu.nju.se.integration.model.FlightEntity;
import com.edu.nju.se.integration.util.StringTool;
import com.edu.nju.se.integration.util.Toolkit;
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

    private static final String separator = ",";
    /**
     * 必须有departingDate，departure，destination三个字段
     * planeType可选
     * flightNum可选
     * @param searchRestrictVO
     * @return
     */
    public List<FlightEntity> searchFlights(SearchRestrictVO searchRestrictVO) {

        String  queryString = "from FlightEntity flight where flight.departingDate = ? ";


        String[] planeCompany = Toolkit.emptyArray;
        if (StringTool.validString(searchRestrictVO.getPlaneType())) {
            planeCompany = searchRestrictVO.getPlaneType().split(separator);
            queryString += StringTool.appendSQL(planeCompany.length, " flight.flightNum like ");
        }

        String[]  departures = Toolkit.emptyArray;
        if (StringTool.validString(searchRestrictVO.getDeparture())) {
            departures = searchRestrictVO.getDeparture().split(separator);
            queryString += StringTool.appendSQL(departures.length, " flight.departure = ");
        }

        String[]  destinations = Toolkit.emptyArray;
        if (StringTool.validString(searchRestrictVO.getDestination())) {
            destinations = searchRestrictVO.getDestination().split(separator);
            queryString += StringTool.appendSQL(destinations.length, " flight.destination = ");
        }

        String flightNum = searchRestrictVO.getFlightNum();
        boolean isValidFlightNum = (flightNum!=null && flightNum.length()>0);

        if (isValidFlightNum) {
            queryString+=(" and flight.flightNum = ? ");
        }

        System.out.println(queryString);
        Query query = getSession().createQuery(queryString);

        int i = 0;
        query.setDate( i++, searchRestrictVO.getDepartingDate() );
        for (String parameter : planeCompany ) { query.setParameter( i++, parameter+"%" ); }
        for (String parameter : departures )   { query.setParameter( i++, parameter ); }
        for (String parameter : destinations ) { query.setParameter( i++, parameter ); }
        if (isValidFlightNum) { query.setParameter(i, flightNum); }

        return query.list();

    }

    public List<FlightEntity> lowestPrice(int top) {
        return getSession().createQuery("from FlightEntity  order by lowestPrice asc").setMaxResults(top).list();
    }

    public List<FlightEntity> uniqueFlight(String planeNumber, Date departureDate) {
        return getSession()
                .createQuery("from FlightEntity where flightNum=? and departingDate=?")
                .setParameter(0, planeNumber)
                .setDate(1, departureDate)
                .list();
    }

    public FlightEntity getLowestFlight(String departure, String destination) {
        return (FlightEntity)getSession()
                .createQuery("from FlightEntity where departure=? and destination=? order by lowestPrice asc")
                .setParameter(0, departure)
                .setParameter(1, destination)
                .setMaxResults(1)
                .uniqueResult();
    }

    public FlightEntity getLowestFlight(String flightNum) {
        return (FlightEntity)getSession()
                .createQuery("from FlightEntity where flightNum=? ORDER BY lowestPrice asc ")
                .setParameter(0, flightNum)
                .setMaxResults(1)
                .uniqueResult();
    }

    public List<FlightEntity> getLowestFlightGroupByFlightNum(String departure, String destination) {
        String sql = "SELECT a.flight_id, a.flight_num,a.departure,a.destination, " +
                "  a.departing_date,a.departing_time,a.arriving_time,a.departing_airport,a.arriving_airport, " +
                "  a.punctual_rate,a.plane_type,min(a.lowest_price) as lowest_price,a.data_source " +
                "  FROM flight a  " +
                "  WHERE a.departure=? AND a.destination=?  " +
                "  GROUP BY a.flight_num;";
        return getListBySQL(sql, departure, destination);
    }

    public List<FlightEntity> getLowestFlightByFlightNum(String flightNum) {
        String sql = "SELECT a.flight_id, a.flight_num,a.departure,a.destination, " +
                "  a.departing_date,a.departing_time,a.arriving_time,a.departing_airport,a.arriving_airport, " +
                "  a.punctual_rate,a.plane_type,min(a.lowest_price) as lowest_price,a.data_source " +
                "FROM flight a " +
                "WHERE a.flight_num=?";
        return getListBySQL(sql, flightNum);
    }
}
