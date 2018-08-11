package com.sh.znks.service.base;

import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.register.User;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;

/**
 * Created by wuminggu on 2018/5/9.
 */
public interface UserService {
    /**
     * 注册学生信息
     * @param user
     * @return
     */
    ResultResponse registerGeneralUser(GeneralUser user);

    /**
     * 注册专家信息
     * @param user
     * @return
     */
    ResultResponse registerExpertUser(ExpertUser user);

    /**
     * 根据电话号码查询用户信息
     * @param phoneNumber
     * @return
     */
    ResultResponse getGeneralUserInfo(String phoneNumber);

    /**
     * 根据电话号码查询专家信息
     * @param phoneNumber
     * @return
     */
    ResultResponse getExpertUserInfo(String phoneNumber);

    /**
     * 取得专家登录后的返回值信息(验证码登录)
     * @param phoneNumber
     * @param identifyCode
     * @return
     */
    ResultResponse getSmsExpLoginInfo(String phoneNumber, int identifyCode);

    /**
     * 取得登录后的返回值信息(密码登录)
     * @param phoneNumber
     * @param passwordEncrypt
     * @return
     */
    ResultResponse getPwdExpLoginInfo(String phoneNumber, String passwordEncrypt);

    /**
     * 取得学生登录后的返回值信息(验证码登录)
     * @param phoneNumber
     * @param identifyCode
     * @return
     */
    ResultResponse getSmsGenLoginInfo(String phoneNumber, int identifyCode);

    /**
     * 取得登录后的返回值信息(密码登录)
     * @param phoneNumber
     * @param passwordEncrypt
     * @return
     */
    ResultResponse getPwdGenLoginInfo(String phoneNumber, String passwordEncrypt);

    /**
     * 退出登录(学生)
     * @return
     */
    ResultResponse logoutGen();

    /**
     * 退出登录(专家)
     * @return
     */
    ResultResponse logoutExp();

    /**
     * 发送短信验证码
     * @param phoneNumber
     * @param occasion
     * @return
     */
    ResultResponse sendIdentifyCode(String phoneNumber, Integer occasion);

    /**
     * 重置密码
     * @param phoneNumber
     * @param identifyCode
     * @param password
     * @param passwordEncrypt
     * @return
     */
    ResultResponse resetPassword(String phoneNumber, Integer identifyCode, String password, String passwordEncrypt, Integer platform);

    /**
     * 绑定结算账号（修改）
     * @param phoneNumber
     * @param identifyCode
     * @param accountNo
     * @param accountType
     * @return
     */
    ResultResponse bindingAccount(String phoneNumber, Integer identifyCode, String accountNo, Integer accountType, Integer platform);

    /**
     * 更新学生基础信息
     * @param user
     * @return
     */
    ResultResponse updateGenInfos(GeneralUser user);

    /**
     * 更新专家基础信息
     * @param user
     * @return
     */
    ResultResponse updateExpInfos(ExpertUser user);
}
