package com.edu.nju.se.integration.logic;


import com.edu.nju.se.integration.tool.MyFunction;
import com.edu.nju.se.integration.vo.PlatformVO;
import org.springframework.stereotype.Component;

/**
 * Created by darxan on 2017/6/10.
 */
@Component
public class PlatformConvert  {

    /**
     *
     * @param flight
     * @return
     */
    public void execute(PlatformVO flight) {
        Class thisClass = PlatformConvert.class;
        try {
            thisClass.getMethod(flight.getDataSource(), PlatformVO.class).invoke(this, flight);
        }catch (Exception e) {
            e.printStackTrace();
            flight.setLink("https://www.baidu.com/s?wd="+flight.getDataSource()+"%20"+flight.getPlaneVO().getFlightId());
            flight.setPlatFormName(flight.getDataSource());
        }
    }

    public void ctrip(PlatformVO platform) {
        platform.setPlatFormName("携程");

        String trip = "city_"+platform.getPlaneVO().getDeparture().toLowerCase()
                + "_" + platform.getPlaneVO().getDestination().toLowerCase();
        String date = platform.getPlaneVO().getDepartingDate().toString();
        platform.setLink("http://flights.ctrip.com/booking/"+trip+"-day-1.html?ddate1="+date);

    }

    public void tuniu(PlatformVO platform) {
        platform.setPlatFormName("途牛");
        String trip = "city_"+platform.getPlaneVO().getDeparture().toUpperCase()
                + "_" + platform.getPlaneVO().getDestination().toUpperCase();
        String date = platform.getPlaneVO().getDepartingDate().toString();
        platform.setLink("http://flight.tuniu.com/"+trip+"/?start="+date);
    }

    public void yilong(PlatformVO platform) {
        platform.setPlatFormName("艺龙");
        String trip = platform.getPlaneVO().getDeparture().toLowerCase()
                + "-" + platform.getPlaneVO().getDestination().toLowerCase();
        String date = platform.getPlaneVO().getDepartingDate().toString();
        platform.setLink("http://flight.elong.com/search/"+trip+"/?departdate="+date+"&seatlevel=Y");
    }

    public static void main(String[] args) {
        PlatformVO platformVO = new PlatformVO();
        platformVO.setDataSource("ctrip");
        new PlatformConvert().execute(platformVO);
    }
}
