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
//    private List<String> getNameList() {
//        List<String> list = new ArrayList<>();
//        if (this.isWhite) {
//            list.add("192.168.2.156");
//            list.add("192.168.2.157");
//            list.add("192.168.2.158");
//            list.add("192.168.2.159");
//        } else {
//            list.add("192.168.1.103");
//            list.add("192.168.1.157");
//            list.add("192.168.1.158");
//            list.add("192.168.1.159");
//        }
//        return list;
//    }

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
