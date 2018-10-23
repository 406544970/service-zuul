package com.lh.servicezuul.filter;

import com.lh.servicezuul.model.ReturnModel;
import com.lh.servicezuul.myClass.MyBlackNameList;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 梁昊
 * @date 2018/10/23
 * @function
 * @editLog
 */
public class BlackNameFilter extends ZuulFilter {

    public BlackNameFilter() {
        super();
        myBlackNameList = new MyBlackNameList();
        ctx = RequestContext.getCurrentContext();
   }

    private MyBlackNameList myBlackNameList;
    private RequestContext ctx;

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = ctx.getRequest();
        String useIp = request.getRemoteAddr();
        return myBlackNameList.isAllow(useIp);
    }

    @Override
    public Object run() {
        ReturnModel returnModel = new ReturnModel();
        returnModel.message = String.format("%s:您的IP已进入黑名单，不允许访问！", returnModel.message);
        ctx.setSendZuulResponse(returnModel.isok);
        ctx.setResponseStatusCode(401);
        return null;
    }
}
