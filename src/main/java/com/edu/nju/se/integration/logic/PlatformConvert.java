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
        flight.setLink("link://");
        flight.setPlatFormName("....");
    }

}
