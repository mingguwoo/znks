package com.sh.znks.common.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuminggu on 2018/6/15.
 */
public class RequestInfoUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInfoUtils.class);

    /**
     * 获取客户端请求的ip
     * proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
     * Nginx代理层配置,多层代理会通过,分割设置在x-forwarded-for HEADER头中
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ip = "";
        try {
            ip = request.getHeader("j-forwarded-for");
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if(ip.equals("127.0.0.1")){
                    //根据网卡取本机配置的IP
                    InetAddress inet=null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        LOGGER.error("获取客户端ip出错{}", e);
                    }
                    if (inet != null)
                        ip= inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if(ip != null && ip.length() > 15){
                if(ip.indexOf(",")>0){
                    ip = ip.substring(0,ip.indexOf(","));
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取远程IP异常{}", e);
        }
        return ip;
    }
}
