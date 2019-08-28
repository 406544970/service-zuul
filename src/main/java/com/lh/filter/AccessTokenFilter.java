package com.lh.filter;

import com.google.gson.Gson;
import com.lh.model.ReturnModel;
import com.lh.model.TokenClass;
import com.lh.myclass.*;
import com.lh.myenum.EnumClass;
import com.lh.service.IpService;
import com.lh.unit.CheckAccessTokenClass;
import com.lh.unit.RedisOperator;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import lh.toolclass.LhJsonClass;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORWARD_TO_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

/**
 * @author 梁昊
 * @date 2018/10/20
 * @function 身份认证过滤器
 * @editLog 过滤IP黑名单和白名单，判断是BS还是CS，判断是否需要身份验证
 */
public class AccessTokenFilter extends ZuulFilter {
    @Autowired
    Gson gson;
    @Autowired
    IpService ipService;
    @Autowired
    CheckAccessTokenClass checkAccessTokenClass;
    @Autowired
    RedisOperator redisOperator;

    public AccessTokenFilter() {
        super();
        iniFilter();
    }

    private final String TokenName = "accessToken";
    private final String UseId = "useId";
    private final String UseType = "useType";
    private final String ClientType = "clientType";

    private MyWhiteNameList myWhiteNameList;
    private MyBlackNameList myBlackNameList;
    private MyDomainList myDomainList;

