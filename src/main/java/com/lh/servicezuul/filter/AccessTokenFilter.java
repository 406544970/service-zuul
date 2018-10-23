package com.lh.servicezuul.filter;

import com.lh.servicezuul.model.ReturnModel;
import com.lh.servicezuul.myClass.*;
import com.lh.servicezuul.myenum.OperateClass;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 梁昊
 * @date 2018/10/20
 * @function 身份认证过滤器
 * @editLog 过滤IP黑名单和白名单，判断是BS还是CS，判断是否需要身份验证
 */
public class AccessTokenFilter extends ZuulFilter {
    public AccessTokenFilter() {
        super();
        iniFilter();
    }
    private final String TokenName = "AccessToken";
    private MyWhiteNameList myWhiteNameList;

    private void iniFilter() {
        myWhiteNameList = new MyWhiteNameList();
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public Object run() {
        boolean isCheck;
        int nStatusCode = 401;
        String myBody = null;
        ReturnModel returnModel = new ReturnModel();
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String useIp = request.getRemoteAddr();
        returnModel.isok = true;
        String useContextPath = request.getContextPath();
        IsCheckWhitePath isCheckWhitePath = new IsCheckWhitePath();

        if (isCheckWhitePath.isCheckWhite(useContextPath)) {
            isCheck = myWhiteNameList.isAllow(useIp);
            if (!isCheck) {
                returnModel.isok = false;
                returnModel.message = String.format("%s:您的IP未在白名单中，不允许访问！", returnModel.message);
            } else {
                isCheck = true;
            }
        } else {
            isCheck = true;
        }

        if (isCheck) {
            OperateTypeClass operateTypeClass = new OperateTypeClass();
            OperateClass.CheckIdentityEnum checkIdentityEnum = operateTypeClass.GetOperateType(useIp);

            switch (checkIdentityEnum) {
                case IS_CS:
                case IS_ANDROID:
                case IS_IOS:
                    Object accessToken = request.getParameter(TokenName);
                    CheckAccessTokenClass checkAccessTokenClass = new CheckAccessTokenClass();
                    returnModel.isok = checkAccessTokenClass.isAccessTokenOk(accessToken);
                    //检查accessToken
                    break;
                case IS_BS:
                    //检查cookie
                    Cookie[] cookies = request.getCookies();
                    CheckCookieClass checkCookieClass = new CheckCookieClass();
                    returnModel.isok = checkCookieClass.isCookieOk(cookies);
                    break;
                case IS_WEIXIN_PUBLIC:
                    break;
                case IS_WEIXIN_SMALLPROGRAME:
                    break;
                case IS_LOCALREMOTE:
                    break;
                default:
                    break;
            }
        }


        if (!returnModel.isok) {
            ctx.setSendZuulResponse(returnModel.isok);
            ctx.setResponseStatusCode(nStatusCode);
            if (myBody != null) {
                ctx.setResponseBody(myBody);
            }
        }
        return null;
    }
}
