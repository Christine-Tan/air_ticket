package com.edu.nju.se.integration.util;

/**
 * Created by darxan on 2017/2/26.
 */
public class MyPair<E,F> {

    private E first;
    private F second;

    public MyPair(){
    }

    public MyPair(E first, F second) {
        this.first = first;
        this.second = second;
    }

    public E getFirst() {
        return first;
    }
    public void setFirst(E first) {
        this.first = first;
    }
    public F getSecond() {
        return second;
    }
    public void setSecond(F second) {
        this.second = second;
    }
}
