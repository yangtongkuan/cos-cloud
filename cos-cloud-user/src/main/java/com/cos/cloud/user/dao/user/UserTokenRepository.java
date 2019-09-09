package com.cos.cloud.user.dao.user;

import com.cos.cloud.user.model.bean.user.UserTokenInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/28 23:54
 * @To change this template use File | Settings | File Templates.
 */
public interface UserTokenRepository extends MongoRepository<UserTokenInfo, String> {

    // 获取当前登录的用户的token
    List<UserTokenInfo> findBySysCustomerAndAndUsernameAndAndDeviceType(String sysCustomer, String username, String deviceType);

    @Modifying
    @Query("delete from UserTokenInfo u where u.sysCustomer=?1 and  u.token=?2 ")
    int deleteByToken(String sysCustomer, String token);

    UserTokenInfo findBySysCustomerAndToken(String sysCustomer, String token);


}
