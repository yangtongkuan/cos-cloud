package com.cos.cloud.user.service.user;

import com.cos.cloud.common.tools.CosCommonUtils;
import com.cos.cloud.user.dao.user.UserRepository;
import com.cos.cloud.user.model.bean.user.UserInfo;
import com.cos.cloud.user.model.bean.user.UserTokenInfo;
import com.cos.cloud.user.model.vo.user.UserInfoVO;
import com.cos.cloud.user.service.cache.UserTokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/29 21:12
 * @Classname: UserAuthService
 * @To change this template use File | Settings | File Templates.
 * @desc: 用户登录授权
 */
@Service
public class UserAuthService {


    @Autowired
    private UserService userService;
    @Autowired
    private UserImpService userImpService;

    @Autowired
    private UserTokenCache userTokenCache;

    public UserInfoVO login(String sysCustomer, String username, String passWd, String deviceType) throws Exception {
        UserInfo userInfo = null;
        if (CosCommonUtils.isEmail(username)) {
            // 邮箱
        } else if (CosCommonUtils.isMobile(username)) {
            userInfo = userService.getByPhone(sysCustomer, username);
        } else {
            userInfo = userService.getByUsername(sysCustomer, username);
        }
        if (userInfo == null) {
            throw new Exception("未获取到用户信息");
        }
        // 验证密码是否可以登录
        try {
            userImpService.validUserPassWd(sysCustomer, userInfo.getUsername(), passWd);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        // 创建一个token
        UserTokenInfo userToken = userTokenCache.createUserToken(sysCustomer, username, deviceType);
        return userInfo.convert2VO().setToken(userToken.getToken());
    }


}
