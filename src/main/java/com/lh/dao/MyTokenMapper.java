package com.lh.dao;

import com.lh.model.InPutParam.MyTokenSelectInParam;
import com.lh.model.MyTokenModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：flyman
 * @create 2019-10-15 11:40
 * @function
 * @editLog
 */
@Mapper
public interface MyTokenMapper {
    /**
     * 得到Token
     *
     * @param myTokenSelectInParam 输入参数
     * @return Token时间
     */
    MyTokenModel selectToken(MyTokenSelectInParam myTokenSelectInParam);
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
