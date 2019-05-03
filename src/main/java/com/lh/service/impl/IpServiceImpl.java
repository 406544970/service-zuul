package com.lh.service.impl;

import com.lh.dao.IpBaseMapper;
import com.lh.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 梁昊
 * @date 2019/5/3
 * @function 从mysql中读取数据
 * @editLog
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class IpServiceImpl implements IpService {
    @Autowired
    IpBaseMapper ipBaseMapper;

    /**
     * @return 得到域名列表，解决跨域问题
     */
    @Override
    public List<String> getDomainList() {
        return ipBaseMapper.getIpList("DO");
    }

    /**
     * @return 得到白名单，对指定方法需要验证白名单
     */
    @Override
    public List<String> getWhileList() {
        return ipBaseMapper.getIpList("WH");
    }

    /**
     * @return 得到黑名单
     */
    @Override
    public List<String> getBlackList() {
        return ipBaseMapper.getIpList("BL");
    }

}
