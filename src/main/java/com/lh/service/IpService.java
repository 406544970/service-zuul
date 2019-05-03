package com.lh.service;

import java.util.List;

/**
 * @author 梁昊
 * @date 2019/5/3
 * @function
 * @editLog
 */
public interface IpService {
    List<String> getDomainList();
    List<String> getWhileList();
    List<String> getBlackList();

}
