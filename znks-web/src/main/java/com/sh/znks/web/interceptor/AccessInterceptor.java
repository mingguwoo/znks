package com.sh.znks.web.interceptor;

import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.common.base.util.ParamEditUtils;
import com.sh.znks.common.base.util.RedisKeyConstant;
import com.sh.znks.common.base.util.RedisUtils;
import com.sh.znks.common.base.util.UrlUtils;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.service.base.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuminggu on 2018/6/4.
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
    private final static Logger log = LoggerFactory.getLogger(AccessInterceptor.class);

    @Autowired
    private UserService userService;
    @Autowired
    private HashSet<String> generalLoginUrlSet;
    @Autowired
    private HashSet<String> expertLoginUrlSet;
    @Autowired
    private HashSet<String> logoutUrlSet;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //Ԥ����
        boolean res = false;
        String url = request.getRequestURL().toString();
        log.error("url is {}", url);
        String path = request.getServletPath();
        if (StringUtils.isNotBlank(path) && path.startsWith("/")) {
            //���˵�������Զ����͵�favicon.ico����
            if (UrlUtils.FAVICON.equals(path)) {
                res = true;
            }

            //�˳��˺�ʱ�����Cookie
            if (logoutUrlSet != null && logoutUrlSet.contains(path)) {
                clearCookie(request,response, Constant.PJN);
            }

            //Base64����,�ж�cookie����û���Ϣ�Ƿ��redis�����Ϣһ��
            Cookie znCookie = getCookie(request, Constant.PJN);
            if (znCookie != null) {
                String zn = znCookie.getValue();
                zn = new String(new BASE64Decoder().decodeBuffer(zn), "UTF-8");
                GeneralUser gen = (GeneralUser) redisUtils.get(RedisKeyConstant.KEY_GENERAL_USER_INFO);
                ExpertUser exp = (ExpertUser) redisUtils.get(RedisKeyConstant.KEY_EXPERT_USER_INFO);
                if (gen != null && zn.equals(gen.getZn())) res = true;
                if (exp != null && zn.equals(exp.getZn())) res = true;
            }

            String phoneNumber = request.getParameter("phoneNumber");
            if (generalLoginUrlSet != null && generalLoginUrlSet.contains(path)) {
                //��ѯ��¼�û��Ƿ���ڲ�����Ч
                ResultResponse gen = userService.getGeneralUserInfo(phoneNumber);
                if (gen.isSuccess()) {
                    GeneralUser info = (GeneralUser) gen.get(Constant.GENERAL_USER_INFO);
                    AuthorHolder.setGeneralAuthor(info);
                    //���û���Ϣ�ŵ�redis��
                    redisUtils.set(RedisKeyConstant.KEY_GENERAL_USER_INFO, info);
                    //Ĭ����Чʱ��Ϊ�ر������֮ǰ
                    setCookie(response,"zn",info.getZn(),Constant.EXC_NO);
                    res = true;
                }
            }

            if (expertLoginUrlSet != null && expertLoginUrlSet.contains(path)) {
                //��ѯ��¼ר���Ƿ���ڲ�����Ч
                ResultResponse exp = userService.getExpertUserInfo(phoneNumber);
                if (exp.isSuccess()) {
                    ExpertUser info = (ExpertUser) exp.get(Constant.EXPERT_USER_INFO);
                    AuthorHolder.setExpertAuthor(info);
                    //���û���Ϣ�ŵ�redis��
                    redisUtils.set(RedisKeyConstant.KEY_EXPERT_USER_INFO, info);
                    //Ĭ����Чʱ��Ϊ�ر������֮ǰ
                    setCookie(response,Constant.PJN,info.getZn(),Constant.EXC_NO);
                    res = true;
                }
            }
        }

        return res;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //����
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    /**
     * ����Cookies
     * @param response
     * @param key
     * @param value
     * @param time
     * @return
     */
    public static HttpServletResponse setCookie(HttpServletResponse response, String key, String value, int time) {
        try {
            if (StringUtils.isNotBlank(value)) {
                value = (new BASE64Encoder()).encode(value.getBytes("UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            log.error("L108_setCookie e:", e);
        }

        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setMaxAge(time);
        response.addCookie(cookie);
        return response;
    }

    /**
     * ɾ����Чcookie
     * @param request
     * @param response
     * @param deleteKey
     */
    private void clearCookie(HttpServletRequest request, HttpServletResponse response, String deleteKey) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        for (String key : cookieMap.keySet()) {
            if (StringUtils.isBlank(key)) {
                continue;
            }
            if (key.equals(deleteKey)) {
                Cookie cookie = cookieMap.get(key);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

    /**
     * ��cookie��װ��Map����
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * �������ֻ�ȡcookie
     * @param request
     * @param key
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String key) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        return cookieMap.containsKey(key) ? cookieMap.get(key) : null;
    }
}
