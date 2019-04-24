package com.lh.servicezuul.model;

/**
 * @author 梁昊
 * @date 2018/12/23
 * @function
 * @editLog
 */
public class ResultVO {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public ResultVO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultVO setMessage(String message) {
        this.message = message;
        return this;
    }
}
