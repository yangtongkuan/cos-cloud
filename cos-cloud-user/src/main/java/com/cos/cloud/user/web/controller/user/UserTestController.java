package com.cos.cloud.user.web.controller.user;

import com.cos.cloud.user.model.vo.user.UserInfoVO;
import com.cos.cloud.user.service.user.UserTestService;
import com.cos.cloud.common.tools.AjaxResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/25 21:58
 * @To change this template use File | Settings | File Templates.
 */
@RestController
@Api("userController相关api")
public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    @ApiOperation("获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",dataType="Long",required=true,value="用户id")
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "test/get")
    public String getById(Long id) {
        if (id == null) {
            return AjaxResult.errorResult("id不能为空");
        }
        UserInfoVO userInfoVO = userTestService.getById(id);
        return AjaxResult.successResult(userInfoVO);
    }

}