    public void iniFilter() {
        myBlackNameList = new MyBlackNameList();
        myDomainList = new MyDomainList();
        myWhiteNameList = new MyWhiteNameList();
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return !ctx.containsKey(FORWARD_TO_KEY)
                && !ctx.containsKey(SERVICE_ID_KEY);
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
        Logger logger = Logger.getLogger("chapter07");
        ReturnModel returnModel = new ReturnModel();
        returnModel.message = String.format("%s:无合法通行证，不允许访问！", returnModel.message);
        int nStatusCode = 401;
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

        String useIp = request.getRemoteAddr();
        String[] whiteList = new String[2];
        whiteList[0] = "http://localhost:63342";
        whiteList[1] = "http://www.lh.com";
        String myOrigin = request.getHeader("origin");
        logger.info(String.format("Origin:%s", myOrigin));
        boolean isValid = false;
        for (String ip : whiteList) {
            if (myOrigin != null && myOrigin.equals(ip)) {
                isValid = true;
                break;
            }
        }
        response.setHeader("Access-Control-Allow-Origin", isValid ? myOrigin : "null");
        response.setHeader("Access-Control-Allow-Method", "OPTIONS, TRACE, GET, HEAD, POST, PUT");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json;text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (myBlackNameList.isAllow(useIp)) {
            returnModel.isok = true;
            myWhiteNameList = new MyWhiteNameList();
            String getRequestURI = request.getRequestURI();
            IsCheckWhitePath isCheckWhitePath = new IsCheckWhitePath();

            if (isCheckWhitePath.isCheckWhite(getRequestURI)) {
                isCheck = myWhiteNameList.isAllow(useIp);
                if (!isCheck) {
                    returnModel.isok = false;
                    returnModel.message = String.format("%s:您的IP(%s)未在白名单中，不允许访问！", returnModel.message, useIp);
                }
            } else {
                isCheck = true;
            }

            if (isCheck) {
                returnModel = new ReturnModel();
                OperateTypeClass operateTypeClass = new OperateTypeClass();
                EnumClass.CheckIdentityEnum checkIdentityEnum = operateTypeClass.GetOperateType(getRequestURI);
                switch (checkIdentityEnum) {
                    case IS_CS:
                    case IS_ANDROID:
                    case IS_IOS: {
                        Object accessToken = request.getParameter(TokenName);
                        Object useId = request.getParameter(UseId);
                        Object useType = request.getParameter(UseType);
                        Object clientType = request.getParameter(ClientType);
                        if ((accessToken != null) && (useId != null) && (useType != null)) {
                            TokenClass tokenClass = new TokenClass();
                            tokenClass.setUseId(useId.toString());
                            tokenClass.setAccessToken(accessToken.toString());
                            tokenClass.setUseType(useType.toString());
                            tokenClass.setClientType(clientType.toString());
                            returnModel.isok = checkAccessTokenClass.isAccessTokenOk(tokenClass);
                            if (returnModel.isok) {
                                returnModel.setSuccess();
                            }
                        }
                    }
                    //检查accessToken
                    break;
                    case IS_BS: {
                        Cookie[] cookies = request.getCookies();
                        returnModel.isok = cookies == null ? false : true;
                        if (returnModel.isok) {
                            String useId = null;
                            String accessToken = null;
                            String useType = null;
                            String clientType = null;
                            for (Cookie row : cookies
                                    ) {
                                if (row.getName().equals(TokenName)) {
                                    accessToken = row.getValue();
                                }
                                if (row.getName().equals(UseId)) {
                                    useId = row.getValue();
                                }
                                if (row.getName().equals(UseType)) {
                                    useType = row.getValue();
                                }
                                if (row.getName().equals(ClientType)) {
                                    clientType = row.getValue();
                                }
                            }
                            if ((accessToken != null) && (useId != null) && (useType != null) && (clientType != null)) {
                                TokenClass tokenClass = new TokenClass();
                                tokenClass.setUseId(useId);
                                tokenClass.setAccessToken(accessToken);
                                tokenClass.setUseType(useType);
                                tokenClass.setClientType(clientType);
                                returnModel.isok = checkAccessTokenClass.isAccessTokenOk(tokenClass);
                                if (returnModel.isok) {
                                    returnModel.setSuccess();
                                    Map<String, String[]> parameterMap = request.getParameterMap();
                                    logger.info(gson.toJson(parameterMap));

                                    if (!parameterMap.containsKey("useId")) {
                                        parameterMap.put("useId", new String[]{useId});
                                    }
                                    if (!parameterMap.containsKey("useType")) {
                                        parameterMap.put("useType", new String[]{useType});
                                    }
                                    if (!parameterMap.containsKey("clientType")) {
                                        parameterMap.put("clientType", new String[]{clientType});
                                    }
                                    String newBody = parameterMap.toString();
                                    final byte[] reqBodyBytes = newBody.getBytes();
                                    ctx.setRequest(new HttpServletRequestWrapper(request) {
                                        @Override
                                        public ServletInputStream getInputStream() throws IOException {
                                            return new ServletInputStreamWrapper(reqBodyBytes);
                                        }

                                        @Override
                                        public int getContentLength() {
                                            return reqBodyBytes.length;
                                        }

                                        @Override
                                        public long getContentLengthLong() {
                                            return reqBodyBytes.length;
                                        }
                                    });
                                }
                            }
                        }
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
            returnModel.message = String.format("%s:您的IP(%s)已进入黑名单，不允许访问！", returnModel.message, useIp);
        }

        if (returnModel.isok) {
//            if (!useIp.equals("192.168.1.123")) {
//                RibbonFilterContextHolder.getCurrentContext().add("version", "1");
//            } else {
//                RibbonFilterContextHolder.getCurrentContext().add("version", "2");
//            }
        } else {
            String resultBody = "<div class=\"top\">";
            resultBody += "<form name=\"userLoginActionForm\" id=\"userLoginActionForm\" method=\"POST\" action=\"\" target=\"_parent\">";
            resultBody += "<input type=\"text\" autofocus=\"true\" id=\"username\" name=\"username\" maxlength=\"20\" placeholder=\"帐号\"";
            resultBody += "onkeydown=\"UserEnter(event)\" onfocus=\"hideVcode()\"/>";
            resultBody += "<input type=\"password\" id=\"userpwd\" name=\"userpwd\" maxlength=\"20\" placeholder=\" 密码\" ";
            resultBody += "onkeydown=\"PassEnter(event)\"/>";
            resultBody += "<input type=\"text\" id=\"validatecode\"  placeholder=\" 验证码\"";
            resultBody += "onkeydown=\"ValidateCodeEnter(event)\">";
            resultBody += "<img id=\"vcodesrc\" onclick=\"updateValidateImage()\" src=\"\"  style=\"display: none\">";
            resultBody += "<input type=\"button\" value=\"\" id=\"login_bt\" name=\"login_bt\"/>";
            resultBody += "<a href=\"\" class=\"forget\">忘记密码</a>";
            resultBody += "</form>";
            resultBody += "</div>";
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(nStatusCode);
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            ctx.setResponseBody(resultBody);
        }
        return null;
    }

}
