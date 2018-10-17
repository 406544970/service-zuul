package com.lh.servicezuul.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.netflix.discovery.DiscoveryManager.getInstance;

@RestController
public class myOperatorControl {

    @GetMapping(value = "/downLine")
    public void downLine(){
        getInstance().shutdownComponent();
    }
}
