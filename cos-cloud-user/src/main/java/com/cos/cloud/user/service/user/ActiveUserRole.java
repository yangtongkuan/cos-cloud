package com.cos.cloud.user.service.user;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/29 0:01
 * @To change this template use File | Settings | File Templates.
 */
public class ActiveUserRole {

    // 权限表示信息
    public static final int USER = 1 << 0;              // 普通用户
    public static final int SUPER_MANAGER = 1 << 10;    // 吵架管理员


    public static void main(String[] args) {
        System.out.println(SUPER_MANAGER | USER - USER);
    }


}
