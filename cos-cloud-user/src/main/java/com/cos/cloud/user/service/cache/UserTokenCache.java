package com.cos.cloud.user.service.cache;

import com.cos.cloud.common.config.redis.RedisConfig;
import com.cos.cloud.common.config.redis.RedisUtils;
import com.cos.cloud.common.tools.ClientIpUtils;
import com.cos.cloud.common.tools.DateUtils;
import com.cos.cloud.common.tools.TokenUtils;
import com.cos.cloud.user.dao.user.UserTokenRepository;
import com.cos.cloud.user.model.bean.user.UserTokenInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/28 23:58
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class UserTokenCache {

    private static final String TOKEN_CONTENT = "token";

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserTokenRepository userTokenRepository;

    // 登录
    @Transactional
    public UserTokenInfo createUserToken(String sysCustomer, String username, String deviceType) {
        List<UserTokenInfo> userTokenList = userTokenRepository.findBySysCustomerAndAndUsernameAndAndDeviceType(sysCustomer, username, deviceType);
        if (userTokenList != null && !userTokenList.isEmpty()) {
            List<UserTokenInfo> delUserTokenList =
                    userTokenList.stream().filter(tokenB -> !Optional.ofNullable(tokenB).isPresent() || deviceType.equalsIgnoreCase(tokenB.getDeviceType())).collect(Collectors.toList());
            if (delUserTokenList != null && !delUserTokenList.isEmpty()) {
                this.removeTokenCache(sysCustomer, delUserTokenList);
            }
        }
        String token = TokenUtils.createToken();
        UserTokenInfo userToken = new UserTokenInfo();
        userToken.setSysCustomer(sysCustomer).setUsername(username).setLoginIp(ClientIpUtils.getClientIp())
                .setExpireDate(RedisConfig.oneMonth)
                .setDeviceType(deviceType).setTime(DateUtils.getCurrentTimeMillis()).setToken(token).setDateTime(DateUtils.sdfHMSDatTime.get().format(LocalDate.now()));
        this.saveUserToken(userToken);
        return userToken;
    }

    // 获取当前登陆的账号信息
    public String getActiveUsername(String sysCustomer, String token) {
        UserTokenInfo tokenInfo = (UserTokenInfo) redisUtils.get(getTokenKey(sysCustomer, token));
        if (Optional.ofNullable(tokenInfo).map(UserTokenInfo::getUsername).isPresent()) {
            if (DateUtils.getCurrentTimeMillis() - tokenInfo.getExpireDate() > tokenInfo.getTime()) {
                // 失效了
                this.removeTokenCache(sysCustomer, token);
                return null;
            }
            return tokenInfo.getUsername();
        } else {
            // 查数据库
            tokenInfo = userTokenRepository.findBySysCustomerAndToken(sysCustomer, token);
            if (isValidToken(tokenInfo)) {
                // 缓存到redis
                redisUtils.set(getTokenKey(sysCustomer, token), tokenInfo, (tokenInfo.getExpireDate() + tokenInfo.getTime() - DateUtils.getCurrentTimeMillis()));
            } else {
                // token 无效 也不需要删除  因为在的登录的时候会自动删除
            }
        }
        return null;
    }


    // 保存token
    @Transactional
    public void saveUserToken(UserTokenInfo userToken) {
        if (userToken != null) {
            userTokenRepository.save(userToken);
            redisUtils.set(this.getTokenKey(userToken.getSysCustomer(), userToken.getToken()), userToken, userToken.getExpireDate());
        }
    }

    // 清除缓存
    public void removeTokenCache(String sysCustomer, String token) {
        if (!StringUtils.isEmpty(token)) {
            userTokenRepository.deleteByToken(sysCustomer, token);
            redisUtils.delete(getTokenKey(sysCustomer, token));
        }
    }

    public void removeTokenCache(String sysCustomer, List<UserTokenInfo> list) {
        if (list != null && !list.isEmpty()) {
            userTokenRepository.delete(list);
            Set<String> tokenSet = list.stream().filter(a -> Optional.ofNullable(a).map(UserTokenInfo::getToken).isPresent()).map(a -> getTokenKey(sysCustomer, a.getToken())).collect(Collectors.toSet());
            if (tokenSet != null && !tokenSet.isEmpty()) {
                redisTemplate.delete(tokenSet);
            }
        }
    }

    public boolean isValidToken(UserTokenInfo tokenInfo) {
        if (Optional.ofNullable(tokenInfo).map(UserTokenInfo::getUsername).isPresent()
                && DateUtils.getCurrentTimeMillis() - tokenInfo.getTime() <= tokenInfo.getExpireDate()) {
            return true;
        } else {
            return false;
        }
    }

    // 获取登录token的key
    private String getTokenKey(String sysCustomer, String token) {
        return sysCustomer + "." + TOKEN_CONTENT + "." + token;
    }
}
