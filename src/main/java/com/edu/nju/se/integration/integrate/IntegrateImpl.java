package com.edu.nju.se.integration.integrate;

import com.edu.nju.se.integration.dataservice.DBSearchService;
import com.edu.nju.se.integration.vo.PlaneVO;
import com.edu.nju.se.integration.vo.SearchRestrictVO;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by darxan on 2017/6/12.
 */
@Component
public class IntegrateImpl implements IntegrateService {

    @Autowired
    private DBSearchService DBSearchService;

    private String toXML(Object o) {
        XStream xStream = new XStream();
        xStream.alias("list", List.class);
        xStream.alias("plane", PlaneVO.class);
        return xStream.toXML(o);
    }

    public String lowestPrice() {
        return toXML(DBSearchService.lowestPrice());
    }

    public String search(SearchRestrictVO restrict) {
        return toXML(DBSearchService.search(restrict));
    }

    public String search(String flight, Date departure) {
        return toXML(DBSearchService.search(flight, departure));
    }

    public String getPrices(String flightNumber, Date date) {
        return toXML(DBSearchService.getPrices(flightNumber, date));
    }

    public String getPrices(SearchRestrictVO restrictVO) {
        return toXML(DBSearchService.getPrices(restrictVO));
    }
}
