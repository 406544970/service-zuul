package com.lh.servicezuul.apicontrol;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.netflix.discovery.DiscoveryManager.getInstance;

@RestController
public class MyOperatorControl {

    @GetMapping(value = "/downLine")
    public void downLine(){
        getInstance().shutdownComponent();
    }
}