package com.cos.cloud.user.web.controller.user;

import com.cos.cloud.common.tools.AjaxResult;
import com.cos.cloud.user.model.bean.sys.SysCustomerInfo;
import com.cos.cloud.user.model.custom_exc.UnKnowSysCustomerException;
import com.cos.cloud.user.model.other.DeviceType;
import com.cos.cloud.user.model.vo.user.UserInfoVO;
import com.cos.cloud.user.service.sys.SysCustomerService;
import com.cos.cloud.user.service.user.UserAuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/29 21:52
 * @Classname: UserAuthController
 * @To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("auth")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private SysCustomerService sysCustomerService;

    @RequestMapping(method = RequestMethod.POST, value = "login")
    public String login(String sysCustomer, String username, String password, String deviceType) throws UnKnowSysCustomerException {
        if (StringUtils.isEmpty(sysCustomer))
            return AjaxResult.errorResult("系统标识不能为空");
        if (StringUtils.isEmpty(username))
            return AjaxResult.errorResult("登录账号不能为空");
        if (StringUtils.isEmpty(deviceType))
            return AjaxResult.errorResult("设备类型不能为空");
        if (DeviceType.isNotValidDeviceType(deviceType)) {
            return AjaxResult.errorResult("未知的设备类型");
        }
        SysCustomerInfo info = sysCustomerService.getValidSysCustomerInfo(sysCustomer);
        UserInfoVO userInfoVO = null;
        try {
            userInfoVO = userAuthService.login(info.getIdentify(), username, password, deviceType);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errorResult(e.getMessage());
        }
        return AjaxResult.successResult(userInfoVO);
    }

}
