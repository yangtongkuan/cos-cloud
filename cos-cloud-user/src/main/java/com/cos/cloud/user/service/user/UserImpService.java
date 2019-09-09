package com.cos.cloud.user.service.user;

import com.cos.cloud.common.tools.MD5Utils;
import com.cos.cloud.user.dao.user.UserImpRepository;
import com.cos.cloud.user.model.bean.user.UserImpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/29 21:21
 * @Classname: UserImpService
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class UserImpService {

    @Autowired
    private UserImpRepository userImpRepository;

    public void validUserPassWd(String sysCustomer, String username, String passWd) throws Exception {
        UserImpInfo userImp = userImpRepository.findBySysCustomerAndUsername(sysCustomer, username);
        if (userImp == null) {
            throw new Exception("获取密码信息错误");
        }
        String byMd5 = MD5Utils.encoderByMd5(passWd);
        if (!byMd5.equals(userImp.getPassword())) {
            throw new Exception("用户名或密码错误");
        }
    }


}
