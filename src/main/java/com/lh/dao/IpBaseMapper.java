package com.lh.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 梁昊
 * @create 2019-05-03 13:42
 * @function
 * @editLog
 */
@Mapper
public interface IpBaseMapper {
    /**
     * 得到域名、白名单、黑名单
     *
     * @param selectSign 查询标识
     * @return
     */
    List<String> getIpList(@Param("selectSign") String selectSign);
}
