package com.lh.myclass;

import java.util.List;

/**
 * @author 梁昊
 * @date 2019/5/3
 * @function 域名列表
 * @editLog
 */
public class MyDomainList extends MyNameList {
    public MyDomainList() {
        super(true);
    }

    @Override
    public int getListCount() {
        return super.getListCount();
    }

    @Override
    public boolean isAllow(String ip) {
        return super.isAllow(ip);
    }

    @Override
    public void setNameList(List<String> nameList) {
        super.setNameList(nameList);
    }

    @Override
    public List<String> getNameList() {
        return super.getNameList();
    }
}
