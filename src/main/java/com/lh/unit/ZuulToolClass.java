package com.lh.unit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁昊
 * @date 2019/8/28
 * @function
 * @editLog
 */
public class ZuulToolClass {
    /**
     * 是否为规定内的域名
     *
     * @param myOrigin 域名
     * @return
     */
    public static boolean getOriginValid(String myOrigin) {
        if (myOrigin == null || myOrigin.length() == 0) {
            return false;
        }
        List<String> list = new ArrayList<>();
        list.add("http://localhost:63342");
        list.add("http://www.lh.com");

        int i = list.indexOf(myOrigin);
        boolean isValid = i > -1 ? true : false;
        list.clear();
        return isValid;
    }
}
