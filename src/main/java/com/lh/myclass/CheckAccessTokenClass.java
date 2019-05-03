package com.lh.myclass;

import com.lh.model.TokenClass;
import com.lh.unit.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 梁昊
 * @date 2018/10/23
 * @function
 * @editLog
 */
public class CheckAccessTokenClass {
    @Autowired
    RedisOperator redisOperator;

    public CheckAccessTokenClass() {
        super();
    }


    public boolean isAccessTokenOk(TokenClass tokenClass) {
        String useId = tokenClass.getUseId();
        String accessToken = tokenClass.getAccessToken();
        String useType = tokenClass.getUseType();
        String clientType = tokenClass.getClientType();
        if ((useId != null) && (accessToken != null) && (useType != null) && (clientType != null)) {
            return checkUseToken(clientType, useId, useType, accessToken);
        }
        return false;
    }

    /**
     * 从Redis中验证token
     *
     * @param clientType 客户端类型 BS或CS等
     * @param useId
     * @param useType
     * @param token
     * @return
     */
    private boolean checkUseToken(String clientType, String useId, String useType, String token) {
        String keyName = String.format("%s%s:%s", clientType, useId, useType);
        String accessToken = redisOperator.getString(keyName);
        if (accessToken != null) {
            return token.equals(accessToken);
        } else
            return false;
    }

}
