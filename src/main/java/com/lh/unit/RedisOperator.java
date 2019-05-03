package com.lh.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 梁昊
 * @date 2019/5/3
 * @function 得到域名、白名单、黑名单
 * @editLog
 */
@Component
public class RedisOperator {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private int insertIntoRedisSet(String setName,List<String> nameList){
        if (nameList == null) {
            return 0;
        }
        SetOperations<String, String> set = stringRedisTemplate.opsForSet();
        for (String row: nameList
             ) {
            set.add(setName,row);
        }
        return nameList.size();
    }

    private List<String> getFormRedisSet(String setName){
        Set<String> members = stringRedisTemplate.opsForSet().members(setName);
        return new ArrayList(members);
    }

    public int insertDomain(List<String> domainList){
        return insertIntoRedisSet("domainSet",domainList);
    }

    public List<String> getDomainList(){
        return getFormRedisSet("domainSet");
    }
}
