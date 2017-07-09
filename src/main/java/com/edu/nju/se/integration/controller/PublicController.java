package com.edu.nju.se.integration.controller;

import com.edu.nju.se.integration.service.PredictService;
import com.edu.nju.se.integration.service.SearchService;
import com.edu.nju.se.integration.util.DateTrans;
import com.edu.nju.se.integration.vo.PlaneVO;
import com.edu.nju.se.integration.vo.SearchRestrictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private SearchService searchService;

    @Autowired
    private PredictService predictService;

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

    @RequestMapping("/search")
    @ResponseBody
    public List<PlaneVO> getSearch(@RequestParam("flightNum") String flightNum,
                                   @RequestParam("departDate") String departDate){
        Date depart = DateTrans.String2Date(departDate,"yyyy-MM-dd");
        return searchService.search(flightNum,depart);
    }

    @RequestMapping("/predict")
    @ResponseBody
    public List<PlaneVO> getPredict(@RequestParam("departCity") String departCity,
                                    @RequestParam("arrivingCity") String arrivingCity,
                                   @RequestParam("departDate") String departDate){
        Date depart = DateTrans.String2Date(departDate,"yyyy-MM-dd");
        return predictService.predictBuyDate(depart, departCity, arrivingCity);
    }

    @RequestMapping("/predict/flightNum")
    @ResponseBody
    public List<PlaneVO> getPredict( @RequestParam("departDate") String departDate,
                                     @RequestParam("flightNum") String flightNum){
        Date depart = DateTrans.String2Date(departDate,"yyyy-MM-dd");
        return predictService.predictBuyDate(depart, flightNum);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public PlaneVO getPlaneDetail(@RequestParam String flightNum,
                                  @RequestParam String departTime){

        Date depart = DateTrans.String2Date(departTime,"yyyy-MM-dd HH:mm:ss");

        PlaneVO vo = searchService.getPrices(flightNum,depart);


        return vo;
    }

    @RequestMapping("/test")
    @ResponseBody
    public List<PlaneVO> test(){
        List<PlaneVO> list = new ArrayList<PlaneVO>();

        PlaneVO vo = new PlaneVO();
        vo.setArrivingAirport("hhhhh");

        PlaneVO p = new PlaneVO();
        p.setCompany("北京");

        list.add(vo);
        list.add(p);

        return list;
    }


}
