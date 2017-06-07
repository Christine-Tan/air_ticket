package com.edu.nju.se.integration.service;

import com.edu.nju.se.integration.vo.PaginationResult;
import com.edu.nju.se.integration.vo.SearchRestrictVO;

/**
 * Created by darxan on 2017/6/7.
 */
public interface SearchService {

    /**
     * query from data base
     *
     * @param restrict include time interval,  price interval , destination and so on
     * @see SearchRestrictVO
     *
     * @return {@link PaginationResult#items} List<PriceVO>, count of page and current page number
     * @see  PaginationResult
     */
    PaginationResult search(SearchRestrictVO restrict);
}
