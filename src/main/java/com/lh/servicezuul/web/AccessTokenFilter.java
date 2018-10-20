package com.lh.servicezuul.web;

import com.lh.servicezuul.myClass.MyBlackNameList;
import com.lh.servicezuul.myClass.MyWhiteNameList;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lianghao
 * @date 2018/10/20
 * @function 身份认证过滤器
 * @editLog 过滤IP黑名单和白名单，判断是BS还是CS，判断是否需要身份验证
 */
public class AccessTokenFilter extends ZuulFilter {
    public AccessTokenFilter() {
        super();
        iniFilter();
    }
    private MyWhiteNameList myWhiteNameList;
    private MyBlackNameList myBlackNameList;
    private boolean isEnable;

    private void iniFilter(){
        isEnable = true;
        myWhiteNameList = new MyWhiteNameList();
        myBlackNameList = new MyBlackNameList();
    }

    @Override
    public boolean shouldFilter() {
        String myPath = null;
        RequestContext ctx = RequestContext.getCurrentContext();

        HttpServletRequest request = ctx.getRequest();
        myPath = request.getMethod();
        myPath = request.getContextPath();
        return isEnable;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;

    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String accessToken = request.getParameter("accessToken");
//        Cookie[] myCookie = request.getCookies();
//        Cookie selectCookie = null;
//        if (myCookie.length > 0) {
//            boolean isHaveCookie = false;
//            for (Cookie row : myCookie
//                    ) {
//                String currentCookie = row.getName();
//                if (currentCookie.equals("accessToken")){
//                    isHaveCookie = true;
//                    selectCookie = row;
//
//                }
//            }
//            if (isHaveCookie){
//                String myCookieValue = selectCookie.getValue();
//                if (!myCookieValue.equals("45y457")){
//
//                }
//            }
//        }
        if (accessToken == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("no power");
            return null;
        }
        return null;
    }
}
