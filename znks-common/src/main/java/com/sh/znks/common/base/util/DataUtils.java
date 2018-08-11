package com.sh.znks.common.base.util;

import java.util.regex.Pattern;

/**
 * Created by wuminggu on 2018/6/19.
 */
public class DataUtils {

    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
