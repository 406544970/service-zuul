package com.lh.apicontrol;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.netflix.discovery.DiscoveryManager.getInstance;

@RestController
@Api(value = "得到实例端口号、手动下线", description = "梁昊规定的常规方法")
public class MyOperatorControl {
    @Value("${server.port}")
    String myPort;

    @ApiOperation(value = "手动下线", notes = "手动下线")
    @GetMapping(value = "/downLine")
    public void downLine(){
        getInstance().shutdownComponent();
    }

    @ApiOperation(value = "当前端口号", notes = "当前端口号")
    @PostMapping("/myport")
    public String myPort(){
        return "myPort: " + this.myPort;
    }
}
