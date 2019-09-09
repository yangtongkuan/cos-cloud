package com.cos.cloud.user.model.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/29 21:58
 * @Classname: DeviceType
 * @To change this template use File | Settings | File Templates.
 */
public class DeviceType {

    private static List<String> deviceTypeList = new ArrayList<>();

    static {
        deviceTypeList.add("pc");
        deviceTypeList.add("android");
        deviceTypeList.add("ios");
    }

    public static boolean isValidDeviceType(String deviceType) {
        for (String tmp : deviceTypeList) {
            if (tmp.equalsIgnoreCase(deviceType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotValidDeviceType(String deviceType) {
        return isValidDeviceType(deviceType);
    }


}
