package com.lh.servicezuul.myClass;

import com.lh.servicezuul.myenum.OperateClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁昊
 * @date 2018/10/22
 * @function
 * @editLog
 */
public class OperateTypeClass {
    private List<String> list;

    public OperateTypeClass() {
        super();
        IniClass();
    }

    private void IniClass(){
        list = new ArrayList<>();
        for (OperateClass.CheckIdentityEnum row:OperateClass.CheckIdentityEnum.values()
             ) {
            list.add(row.toString());
        }
    }

    public OperateClass.CheckIdentityEnum GetOperateType(String _myPath){
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (_myPath.indexOf(list.get(i)) > -1){
                index = i;
            }
        }
        if (index > -1){
            return null;
        }else{
            return OperateClass.CheckIdentityEnum.valueOf(list.get(index));
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        list.clear();
    }
}
