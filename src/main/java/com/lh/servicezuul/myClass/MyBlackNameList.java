package com.lh.servicezuul.myClass;

/**
 * @author 梁昊
 * @date 2018/10/20
 * @function 以客户端IP为黑名单，过滤请求
 * @editLog
 */
public final class MyBlackNameList extends MyNameList {
    public MyBlackNameList() {
        super(false);
    }

    public boolean isAllow(String ip){
        return !super.isCheckName(ip);
    }
}
