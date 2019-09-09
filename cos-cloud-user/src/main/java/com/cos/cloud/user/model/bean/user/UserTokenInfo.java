package com.cos.cloud.user.model.bean.user;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/28 23:28
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
@Document(collection = "user_token_info")
public class UserTokenInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String token;
    private String sysCustomer;                 // 客户
    private String username;
    private String deviceType;                  // 设备类型
    private String loginIp;                     // 登录ip
    private Long time;
    private String dateTime;                    // 登录时间（format）
    private Long expireDate = 7 * 24 * 60 * 60 * 1000L;// 默认超时7天
}
