package com.lh.unit;

import com.lh.model.InPutParam.MyTokenSelectInParam;
import com.lh.model.TokenClass;
import com.lh.service.MyTokenService;
import com.lh.unit.RedisOperator;
import lh.units.ResultStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 梁昊
 * @date 2018/10/23
 * @function
 * @editLog
 */
@Component
public class CheckAccessTokenClass {
    //    @Autowired
//    RedisOperator redisOperator;
    @Autowired
    MyTokenService myTokenService;


    public CheckAccessTokenClass() {
        super();
    }


    public boolean isAccessTokenOk(TokenClass tokenClass) {
        String useId = tokenClass.getUseId();
        String accessToken = tokenClass.getAccessToken();
        String useType = tokenClass.getUseType();
        String clientType = tokenClass.getClientType();
        if ((useId != null) && (accessToken != null) && (useType != null) && (clientType != null))
            return checkUseToken(clientType, useId, useType, accessToken);
        else
            return false;
    }

    /**
     * 从Redis中验证token
     *
     * @param clientType 客户端类型 BS或CS等
     * @param useId
     * @param useType
     * @param accessToken
     * @return
     */
    private boolean checkUseToken(String clientType, String useId, String useType, String accessToken) {
//        String keyName = String.format("%s%s:%s", clientType, useType, useId);
//        String nowAccessToken = redisOperator.getString(keyName);
//        if (nowAccessToken != null) {
//            return nowAccessToken.equals(accessToken);
//        } else
//            return false;
        MyTokenSelectInParam myTokenSelectInParam = new MyTokenSelectInParam();
        myTokenSelectInParam.setUseId(useId);
        myTokenSelectInParam.setUseType(useType);
        myTokenSelectInParam.setClientType(clientType);
        myTokenSelectInParam.setAccessToken(accessToken);
        String selectToken = myTokenService.selectToken(myTokenSelectInParam);
        if (selectToken == null)
            return false;
        else
            return true;
    }

}
