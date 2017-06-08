package com.edu.nju.se.integration.vo;

import java.util.List;

/**
 * 分页包装
 * Created by darxan on 2017/2/25.
 */
public class PaginationResult<T> {

    public PaginationResult(){}

    public PaginationResult(List items, int totalPages) {
        this.items = items;
        this.totalPages = totalPages;
    }

    private List<T> items;

    private int totalPages;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "PaginationResult{" +
                "items=" + items +
                ", totalPages=" + totalPages +
                '}';
    }
}
