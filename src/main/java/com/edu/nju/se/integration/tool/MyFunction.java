package com.edu.nju.se.integration.tool;

/**
 * Created by darxan on 2017/2/19.
 */
public interface MyFunction<R, T> {

    R execute(T parameter);
}
