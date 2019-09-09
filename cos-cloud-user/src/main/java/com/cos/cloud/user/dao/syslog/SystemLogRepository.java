package com.cos.cloud.user.dao.syslog;

import com.cos.cloud.user.model.bean.log.SystemLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SystemLogRepository extends MongoRepository<SystemLog, String> {
}
