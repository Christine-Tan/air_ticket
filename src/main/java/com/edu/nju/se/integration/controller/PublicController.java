package com.edu.nju.se.integration.controller;

import com.edu.nju.se.integration.service.SearchService;
import com.edu.nju.se.integration.tool.*;
import com.edu.nju.se.integration.util.DateTrans;
import com.edu.nju.se.integration.vo.PlaneVO;
import com.edu.nju.se.integration.vo.SearchRestrictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by darxan on 2017/2/14.
 */
@Controller
@RequestMapping(value = "/home")
public class PublicController {

    @Autowired
    SearchService searchService;

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Date.class,
//                new CustomDateEditor(DateFormatter.dateFormat, false));
//    }

    @RequestMapping("")
    @ResponseBody
    public List<PlaneVO> getDefaultList(){
        return searchService.lowestPrice();
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<PlaneVO> getPlaneList(@RequestParam("departCity") String departCity,
                                        @RequestParam("arrivingCity") String arrivingCity,
                                        @RequestParam("departDate") String departDate,
                                        @RequestParam("company") String company) {

        SearchRestrictVO srvo = new SearchRestrictVO();

        srvo.setDeparture(departCity);
        srvo.setDestination(arrivingCity);
        srvo.setDepartingDate(DateTrans.String2Date(departDate,"yyyy-MM-dd"));

        if (!company.equals("all")) {
            srvo.setPlaneType(company);
        }
        return searchService.search(srvo);
    }

    @RequestMapping("/detail")
    public String getPlaneDetail(@RequestParam String flightNum,
                                  @RequestParam String departTime,
                                  HttpSession session){

        Date depart = DateTrans.String2Date(departTime,"yyyy-MM-dd HH:mm:ss");

        PlaneVO vo = searchService.getPrices(flightNum,depart);

        session.setAttribute("plane",vo);

        return "planeDetail";
    }



}
