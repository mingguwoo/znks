package com.sh.znks.common.base.util;

import org.apache.commons.lang.StringUtils;
import java.util.regex.Pattern;

/**
 * Created by wuminggu on 2018/6/15.
 */
public class UrlUtils {
    public static boolean isUrl(String url) {
        return checkUrl(url);
    }

    public static boolean checkUrl(String url) {
        /*if (StringUtils.isNotBlank(url)) {
            return Pattern.compile("^http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./:?%&#=]*)?$").matcher(url).matches()
                    || Pattern.compile("^//([\\w-]+\\.)+[\\w-]+(/[\\w- ./:?%&#=]*)?$").matcher(url).matches();
        }
        return false;*/

        if (StringUtils.isNotBlank(url)) {
            String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
            return Pattern.compile(regex).matcher(url).matches();
        }
        return false;
    }

    public static final String FAVICON = "/favicon.ico";
}
