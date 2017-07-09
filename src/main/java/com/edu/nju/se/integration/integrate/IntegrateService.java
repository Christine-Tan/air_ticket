package com.edu.nju.se.integration.integrate;

import com.edu.nju.se.integration.vo.PaginationResult;
import com.edu.nju.se.integration.vo.PlaneVO;
import com.edu.nju.se.integration.vo.SearchRestrictVO;

import java.util.Date;
import java.util.List;

/**
 * Created by darxan on 2017/6/7.
 */
public interface IntegrateService {

    /**
     * 推荐的价格最低的机票
     * @return
     */
    String lowestPrice();
    /**
     * query from data base
     * 一个PlaneVO对应的的是某个时间的航班，会有多个平台来源dataSource。
     * ticketVO没有票价信息，每个来源会有多个票价。
     * @param restrict include time,  price  , destination and so on
     * @see SearchRestrictVO
     *
     * @return  List<PriceVO>
     * @see  PaginationResult
     */
    String search(SearchRestrictVO restrict);



    /**
     *
     * @param flight
     * @param departure
     * @return
     */
    String search(String flight, Date departure);


    /**
     * show all prices information according to the flightNumber。
     * 每个航班号会有多个平台来源dataSource，每个平台的对应航班会有多个票价
     *
     * @param flightNumber
     * @return
     */
    String getPrices(String flightNumber, Date date);

    /**
     *
     * @param restrictVO
     * @return
     *
     */
    String getPrices(SearchRestrictVO restrictVO);


    String predict(String depart, String destination);

    String predict(String flightNum) ;
}
