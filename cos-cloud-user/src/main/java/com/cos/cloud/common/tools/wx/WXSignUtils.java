package com.cos.cloud.common.tools.wx;

import com.cos.cloud.common.tools.OrderNOUtils;
import com.cos.cloud.common.tools.RandomCharsUtils;

import java.math.BigDecimal;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @desc: 微信获取签名
 * @User: @Created by yangtk
 * @Date: @Date 2019/9/24 13:00
 * @Classname: WXSignUtils
 * @To change this template use File | Settings | File Templates.
 */
public class WXSignUtils {
    private static final String trade_type = "APP";

    public static void getSign(String appId, String mchId, String out_trade_no, Double payMoney, String notify_url) {
        SortedMap<Object, Object> params = new TreeMap<Object, Object>();
        params.put("appid", appId);
        params.put("mch_id", mchId);
        params.put("nonce_str", RandomCharsUtils.getRandomChar(16));
        params.put("body", "cos订单支付-测试支付");
        params.put("out_trade_no", out_trade_no);
        params.put("total_fee", calculateMoney(payMoney));
        params.put("spbill_create_ip", "127.0.0.1");
        params.put("time_start", "time_start");
        params.put("time_expire", "time_expire");
        params.put("notify_url", notify_url);
        params.put("trade_type", trade_type);


    }

    // 由元转成分
    private static Integer calculateMoney(Double money) {
        BigDecimal val1 = new BigDecimal(Double.toString(money));
        BigDecimal val2 = new BigDecimal(Double.toString(100));
        return (int) val1.multiply(val2).doubleValue();
    }


}
