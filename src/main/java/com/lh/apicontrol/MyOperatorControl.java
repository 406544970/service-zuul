package com.lh.apicontrol;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.netflix.discovery.DiscoveryManager.getInstance;

@RestController
public class MyOperatorControl {
    @Value("${server.port}")
    String myPort;


    @GetMapping(value = "/downLine")
    public void downLine(){
        getInstance().shutdownComponent();
    }

    @PostMapping("/myport")
    public String myPort(){
        return "myPort: " + this.myPort;
    }
}
