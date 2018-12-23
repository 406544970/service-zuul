package com.lh.servicezuul.myclass;

import com.lh.servicezuul.model.TokenClass;

/**
 * @author 梁昊
 * @date 2018/10/23
 * @function
 * @editLog
 */
public class CheckAccessTokenClass {
    public CheckAccessTokenClass() {
        super();
    }

    public boolean isAccessTokenOk(TokenClass tokenClass) {
        String useId = tokenClass.getUseId();
        String accessToken = tokenClass.getAccessToken();
        String useType = tokenClass.getUseType();
        if ((useId != null) && (accessToken != null) && (useType != null)) {
            return true;
        } else {
            return false;
        }
    }
}
