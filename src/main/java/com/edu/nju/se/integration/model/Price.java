package com.edu.nju.se.integration.model;

import com.edu.nju.se.integration.model.base.BaseTable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by darxan on 2017/6/6.
 */
@Entity
@Table(name = "price", schema = "ticket")
@Data
public class Price extends BaseTable {
    @Id
    private int flightId;
    private String note;
    private int price;
}
