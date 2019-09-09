package com.cos.cloud.user.model.bean.log;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Document(collection = "sys_log")
public class SystemLog implements Serializable {
    @Id
    private String id;
    private String sysCustomer;
    // 请求时间 // 结束时间  //请求时长 //请求id // 类名 // 方法名 // 请求地址 // 请求参数 // 返回参数
    private String type; // 日志类型
    private String title;
    private String description;
    private String ip;
    private String opUser;
    private String requestTime;
    private long consumeTime;
    private String methodName;
    private String requestUrl;
    private String params;
    private String resultStatus;
    private Date createTime;

    public SystemLog() {
        this.createTime = new Date();
    }
}
