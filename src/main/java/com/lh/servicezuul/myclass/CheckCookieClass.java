package com.lh.servicezuul.myclass;

import javax.servlet.http.Cookie;

/**
 * @author 梁昊
 * @date 2018/10/23
 * @function
 * @editLog
 */
public class CheckCookieClass {
    public CheckCookieClass() {
        super();
    }

    private final String CookieName = "AccessToken";

    public boolean isCookieOk(Cookie[] cookies) {
        String myCookie = null;
        if ((cookies == null) || (cookies.length == 0)) {
            return false;
        }
        for (Cookie row : cookies
                ) {
            if (row.getName().equals(CookieName)) {
                myCookie = row.getValue();
            }
        }
        if (myCookie != null) {
            return myCookie.equals("LiangHaoAccessToken") ? true : false;
        } else {
            return false;
        }
    }
}
