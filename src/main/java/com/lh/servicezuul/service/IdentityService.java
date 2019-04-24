package com.lh.servicezuul.service;


import com.lh.servicezuul.model.ResultVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient(value = "identity-mucon", path = "identity-mucon")
public interface IdentityService {

    /**
     * 读取PC端游客redis信息
     * @param keyName userId
     * @return
     */
    @GetMapping(value = "/checkPcTouristsIdentity")
    ResultVO checkPcTouristsIdentity(@RequestParam(value = "keyName", defaultValue = "")String keyName,
                                     @RequestParam(value = "value", defaultValue = "")String value);

    /**
     * 读取后台用户redis信息
     * @param keyName userId
     * @return
     */
    @GetMapping(value = "/checkManagerIdentity")
    ResultVO checkManagerIdentity(@RequestParam(value = "keyName", defaultValue = "")String keyName,
                                  @RequestParam(value = "value", defaultValue = "")String value);

    /**
     * 读取APP端游客redis信息
     * @param keyName userId
     * @return
     */
    @GetMapping(value = "/checkAppTouristsIdentity")
    ResultVO checkAppTouristsIdentity(@RequestParam(value = "keyName", defaultValue = "")String keyName,
                                             @RequestParam(value = "value", defaultValue = "")String value);

    /**
     * 读取微信端游客redis信息
     * @param keyName userId
     * @return
     */
    @GetMapping(value = "/checkWeiXinTouristsIdentity")
    ResultVO checkWeiXinTouristsIdentity(@RequestParam(value = "keyName", defaultValue = "")String keyName,
                                                @RequestParam(value = "value", defaultValue = "")String value);
}
