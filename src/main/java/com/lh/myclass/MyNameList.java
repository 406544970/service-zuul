package com.lh.myclass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁昊
 * @date 2018/10/20
 * @function 名单抽象类
 * @editLog
 */
public abstract class MyNameList {
    public MyNameList(boolean isWhite) {
        super();
        this.iniClass(isWhite);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        nameList.clear();
        nameList = null;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public List<String> getNameList() {
        return nameList;
    }

    private List<String> nameList;
    private boolean isWhite;

    private void iniClass(boolean isWhite) {
        this.isWhite = isWhite;
        nameList = new ArrayList();
//        nameList = this.getNameList();
    }
    public int getListCount(){
        if (nameList == null) {
            return 0;
        }
        return this.nameList.size();
    }

    /**
     * @param ip 客户端IP
     * @return 客户端IP是否存在于名单中
     */
    protected boolean isAllow(String ip) {
        if (this.nameList.isEmpty())
            return false;
        else {
            return this.nameList.indexOf(ip) > -1 ? true : false;
        }
    }

}
