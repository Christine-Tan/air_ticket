package com.edu.nju.se.integration.service;

import com.edu.nju.se.integration.vo.PaginationResult;
import com.edu.nju.se.integration.vo.PriceVO;
import com.edu.nju.se.integration.vo.SearchRestrictVO;
import com.edu.nju.se.integration.vo.TicketVO;

import java.util.List;

/**
 * Created by darxan on 2017/6/7.
 */
public interface SearchService {

    /**
     * query from data base
     *
     * @param restrict include time,  price  , destination and so on
     * @see SearchRestrictVO
     *
     * @return  List<PriceVO>
     * @see  PaginationResult
     */
    List<TicketVO> search(SearchRestrictVO restrict);

    /**
     * show all prices information according to the flightNumber
     * @param flightNumber
     * @return
     */
    List<PriceVO> getPrices(String flightNumber);

}
