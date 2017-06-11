package com.edu.nju.se.integration.vo;

import com.edu.nju.se.integration.logic.PlatformConvert;
import com.edu.nju.se.integration.model.FlightEntity;
import com.edu.nju.se.integration.tool.MyFunction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darxan on 2017/6/10.
 */
@Data
public class PlatformVO {

    public PlatformVO(){}

    public PlatformVO(FlightEntity flightEntity, PlaneVO planeVO, PlatformConvert platformConvert){
        this.dataSource = flightEntity.getDataSource();
        this.planeVO = planeVO;
        this.lowestPrice = flightEntity.getLowestPrice();
        this.priceVOS = new ArrayList<PriceVO>();

        platformConvert.execute(this);
    }

    //ctrip
    private String dataSource;
    //携程
    private String platformName;
    //最低价格
    private double lowestPrice;
    //航班
    @JsonIgnore
    private transient PlaneVO planeVO;

    @JsonIgnore
    public PlaneVO getPlaneVO() {

        return planeVO;
    }

    @JsonIgnore
    public void setPlaneVO(PlaneVO planeVO) {
        this.planeVO = planeVO;
    }

    //链接
    private String link;

    //不需要的时候不会加载
    private List<PriceVO> priceVOS;


}
