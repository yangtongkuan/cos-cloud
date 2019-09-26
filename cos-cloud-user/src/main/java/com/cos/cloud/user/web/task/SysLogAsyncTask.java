package com.cos.cloud.user.web.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cos.cloud.common.ann.SysLog;
import com.cos.cloud.user.model.bean.user.UserInfo;
import com.cos.cloud.user.model.bean.log.SystemLog;
import com.cos.cloud.user.service.syslog.SystemLogService;
import com.cos.cloud.user.service.user.UserService;
import com.cos.cloud.common.tools.ClientIpUtils;
import com.cos.cloud.common.tools.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/10 10:54
 * @Classname: SysLogAsyncTask
 * @To change this template use File | Settings | File Templates.
 */
@Service
@Async
@Slf4j
public class SysLogAsyncTask {

    private final String TOKEN = "token";
    private final String SYS_CUSTOMER = "sysCustomer";

    @Autowired
    private UserService userService;

    @Autowired
    private SystemLogService systemLogService;

    // 保存系统操作的日志
    public void saveSysLog(JoinPoint joinPoint, HttpServletRequest request, Object res, long beginTime, long endTime) {
        if (request == null) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        SystemLog systemLog = new SystemLog();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        SysLog sysLog = methodSignature.getMethod().getAnnotation(SysLog.class);
        systemLog.setTitle(sysLog.title()).setType(sysLog.type().name());
        systemLog.setDescription(sysLog.description());
        systemLog.setIp(ClientIpUtils.getClientIp(request));
        int tokenIndex = ArrayUtils.indexOf(methodSignature.getParameterNames(), TOKEN);
        int sysCustomerIndex = ArrayUtils.indexOf(methodSignature.getParameterNames(), SYS_CUSTOMER);
        if (tokenIndex != -1 && sysCustomerIndex != -1) {
            UserInfo userInfo = userService.getByUsername(joinPoint.getArgs()[sysCustomerIndex].toString(), joinPoint.getArgs()[tokenIndex].toString());
            if (userInfo != null) {
                systemLog.setOpUser(userInfo.getUsername());
            }
        }
        String requestTime = DateUtils.sdfHMSHDatTime.get().format(new Date(beginTime));
        systemLog.setRequestTime(requestTime).setConsumeTime(endTime - beginTime);
        systemLog.setMethodName(joinPoint.getTarget().getClass().getName() + "." + methodSignature.getName());
        systemLog.setRequestUrl(request.getRequestURI());
        String paramsNames[] = methodSignature.getParameterNames();
        StringBuilder params = new StringBuilder();
        for (int index = 0; index < joinPoint.getArgs().length; index++) {
            if (index == 0) {
                params.append(paramsNames[index] + "=" + JSON.toJSONString(joinPoint.getArgs()[index]));
            } else {
                params.append("&" + paramsNames[index] + "=" + JSON.toJSONString(joinPoint.getArgs()[index]));
            }
        }
        systemLog.setParams(params.toString());
        if (res != null && StringUtils.isNotEmpty(res.toString())) {
            JSONObject object = JSONObject.parseObject(res.toString());
            Object status = object.get("status");
            if (status != null) {
                systemLog.setResultStatus(status.toString());
            }
        }
//         请求时间 // 结束时间  //请求时长 //请求id // 类名 // 方法名 // 请求地址 // 请求参数 // 返回参数
        systemLogService.saveOrUpdate(systemLog);
    }

}
