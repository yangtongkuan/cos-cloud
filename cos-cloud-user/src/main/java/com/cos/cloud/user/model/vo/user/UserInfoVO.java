package com.cos.cloud.user.model.vo.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/8 20:01
 * @Classname: UserInfoVO
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
public class UserInfoVO implements Serializable {

    private Long id;
    private String sysCustomer;
    private String username;
    private String name;
    private String sex;
    private String address;
    private String phone; // 登录账号
    private String photo;
    private String signature;
    private String token;

}
