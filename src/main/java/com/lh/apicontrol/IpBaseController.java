package com.lh.apicontrol;

import com.lh.service.IpService;
import io.swagger.annotations.Api;
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


    @PostMapping("/getDomainList")
    public List<String> getDomainList(){
        return ipService.getDomainList("DO");
    }

//    @PostMapping("/getDomainList")
//    public List<String> getDomainList(){
//        return ipService.getDomainList("DO");
//    }
//    @PostMapping("/getDomainList")
//    public List<String> getDomainList(){
//        return ipService.getDomainList("DO");
//    }

}
