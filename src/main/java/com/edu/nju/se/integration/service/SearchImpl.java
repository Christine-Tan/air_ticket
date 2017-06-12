package com.edu.nju.se.integration.service;

import com.edu.nju.se.integration.integrate.IntegrateService;
import com.edu.nju.se.integration.vo.PlaneVO;
import com.edu.nju.se.integration.vo.SearchRestrictVO;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by darxan on 2017/6/12.
 */
@Service
public class SearchImpl implements SearchService {

    @Autowired
    private IntegrateService integrateService;

    private <T> T toObject(String string) {
        XStream xStream = new XStream();
        xStream.alias("list", List.class);
        xStream.alias("plane", PlaneVO.class);
        return (T) xStream.fromXML(string);
    }

    public List<PlaneVO> lowestPrice() {
        return toObject(integrateService.lowestPrice());
    }

    public List<PlaneVO> search(SearchRestrictVO restrict) {
        return toObject(integrateService.search(restrict));
    }

    public List<PlaneVO> search(String flight, Date departure) {
        return toObject(integrateService.search(flight, departure));
    }

    public PlaneVO getPrices(String flightNumber, Date date) {
        return toObject(integrateService.getPrices(flightNumber, date));
    }

    public PlaneVO getPrices(SearchRestrictVO restrictVO) {
        return toObject(integrateService.getPrices(restrictVO));
    }
}
