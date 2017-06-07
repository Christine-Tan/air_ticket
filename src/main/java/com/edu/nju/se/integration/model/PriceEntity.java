package com.edu.nju.se.integration.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by darxan on 2017/6/7.
 */
@Data
@Entity
@Table(name = "price", schema = "ticket", catalog = "")
public class PriceEntity {

    @Basic
    @Column(name = "note")
    private String note;

    @Basic
    @Column(name = "price")
    private int price;

    @Id
    @Column(name = "price_id")
    private int priceId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceEntity that = (PriceEntity) o;

        if (price != that.price) return false;
        if (priceId != that.priceId) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = note != null ? note.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + priceId;
        return result;
    }
}
