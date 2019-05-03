package com.lh.service;

import java.util.List;

/**
 * @author 梁昊
 * @date 2019/5/3
 * @function
 * @editLog
 */
public interface IpService {
    List<String> getDomainList(String selectSign);
    List<String> getWhileList(String selectSign);
    List<String> getBlackList(String selectSign);

}
