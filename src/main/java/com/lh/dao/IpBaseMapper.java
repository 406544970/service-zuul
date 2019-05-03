package com.lh.dao;

import com.lh.model.IpBase;
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
     * @return 返回列表
     */
//    List<IpBase> getIpList(@Param("selectSign") String selectSign);
    List<String> getIpList(@Param("selectSign") String selectSign);
//    List<IpBase> getIpList();
}
