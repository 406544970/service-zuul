package com.lh.service.impl;

import com.lh.dao.MyTokenMapper;
import com.lh.model.InPutParam.MyTokenSelectInParam;
import com.lh.model.MyTokenModel;
import com.lh.service.MyTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：flyman
 * @create 2019-10-15 11:43
 * @function
 * @editLog
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class MyTokenServiceImpl implements MyTokenService {
    @Autowired
    MyTokenMapper myTokenMapper;
    /**
     * 得到Token
     *
     * @param myTokenSelectInParam 得到Token
     * @return Token时间
     */
    @Override
    public MyTokenModel selectToken(MyTokenSelectInParam myTokenSelectInParam) {
        return myTokenMapper.selectToken(myTokenSelectInParam);
    }

    /**
     * 增加Token
     *
     * @param myTokenSelectInParam com.lh.model.InPutParam.MyTokenSelectInParam
     * @return Token时间
     */
    @Override
    public int insertToken (MyTokenSelectInParam myTokenSelectInParam) {
        return myTokenMapper.insertToken(myTokenSelectInParam);
    }

    /**
     * 增加Token
     *
     * @param myTokenSelectInParam com.lh.model.InPutParam.MyTokenSelectInParam
     * @return Token时间
     */
    @Override
    public int insertTokenBeforeCheck(MyTokenSelectInParam myTokenSelectInParam) {
        return myTokenMapper.insertTokenBeforeCheck(myTokenSelectInParam);
    }

    /**
     * 更新Token
     *
     * @param myTokenSelectInParam com.lh.model.InPutParam.MyTokenSelectInParam
     * @return 影响条数
     */
    @Override
    public int updateToken (MyTokenSelectInParam myTokenSelectInParam) {
        return myTokenMapper.updateToken(myTokenSelectInParam);
    }

    /**
     * 删除Token
     *
     * @param myTokenSelectInParam com.lh.model.InPutParam.MyTokenSelectInParam
     * @return 影响条数
     */
    @Override
    public int deleteToken (MyTokenSelectInParam myTokenSelectInParam) {
        return myTokenMapper.deleteToken(myTokenSelectInParam);
    }

    /**
     * 删除历史Token
     *
     * @return 影响条数
     */
    @Override
    public int deleteHistoryToken () {
        return myTokenMapper.deleteHistoryToken();
    }

}
