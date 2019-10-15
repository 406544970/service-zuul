package com.lh.model;

import java.util.Date;

/**
 * @author ：flyman
 * @create 2019-10-15 11:38
 * @function
 * @editLog
 */
public class MyTokenModel {
    /**
     * token
     */
    private String accessToken;
    /**
     * token有效期
     */
    private Date tokenEffective;

    public String getAccessToken(){
        return accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public Date getTokenEffective(){
        return tokenEffective;
    }

    public void setTokenEffective(Date tokenEffective){
        this.tokenEffective = tokenEffective;
    }
}
