package com.edu.nju.se.integration.vo;

import com.edu.nju.se.integration.model.PriceEntity;
import lombok.Data;

/**
 * Created by darxan on 2017/6/7.
 */
@Data
public class PriceVO {

    public PriceVO(){}
    public PriceVO(PriceEntity priceEntity){
        note = priceEntity.getNote();
        price = priceEntity.getPrice();
        priceId = priceEntity.getPriceId();
    }

    private String note;

    private int price;

    private int priceId;

    private TicketVO ticketVO;

}
