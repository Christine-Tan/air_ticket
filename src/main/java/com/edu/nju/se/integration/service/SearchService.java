package com.edu.nju.se.integration.service;

import com.edu.nju.se.integration.vo.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by darxan on 2017/6/7.
 */
public interface SearchService {

    /**
     * 推荐的价格最低的机票
     * @return
     */
    List<PlaneVO> lowestPrice();
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
    List<PlaneVO> search(SearchRestrictVO restrict);



    /**
     *
     * @param flight
     * @param departure
     * @return
     */
    List<PlaneVO> search(String flight, Date departure);


    /**
     * show all prices information according to the flightNumber。
     * 每个航班号会有多个平台来源dataSource，每个平台的对应航班会有多个票价
     *
     * @param flightNumber
     * @return
     */
    PlaneVO getPrices(String flightNumber, Date date);

    /**
     *
     * @param restrictVO
     * @return
     *
     */
    PlaneVO gerPrices(SearchRestrictVO restrictVO);


}
