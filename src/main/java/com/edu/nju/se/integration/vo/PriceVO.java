package com.edu.nju.se.integration.vo;

import com.edu.nju.se.integration.model.PriceEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * Created by darxan on 2017/6/7.
 */
@Data
public class PriceVO {

    public PriceVO() {}

    public PriceVO(PriceEntity priceEntity){
        note = priceEntity.getNote();
        price = priceEntity.getPrice();
        priceId = priceEntity.getPriceId();
    }

    private String note;

    private int price;

    private int priceId;

    @JsonIgnore
    private PlatformVO platformVO;
    
    @JsonIgnore
    public PlatformVO getPlatformVO() {
        System.out.println("get plane from platform");
        return platformVO;
    }

    @JsonIgnore
    public void setPlatformVO(PlatformVO platformVO) {
        this.platformVO = platformVO;
    }
}
