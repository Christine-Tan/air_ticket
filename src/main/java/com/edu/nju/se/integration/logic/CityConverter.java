package com.edu.nju.se.integration.logic;

import com.edu.nju.se.integration.dao.CityDao;
import com.edu.nju.se.integration.exception.CityNotFoundException;
import com.edu.nju.se.integration.model.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by darxan on 2017/6/9.
 */
@Component
public class CityConverter {

    private volatile Map<String, String> cityCode;

    @Autowired
    private CityDao cityDao;

    protected synchronized void initMapping() {
        cityCode = new HashMap<String, String>();
        List<CityEntity> cities = cityDao.list();
        for (CityEntity cityEntity : cities) {
            String originCodes = cityCode.get(cityEntity.getCity());
            if (originCodes==null) {
                originCodes = "";
            } else {
                originCodes += ",";
            }
            originCodes += cityEntity.getCode();
            cityCode.put(cityEntity.getCity(), originCodes);
        }
    }

    public String convertCityToCode(String city) throws CityNotFoundException {

        if (cityCode==null) {
            initMapping();
        }
        String code = cityCode.get(city);
        if (code==null) {
            throw new CityNotFoundException();
        }
        return code;
    }


}
