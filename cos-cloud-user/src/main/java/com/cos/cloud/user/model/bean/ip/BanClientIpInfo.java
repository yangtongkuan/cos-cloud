package com.cos.cloud.user.model.bean.ip;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/30 10:04
 * @Classname: BanClientIpInfo
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
@Table(name = "t_ban_client_ip")
@Entity
public class BanClientIpInfo implements Serializable {
    @Id
    private Long id;
    private String sysCustomer;
    private String ip;
    private String note;
    private String createUser;
    private Date createTime;
    private Integer delFlag;
}
