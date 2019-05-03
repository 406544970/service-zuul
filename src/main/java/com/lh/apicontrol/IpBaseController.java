package com.lh.apicontrol;

import com.lh.service.IpService;
import com.lh.unit.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 梁昊
 * @create 2019-05-03 13:53
 * @function
 * @editLog
 */
@EnableEurekaClient
@RestController
@RequestMapping("/api")
@Api(value = "得到域名、白名单、黑名单", description = "得到域名、白名单、黑名单粒子层")
public class IpBaseController {

    @Autowired
    IpService ipService;

    @Autowired
    RedisOperator redisOperator;

    /**
     * 得到域名
     *
     * @return 返回列表
     */
    @ApiOperation(value = "得到域名", notes = "返回列表")
    @PostMapping("/getDomainList")
    public List<String> getDomainList(){
        List<String> domainList = redisOperator.getDomainList();
        if (domainList == null || domainList.isEmpty()) {
            return ipService.getDomainList();
        }
        else
            return domainList;
    }

    /**
     * 白名单
     *
     * @return 返回列表
     */
    @ApiOperation(value = "白名单", notes = "返回列表")
    @PostMapping("/getWhileList")
    public List<String> getWhileList(){
        return ipService.getWhileList();
    }

    /**
     * 黑名单
     *
     * @return 返回列表
     */
    @ApiOperation(value = "黑名单", notes = "返回列表")
    @PostMapping("/getBlackList")
    public List<String> getBlackList(){
        return ipService.getBlackList();
    }

    @PostMapping("/insertDomain")
    public int insertDomain(){
        return redisOperator.insertDomain(ipService.getDomainList());
    }
}
