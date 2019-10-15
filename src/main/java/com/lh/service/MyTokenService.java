package com.lh.service;

import com.lh.model.InPutParam.MyTokenSelectInParam;

/**
 * @author ：flyman
 * @create 2019-10-15 11:43
 * @function
 * @editLog
 */
public interface MyTokenService {
    /**
     * 得到Token
     *
     * @param myTokenSelectInParam 得到Token
     * @return Token时间
     */
    String selectToken(MyTokenSelectInParam myTokenSelectInParam);
    /**
     * 增加Token
     *
     * @param myTokenSelectInParam com.lh.model.InPutParam.MyTokenSelectInParam
     * @return Token时间
     */
    int insertToken(MyTokenSelectInParam myTokenSelectInParam);

    /**
     * 增加Token
     *
     * @param myTokenSelectInParam com.lh.model.InPutParam.MyTokenSelectInParam
     * @return Token时间
     */
    int insertTokenBeforeCheck(MyTokenSelectInParam myTokenSelectInParam);
    /**
     * 更新Token
     *
     * @param myTokenSelectInParam com.lh.model.InPutParam.MyTokenSelectInParam
     * @return 影响条数
     */
    int updateToken(MyTokenSelectInParam myTokenSelectInParam);
    /**
     * 删除Token
     *
     * @param myTokenSelectInParam com.lh.model.InPutParam.MyTokenSelectInParam
     * @return 影响条数
     */
    int deleteToken(MyTokenSelectInParam myTokenSelectInParam);
    /**
     * 删除历史Token
     *
     * @return 影响条数
     */
    int deleteHistoryToken();

}
