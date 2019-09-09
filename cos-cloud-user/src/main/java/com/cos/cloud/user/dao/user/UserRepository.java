package com.cos.cloud.user.dao.user;

import com.cos.cloud.user.model.bean.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/8 19:33
 * @Classname: UserRepository
 * @To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends JpaRepository<UserInfo, Long> ,QueryDslPredicateExecutor<UserInfo> {

    // 通过用户唯一的username获取
    UserInfo findBySysCustomerAndUsernameAndDelFlag(String sysCustomer, String username, int delFlag);

    UserInfo findBySysCustomerAndPhoneAndDelFlag(String sysCustomer, String phone, int delFlag);

}
