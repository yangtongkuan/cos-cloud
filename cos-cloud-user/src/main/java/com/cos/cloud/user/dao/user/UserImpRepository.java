package com.cos.cloud.user.dao.user;

import com.cos.cloud.user.model.bean.user.UserImpInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/29 21:20
 * @Classname: UserImpRepository
 * @To change this template use File | Settings | File Templates.
 */
public interface UserImpRepository extends JpaRepository<UserImpInfo, Long> {

    UserImpInfo findBySysCustomerAndUsername(String sysCustomer, String username);
}
