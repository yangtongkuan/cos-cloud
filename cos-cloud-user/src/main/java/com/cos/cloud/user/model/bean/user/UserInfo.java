package com.cos.cloud.user.model.bean.user;

import com.cos.cloud.user.model.vo.user.UserInfoVO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/7/29 18:44
 * @Classname: UserInfo
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_user_info")
public class UserInfo implements Serializable {

    @Id
    private Long id;
    private String sysCustomer;
    private String username;
    private String name;
    private String sex;
    private String address;
    private String phone; // 登录账号
    private String photo;
    private String signature;
    private int delFlag = 0;
    private Date createTime;
    private String createUser;
    private String delUser;
    private Date delDate;
    private String updateUser;
    private Date updateTime;

    // 类型转换信息返回
    public UserInfoVO convert2VO() {
        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
}
