package com.lh.myclass;

import com.lh.model.TokenClass;

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


    public boolean isAccessTokenOk(TokenClass tokenClass) {
//        boolean isOk = false;
//        ResultVO resultVO = new ResultVO();
//        String useId = tokenClass.getUseId();
//        String accessToken = tokenClass.getAccessToken();
//        String useType = tokenClass.getUseType();
//        if ((useId != null) && (accessToken != null) && (useType != null)) {
//            switch (useType) {
//                case "Manager":
//                    resultVO = identityService.checkManagerIdentity(useId, accessToken);
//                    break;
//                case "PcTourist":
//                    resultVO = identityService.checkPcTouristsIdentity(useId, accessToken);
//                    break;
//                case "AppTourist":
//                    resultVO = identityService.checkAppTouristsIdentity(useId, accessToken);
//                    break;
//                case "WeixinTourist":
//                    resultVO = identityService.checkWeiXinTouristsIdentity(useId, accessToken);
//                    break;
//                default:
//                    break;
//            }
//            isOk = resultVO.getCode().equals("200") ? true : false;
//        }
        return true;
    }
}