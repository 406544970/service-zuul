package com.lh.model;

/**
 * @program: service-zuul
 * @description: Token类型
 * @author: 梁昊
 * @create: 2018-12-23 11:23
 * @lastUpdateTime:
 **/
public class TokenClass {
    private String useId;
    private String useType;

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }
}
