package com.cos.cloud.user.web.controller.syslog;

import com.cos.cloud.user.model.bean.log.SystemLog;
import com.cos.cloud.user.service.syslog.SystemLogService;
import com.cos.cloud.common.tools.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("log")
public class SystemLogController {

    @Autowired
    private SystemLogService systemLogService;

    @RequestMapping("/list")
    public String systemLogList() {
        List<SystemLog> logServiceAll = systemLogService.findAll();
        if (logServiceAll != null) {
            return AjaxResult.successResult(logServiceAll.size(), logServiceAll);
        }
        return AjaxResult.successResult(0, null);
    }
}

