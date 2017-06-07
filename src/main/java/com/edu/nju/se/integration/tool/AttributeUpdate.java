package com.edu.nju.se.integration.tool;

import java.lang.reflect.Field;

/**
 * Created by darxan on 2017/2/18.
 */
public class AttributeUpdate{


    /**
     * 更新所有nullable中不为null的属性值到base中
     * @param base
     * @param nullable
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> boolean update(T base, T nullable, Class<T> tClass) {

        Field[] fields = tClass.getDeclaredFields();

        try {
            for (Field field:fields) {
                field.setAccessible(true);
                if (field.get(nullable)!=null) {
                    if(!field.get(nullable).equals("")) {
                        field.set(base, field.get(nullable));
                    }
                }
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static <T> boolean clearObject(T object, Class<T> tClass) {
        Field[] fields = tClass.getDeclaredFields();

        try {
            for (Field field:fields) {
                field.setAccessible(true);
                if(field.getType()==String.class) {
                    if (field.get(object)!=null) {
                        if (field.get(object).equals("")) {
                            field.set(object, null);
                        }
                    }

                }
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
