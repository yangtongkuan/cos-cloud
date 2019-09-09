package com.cos.cloud.user.dao.ip;

import com.cos.cloud.user.model.bean.ip.BanClientIpInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/30 10:14
 * @Classname: BanClientIpRepository
 * @To change this template use File | Settings | File Templates.
 */
public interface BanClientIpRepository extends JpaRepository<BanClientIpInfo, Long> {

    List<BanClientIpInfo> findByDelFlag(Integer flag);

    List<BanClientIpInfo> findBySysCustomerAndDelFlag(String sysCustomer, Integer flag);

}
