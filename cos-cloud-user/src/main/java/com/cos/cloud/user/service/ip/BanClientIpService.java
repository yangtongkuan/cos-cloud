package com.cos.cloud.user.service.ip;

import com.cos.cloud.common.tools.IdWorker;
import com.cos.cloud.user.dao.ip.BanClientIpRepository;
import com.cos.cloud.user.model.bean.ip.BanClientIpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/30 10:15
 * @Classname: BanClientIpService
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class BanClientIpService {

    @Autowired
    private BanClientIpRepository banClientIpRepository;

    // sysCustomer,set<ip>
    private static ConcurrentHashMap<String, Set<String>> dataMap = new ConcurrentHashMap<>();

    // 初始化
    @PostConstruct
    private void initLoad() {
        dataMap.clear();
        List<BanClientIpInfo> ipInfoList = banClientIpRepository.findByDelFlag(0);
        if (ipInfoList != null && !ipInfoList.isEmpty()) {
            Map<String, Set<String>> map = ipInfoList.stream()
                    .filter(a -> Optional.ofNullable(a).map(BanClientIpInfo::getIp).isPresent() && Optional.ofNullable(a).map(BanClientIpInfo::getSysCustomer).isPresent())
                    .collect(Collectors.groupingBy(BanClientIpInfo::getSysCustomer, Collectors.mapping(BanClientIpInfo::getIp, Collectors.toSet())));
            if (map != null) {
                dataMap.putAll(map);
            }
        }
    }

    // 获取系统白名单
    public Set<String> listOfBanClientIp(String sysCustomer) {
        if (dataMap.containsKey(sysCustomer)) {
            return dataMap.get(sysCustomer);
        } else {
            List<BanClientIpInfo> clientIpList = banClientIpRepository.findBySysCustomerAndDelFlag(sysCustomer, 0);
            Set<String> set = null;
            if (clientIpList != null) {
                set = clientIpList.stream().filter(a -> Optional.ofNullable(a).map(BanClientIpInfo::getIp).isPresent())
                        .map(a -> a.getIp()).collect(Collectors.toSet());
            }
            if (set == null) {
                set = Collections.emptySet();
            }
            dataMap.put(sysCustomer, set);
            return set;
        }
    }

    // 添加一个禁止访问的ip
    public void addBanClientIp(BanClientIpInfo info) throws Exception {
        Set<String> set = this.listOfBanClientIp(info.getSysCustomer());
        if (Optional.ofNullable(set).isPresent() && set.contains(info.getIp())) {
            throw new Exception("当前ip已存在");
        }
        info.setCreateTime(new Date()).setId(IdWorker.getInstance().nextId());
        banClientIpRepository.save(info);
        if (set == null) {
            set = new HashSet<>();
        }
        set.add(info.getIp());
        dataMap.put(info.getSysCustomer(), set);
    }

    // 删除一个禁止访问的ip
    public void delBanClientIp(String sysCustomer, Long id) throws Exception {
        BanClientIpInfo clientIpInfoDB = this.getBanClientIp(sysCustomer, id);
        clientIpInfoDB.setDelFlag(1);
        banClientIpRepository.save(clientIpInfoDB);
        Set<String> set = dataMap.get(sysCustomer);
        if (Optional.ofNullable(set).isPresent() && set.contains(clientIpInfoDB.getIp())) {
            set.remove(clientIpInfoDB.getIp());
            dataMap.put(sysCustomer, set);
        }
    }

    // 通过id 获取ip地址
    public BanClientIpInfo getBanClientIp(String sysCustomer, Long id) throws Exception {
        BanClientIpInfo clientIpInfo = banClientIpRepository.findOne(id);
        if (Optional.ofNullable(clientIpInfo).map(a -> a.getSysCustomer()).isPresent()
                && !sysCustomer.equalsIgnoreCase(clientIpInfo.getSysCustomer())) {
            throw new Exception("未查询到ip信息");
        }
        return clientIpInfo;
    }
}
