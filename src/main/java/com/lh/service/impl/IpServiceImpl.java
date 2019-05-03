package com.lh.service.impl;

import com.lh.dao.IpBaseMapper;
import com.lh.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 梁昊
 * @date 2019/5/3
 * @function
 * @editLog
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class IpServiceImpl implements IpService {
    @Autowired
    IpBaseMapper ipBaseMapper;

    @Override
    public List<String> getDomainList(String selectSign) {
        return ipBaseMapper.getIpList("DO");
    }

    @Override
    public List<String> getWhileList(String selectSign) {
        return ipBaseMapper.getIpList("WH");
    }

    @Override
    public List<String> getBlackList(String selectSign) {
        return ipBaseMapper.getIpList("BL");
    }
}
