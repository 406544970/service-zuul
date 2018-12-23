package com.lh.servicezuul.myclass;

import com.lh.servicezuul.model.TokenClass;
import com.lh.servicezuul.service.IdentityService;
import com.lishunyi.result.VO.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 梁昊
 * @date 2018/10/23
 * @function
 * @editLog
 */
public class CheckAccessTokenClass {
    public CheckAccessTokenClass() {
        super();
    }

    @Autowired
    private IdentityService identityService;

    public boolean isAccessTokenOk(TokenClass tokenClass) {
        boolean isOk = false;
        ResultVO resultVO = new ResultVO();
        String useId = tokenClass.getUseId();
        String accessToken = tokenClass.getAccessToken();
        String useType = tokenClass.getUseType();
        if ((useId != null) && (accessToken != null) && (useType != null)) {
            switch (useType) {
                case "Manager":
                    resultVO = identityService.checkManagerIdentity(useId, accessToken);
                    break;
                case "pctourist":
                    resultVO = identityService.checkPcTouristsIdentity(useId, accessToken);
                    break;
                case "apptourist":
                    resultVO = identityService.checkAppTouristsIdentity(useId, accessToken);
                    break;
                case "weixintourist":
                    resultVO = identityService.checkWeiXinTouristsIdentity(useId, accessToken);
                    break;
                default:
                    break;
            }
            isOk = resultVO.getCode().equals("200") ? true : false;
        }
        return isOk;
    }
}
