package com.edu.nju.se.integration.logic;

import com.edu.nju.se.integration.dao.CompanyDao;
import com.edu.nju.se.integration.exception.CityNotFoundException;
import com.edu.nju.se.integration.exception.CompanyNotFoundException;
import com.edu.nju.se.integration.model.CityEntity;
import com.edu.nju.se.integration.model.CompanyEntity;
import com.edu.nju.se.integration.vo.PlaneVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by darxan on 2017/6/10.
 */
@Component
public class CompanyConvertor {
    private volatile Map<String, String> companyMapping;

    @Autowired
    private CompanyDao companyDao;

    protected synchronized void initMapping() {
        companyMapping = new HashMap<String, String>();
        List<CompanyEntity> companies = companyDao.list();
        for (CompanyEntity company : companies) {
            companyMapping.put(company.getCode(), company.getName());
        }
    }

    public String convertCodeToCompany(String code) throws CompanyNotFoundException {

        if (companyMapping ==null) {
            initMapping();
        }
        String company = companyMapping.get(code.substring(0,2));
        if (company==null) {
            throw new CompanyNotFoundException();
        }
        return company;
    }

    public void convertCode(PlaneVO planeVO) throws CompanyNotFoundException {
        planeVO.setCompany(convertCodeToCompany(planeVO.getFlightNum()));
    }

    public void convertCode(List<PlaneVO> planeVOS) throws CompanyNotFoundException {
        for (PlaneVO vo:planeVOS) {
            convertCode(vo);
        }
    }
}
