package com.nearsoft;

import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * Created by xloeza on 10/8/16.
 */
public  class ApiInstance {
    public static <T> T map(JSONObject source, Class<T> targetClass) throws IllegalAccessException, InstantiationException {
        T target = targetClass.newInstance();
        Field[] fields = targetClass.getDeclaredFields();

        for (Field field:fields) {
            field.set(target, source.getString(field.getName()));
        }

        return target;
    }
}
