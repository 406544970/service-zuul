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
        String nowOrigin = myOrigin
                .replace("https://", "")
                .replace("http://", "");
        String[] split = nowOrigin.split(":");
        int length = split.length;
        if (length > 1) {
            String replaceContent = ":" + split[length - 1];
            nowOrigin = nowOrigin.replace(replaceContent, "");
        }

        List<String> list = new ArrayList<>();
        list.add("localhost");
        list.add("www.lh.com");
        list.add("cp.lh.com");
        list.add("cj.lh.com");
        list.add("wmj.lh.com");
        list.add("lj.lh.com");

        int i = list.indexOf(nowOrigin);
        list.clear();
        boolean isValid = i > -1 ? true : false;
        return isValid;
    }
}
