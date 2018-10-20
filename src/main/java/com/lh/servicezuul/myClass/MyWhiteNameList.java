package com.lh.servicezuul.myClass;



/**
 * @author lianghao
 * @date 2018/10/20
 * @function 以客户端IP为白名单，过滤请求
 * @editLog
 */
public final class MyWhiteNameList extends MyNameList {
    public MyWhiteNameList() {
        super(true);
    }

    public boolean isAllow(String ip){
        return super.isCheckName(ip);
    }

}
