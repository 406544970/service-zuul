package com.lh.model.InPutParam;

import java.util.Date;

/**
 * @author ：flyman
 * @create 2019-10-15 11:38
 * @function
 * @editLog
 */
public class MyTokenSelectInParam {
    /**
     * 表inf_accessToken,字段名useId:用户ID
     */
    private String useId;
    /**
     * 表inf_accessToken,字段名useType:用户类型
     */
    private String useType;
    /**
     * 表inf_accessToken,字段名clientType:客户端类型
     */
    private String clientType;
    /**
     * token
     */
    private String accessToken;
    /**
     * token有效期
     */
    private Date tokenEffective;

    public String getUseId(){
        return useId;
    }

    public void setUseId(String useId){
        this.useId = useId;
    }

    public String getUseType(){
        return useType;
    }

    public void setUseType(String useType){
        this.useType = useType;
    }

    public String getClientType(){
        return clientType;
    }

    public void setClientType(String clientType){
        this.clientType = clientType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getTokenEffective() {
        return tokenEffective;
    }

    public void setTokenEffective(Date tokenEffective) {
        this.tokenEffective = tokenEffective;
    }
}
