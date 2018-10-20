package com.lh.servicezuul.myClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lianghao
 * @date 2018/10/20
 * @function 名单抽象类
 * @editLog
 */
public abstract class MyNameList {
    public MyNameList(boolean isWhite) {
        super();
        this.iniClass(isWhite);
    }

    private List<String> nameList;
    private boolean isWhite;

    private void iniClass(boolean isWhite) {
        this.isWhite = isWhite;
        nameList = new ArrayList();
        nameList = this.getNameList();
    }

    private List<String> getNameList() {
        List<String> list = new ArrayList<>();
        if (this.isWhite) {
            list.add("192.168.2.156");
            list.add("192.168.2.157");
            list.add("192.168.2.158");
            list.add("192.168.2.159");
        } else {
            list.add("192.168.1.156");
            list.add("192.168.1.157");
            list.add("192.168.1.158");
            list.add("192.168.1.159");
        }
        return list;
    }

    /**
     * @param ip
     * @return
     */
    protected boolean isCheckName(String ip) {
        if (this.nameList.isEmpty())
            return false;
        else {
            return this.nameList.indexOf(ip) > -1 ? true : false;
        }
    }

}
