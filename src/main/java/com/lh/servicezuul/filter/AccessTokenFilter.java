package com.lh.servicezuul.filter;

import com.google.gson.Gson;
import com.lh.servicezuul.model.ReturnModel;
import com.lh.servicezuul.myclass.*;
import com.lh.servicezuul.myenum.EnumClass;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

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
    private MyBlackNameList myBlackNameList;

    private void iniFilter() {
        myBlackNameList = new MyBlackNameList();
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
        return 0;
    }

    @Override
    public Object run() {
        boolean isCheck;
        int nStatusCode = 401;
        ReturnModel returnModel = new ReturnModel();
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String useIp = request.getRemoteAddr();
        Logger logger = Logger.getLogger("chapter07");
        logger.info("useIP:" + useIp);

        if (myBlackNameList.isAllow(useIp)) {
            returnModel.isok = true;
            myWhiteNameList = new MyWhiteNameList();
            String getRequestURI = request.getRequestURI();
            logger.info("useContextPath:" + getRequestURI);
            IsCheckWhitePath isCheckWhitePath = new IsCheckWhitePath();

            if (isCheckWhitePath.isCheckWhite(getRequestURI)) {
                isCheck = myWhiteNameList.isAllow(useIp);
                if (!isCheck) {
                    returnModel.isok = false;
                    returnModel.message = String.format("%s:您的IP(%s)未在白名单中，不允许访问！", returnModel.message,useIp);
                }
            } else {
                isCheck = true;
            }

            if (isCheck) {
                OperateTypeClass operateTypeClass = new OperateTypeClass();
                EnumClass.CheckIdentityEnum checkIdentityEnum = operateTypeClass.GetOperateType(getRequestURI);
                switch (checkIdentityEnum) {
                    case IS_CS:
                    case IS_ANDROID:
                    case IS_IOS:
                        Object accessToken = request.getParameter(TokenName);
                        CheckAccessTokenClass checkAccessTokenClass = new CheckAccessTokenClass();
                        returnModel.isok = checkAccessTokenClass.isAccessTokenOk(accessToken);
                        if (!returnModel.isok) {
                            returnModel.message = String.format("%s:无合法通行证，不允许访问！", returnModel.message);
                        }
                        //检查accessToken
                        break;
                    case IS_BS:
                        //检查cookie
                        Cookie[] cookies = request.getCookies();
                        CheckCookieClass checkCookieClass = new CheckCookieClass();
                        returnModel.isok = checkCookieClass.isCookieOk(cookies);
                        if (!returnModel.isok) {
                            returnModel.message = String.format("%s:无合法Cookie，不允许访问！", returnModel.message);
                        }
                        break;
                    case IS_WEIXIN_PUBLIC:
                        break;
                    case IS_WEIXIN_SMALLPROGRAME:
                        break;
                    case IS_LOCALREMOTE:
                        break;
                    case IS_NO:
                        returnModel.isok = true;
                        break;
                    default:
                        break;
                }
            }
        } else {
            returnModel.message = String.format("%s:您的IP(%s)已进入黑名单，不允许访问！", returnModel.message,useIp);
        }


        if (!returnModel.isok) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(nStatusCode);
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            Gson gson = new Gson();
            ctx.setResponseBody(gson.toJson(returnModel));
        }
        return null;
    }
}
