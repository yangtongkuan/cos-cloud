package com.cos.cloud.user.service.user;

import com.cos.cloud.user.dao.user.UserRepository;
import com.cos.cloud.user.model.bean.user.UserInfo;
import com.cos.cloud.user.model.vo.user.UserInfoVO;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/25 22:00
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class UserTestService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public UserInfoVO getById(Long id) {
//        QUserInfo qUserInfo = QUserInfo.userInfo;
        // 1. jpa
//        Predicate predicate = qUserInfo.id.eq(id);
//        UserInfo userInfo = userRepository.findOne(predicate);
        // 2. jpaQuery
//        UserInfo userInfo = jpaQueryFactory.selectFrom(qUserInfo).where(qUserInfo.id.eq(id)).fetchOne();
//        if (userInfo != null) {
//            return userInfo.convert2VO();
//        }
        // 3.
//        jpaQueryFactory.selectFrom(qUserInfo).leftJoin(qUserInfo).on(qUserInfo.id.eq(qUserInfo.id)).where()
//        Predicate predicate = ExpressionUtils.and(qUserInfo.id.eq(id), qUserInfo.delFlag.eq(0));
//        UserInfo userInfo = userRepository.findOne(predicate);
//        if (userInfo != null) {
//            return userInfo.convert2VO();
//        }
        return null;
    }
}

