package com.cos.cloud.user.service.syslog;

import com.cos.cloud.user.dao.syslog.SystemLogRepository;
import com.cos.cloud.user.model.bean.log.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemLogService {
    @Autowired
    private SystemLogRepository systemLogRepository;

    // 保存或者修改日志
    public void saveOrUpdate(SystemLog systemLog) {
        if (systemLog != null) {
            systemLogRepository.save(systemLog);
        }
    }
    public  List<SystemLog> findAll(){
       return systemLogRepository.findAll();
    }

}
