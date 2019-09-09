package com.cos.cloud.user.web.controller.user;

import com.cos.cloud.common.ann.SysLog;
import com.cos.cloud.common.ann.SysLogType;
import com.cos.cloud.user.model.bean.sys.SysCustomerInfo;
import com.cos.cloud.user.model.bean.user.UserInfo;
import com.cos.cloud.user.model.custom_exc.UnKnowSysCustomerException;
import com.cos.cloud.user.service.sys.SysCustomerService;
import com.cos.cloud.user.service.user.UserService;
import com.cos.cloud.common.tools.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/7/31 18:47
 * @Classname: UserController
 * @To change this template use File | Settings | File Templates.
 */
@RestController
@Slf4j
public class UserController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;
    @Autowired
    private SysCustomerService sysCustomerService;

    @RequestMapping(value = "/save")
    public Object save(String username) {
        for (int index = 0; index < 10; index++) {
            UserInfo user = new UserInfo();
            user.setUsername(username).setCreateTime(new Date())
                    .setId(Long.parseLong(String.valueOf(index)));
            redisTemplate.opsForValue().set("user:" + String.valueOf(index), user, 60, TimeUnit.SECONDS);
        }
        return AjaxResult.successResult();
    }

    @RequestMapping(value = "/get")
    @SysLog(type = SysLogType.USER)
    public Object get(String sysCustomer, String username, UserInfo userInfo) {
        UserInfo user = userService.getByUsername("o2o-test", "15269020596");
        return AjaxResult.successResult(user);
    }

    @RequestMapping(value = "/sql/list")
    @SysLog(type = SysLogType.USER)
    public String get(String sysCustomer, String username) throws UnKnowSysCustomerException {
        SysCustomerInfo customerInfo = sysCustomerService.getValidSysCustomerInfo(sysCustomer);
        List<UserInfo> userInfos = userService.listByUsername(customerInfo.getIdentify(), username);
        return AjaxResult.successResult(userInfos == null ? 0 : userInfos.size(), userInfos);
    }


//    @RequestMapping(value = "login/by-password")
//    public String  loginByPassword(String sysCustomer,){}

}
