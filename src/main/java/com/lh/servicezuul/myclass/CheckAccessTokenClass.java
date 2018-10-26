package com.lh.servicezuul.myclass;

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
    public boolean isAccessTokenOk(Object AccessToken){
        if (AccessToken == null) {
            return false;
        }
        return true;
    }
}
