package com.lh.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public List<String> getDomainList(){
        return getFormRedisSet("domainSet");
    }

    public int insertDomain(List<String> domainList){
        return insertIntoRedisSet("domainSet",domainList);
    }

    public List<String> getBlackList(){
        return getFormRedisSet("blackSet");
    }

    public int insertBlack(List<String> blackList){
        return insertIntoRedisSet("blackSet",blackList);
    }

    public List<String> getWhiteList(){
        return getFormRedisSet("whiteSet");
    }

    public int insertWhite(List<String> whiteList){
        return insertIntoRedisSet("whiteSet",whiteList);
    }

    /**
     * 增加或修改指定键值
     *
     * @param key   键
     * @param value 值
     */
    public void saveString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getString(String key) {
        if (key != null)
            return stringRedisTemplate.opsForValue().get(key);
        else
            return null;
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

}
