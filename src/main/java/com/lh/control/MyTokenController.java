package com.lh.control;

import com.lh.model.InPutParam.MyTokenSelectInParam;
import com.lh.service.MyTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lh.model.ResultVO;
import lh.units.ResultStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author ：flyman，后端工程师：flyman2，前端工程师：flyman3
 * @create 2019-10-15 14:09
 * @function
 * @editLog
 */
@RestController
@RequestMapping("/myTokenController")
@Api(value = "Token of MySql", description = "Token层")
public class MyTokenController {
    @Value("${token.valid.hours}")
    private int hours;
    @Autowired
    MyTokenService myTokenService;

    /**
     * 增加Token，方法ID：IN201910151356327349098631B9EF1
     *
     * @param useId 用户ID
     * @param useType 用户类型
     * @param clientType 客户端类型
     * @return Token时间
     */
    @ApiOperation(value = "增加Token", notes = "Token时间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "useId", value = "用户ID", dataType = "String", required = true)
            , @ApiImplicitParam(name = "useType", value = "用户类型", dataType = "String", required = true)
            , @ApiImplicitParam(name = "clientType", value = "客户端类型", dataType = "String", required = true)
    })
    @PostMapping("/insertToken")
    public ResultVO insertToken(@RequestParam(value = "useId") String useId
            , @RequestParam(value = "useType") String useType
            , @RequestParam(value = "clientType") String clientType) {
        useId = useId.trim();
        useType = useType.trim();
        clientType = clientType.trim();

        MyTokenSelectInParam myTokenSelectInParam = new MyTokenSelectInParam();
        myTokenSelectInParam.setUseId(useId);
        myTokenSelectInParam.setUseType(useType);
        myTokenSelectInParam.setClientType(clientType);
        int repetitionCount = myTokenService.insertTokenBeforeCheck(myTokenSelectInParam);
        if (repetitionCount > 0){
            repetitionCount = myTokenService.updateToken(myTokenSelectInParam);
            if (repetitionCount > 0) {
                return ResultStruct.success(myTokenSelectInParam);
            }
        }
        UUID uuid = UUID.randomUUID();
        String accessToken = uuid.toString().replace("-","");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.HOUR, hours);// num为增加的天数，可以改变的
        Date tokenEffective = ca.getTime();
        myTokenSelectInParam.setAccessToken(accessToken);
        myTokenSelectInParam.setTokenEffective(tokenEffective);
        int resultCount = myTokenService.insertToken(myTokenSelectInParam);
        if (resultCount > 0)
            return ResultStruct.success(myTokenSelectInParam);
        else
            return ResultStruct.error("增加失败", ResultVO.class,null);
    }
    /**
     * 更新Token，方法ID：UP201910151414399381CE1B2732C68
     *
     * @param useId
    用户ID, 字段
     * @param useType 用户类型, 字段
     * @param clientType 客户端类型, 字段
     * @return 影响条数
     */
    @ApiOperation(value = "更新Token", notes = "影响条数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "useId", value = "用户ID", dataType = "String", required = true)
            , @ApiImplicitParam(name = "useType", value = "用户类型", dataType = "String", required = true)
            , @ApiImplicitParam(name = "clientType", value = "客户端类型", dataType = "String", required = true)
    })
    @PostMapping("/updateToken")
    public ResultVO updateToken(@RequestParam(value = "useId") String useId
            , @RequestParam(value = "useType") String useType
            , @RequestParam(value = "clientType") String clientType) {
        useId = useId.trim();
        useType = useType.trim();
        clientType = clientType.trim();

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.HOUR, hours);// num为增加的天数，可以改变的
        Date tokenEffective = ca.getTime();

        MyTokenSelectInParam myTokenSelectInParam = new MyTokenSelectInParam();
        myTokenSelectInParam.setUseId(useId);
        myTokenSelectInParam.setUseType(useType);
        myTokenSelectInParam.setClientType(clientType);
        myTokenSelectInParam.setTokenEffective(tokenEffective);
        int updateCount = myTokenService.updateToken(myTokenSelectInParam);
        if (updateCount > 0)
            return ResultStruct.success(myTokenSelectInParam);
        else
            return ResultStruct.error("增加失败", ResultVO.class,null);
    }
    /**
     * 删除Token，方法ID：DE201910151528051744F6595D8687C
     *
     * @param useId 用户ID
     * @param useType 用户类型
     * @param clientType 客户端类型
     * @return 影响条数
     */
    @ApiOperation(value = "删除Token", notes = "影响条数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "useId", value = "用户ID", dataType = "String", required = true)
            , @ApiImplicitParam(name = "useType", value = "用户类型", dataType = "String", required = true)
            , @ApiImplicitParam(name = "clientType", value = "客户端类型", dataType = "String", required = true)
    })
    @PostMapping("/deleteToken")
    public ResultVO deleteToken(@RequestParam(value = "useId") String useId
            , @RequestParam(value = "useType") String useType
            , @RequestParam(value = "clientType") String clientType) {
        useId = useId.trim();
        useType = useType.trim();
        clientType = clientType.trim();

        MyTokenSelectInParam myTokenSelectInParam = new MyTokenSelectInParam();
        myTokenSelectInParam.setUseId(useId);
        myTokenSelectInParam.setUseType(useType);
        myTokenSelectInParam.setClientType(clientType);
        int deleteCount = myTokenService.deleteToken(myTokenSelectInParam);
        if (deleteCount > 0)
            return ResultStruct.success(deleteCount);
        else
            return ResultStruct.error("删除失败", ResultVO.class, int.class);
    }
    /**
     * 删除历史Token，方法ID：DE20191015153154242A74F7597D752
     *
     * @return 影响条数
     */
    @ApiOperation(value = "删除历史Token", notes = "影响条数")
    @ApiImplicitParams({
    })
    @PostMapping("/deleteHistoryToken")
    public ResultVO deleteHistoryToken() {
        int deleteCount = myTokenService.deleteHistoryToken();
        if (deleteCount > 0)
            return ResultStruct.success(deleteCount);
        else
            return ResultStruct.error("删除失败", ResultVO.class, int.class);
    }
}
