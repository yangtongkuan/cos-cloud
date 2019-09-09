package com.cos.cloud.user.service.user;

import com.cos.cloud.user.dao.user.UserRepository;
import com.cos.cloud.user.model.bean.user.UserInfo;
import com.cos.cloud.user.model.vo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/5 20:36
 * @Classname: UserService
 * @To change this template use File | Settings | File Templates.
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserService {

//    @Autowired
//    private JPAQueryFactory jpaQuery;

    @Autowired
    private UserRepository userRepository;

    //
    // 根绝用户名设置唯一唯一的名字
    @Cacheable(key = "#p0+':'+#p1", condition = "#result!=null")
    public UserInfo getByUsername(String sysCustomer, String username) {
        return userRepository.findBySysCustomerAndUsernameAndDelFlag(sysCustomer, username, 0);
    }

    public UserInfo getByPhone(String sysCustomer, String phone) {
        return userRepository.findBySysCustomerAndPhoneAndDelFlag(sysCustomer, phone, 0);
    }

    public List<UserInfo> listByUsername(String sysCustomer, String username) {
        // dsl 查询 方式详解
        //1.
//        QUserInfo qUserInfo = QUserInfo.userInfo;
//        BooleanBuilder qBuilder = new BooleanBuilder();
//        qBuilder.and(qUserInfo.sysCustomer.eq(sysCustomer));
//        Predicate pre = qUserInfo.sysCustomer.eq(sysCustomer).and(qUserInfo.id.isNotNull());
//        if (StringUtils.isNotEmpty(username)) {
//            pre = ExpressionUtils.and(pre, qUserInfo.username.eq(username).or(qUserInfo.phone.eq(username)));
//        }
//        return (List<UserInfo>) userRepository.findAll(pre);
        // 2.
//        QUserInfo qUserInfo = QUserInfo.userInfo;
//        BooleanBuilder qBuilder = new BooleanBuilder();
//        qBuilder.and(qUserInfo.sysCustomer.eq(sysCustomer));
//        if (StringUtils.isNotEmpty(username)) {
//            qBuilder.and(qUserInfo.username.eq(username).or(qUserInfo.phone.eq(username)));
//        }
//        JPAQuery<UserInfo> userInfoJPAQuery = jpaQueryFactory.selectFrom(qUserInfo).where(qBuilder);
//        JPAQuery<UserInfo> userInfoJPAQuery = jpaQueryFactory.select(qUserInfo).from(qUserInfo).where(qBuilder);
//        return userInfoJPAQuery.fetch();
        // 3.
//        QUserInfo qUserInfo = QUserInfo.userInfo;
//        Predicate pre = qUserInfo.sysCustomer.eq(sysCustomer).and(qUserInfo.id.isNotNull());
//        if (StringUtils.isNotEmpty(username)) {
//            pre = ExpressionUtils.and(pre, qUserInfo.username.eq(username).or(qUserInfo.phone.eq(username)));
//        }
//        JPAQuery<UserInfo> userInfoJPAQuery = jpaQuery.select(qUserInfo).from(qUserInfo).where(pre);
//        long fetchCount = userInfoJPAQuery.fetchCount();
//        //  分页是从1开始的
//        QueryResults<UserInfo> queryResults = userInfoJPAQuery.orderBy(qUserInfo.id.desc()).offset(0).limit(2).fetchResults();
//        System.out.println(fetchCount);
//        System.out.println(queryResults.getTotal());
//        return queryResults.getResults();
////        return userInfoJPAQuery.orderBy(qUserInfo.id.desc()).fetch();
        return null;
    }


}
