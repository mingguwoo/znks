package com.sh.znks.dao;

import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;

import java.util.List;

/**
 * Created by wuminggu on 2018/5/9.
 */
public interface UserDao {
    /**
     * 插入专家个人信息
     * @param expertUser
     * @return
     */
    int insertExpertUser(ExpertUser expertUser);

    /**
     * 根据专家id查询专家信息详情
     * @param suList
     * @return
     */
    List<ExpertUser> getExpertUserByExpertId(List<String> suList);

    /**
     * 取得专家总数
     * @param su
     * @return
     */
    Long getCountOfExpertUser(ExpertUser su);

    /**
     * 根据电话号码查询专家信息
     * @param pn
     * @return
     */
    ExpertUser getExpertUserByPhone(String pn);

    /**
     * 根据电话号码查询用户信息
     * @param pn
     * @return
     */
    GeneralUser getGeneralUserByPhone(String pn);

    /**
     * 取得验证码
     * @param phoneNumber
     * @return
     */
    Integer getIdentifyCode(String phoneNumber);

    /**
     * 取得学生总数
     * @param su
     * @return
     */
    Long getCountOfGeneralUser(GeneralUser su);

    /**
     * 插入专家个人信息
     * @param generalUser
     * @return
     */
    int insertGeneralUser(GeneralUser generalUser);

    /**
     * 将验证码插入到表中
     * @param phoneNumber
     * @param IdentifyCode
     * @return
     */
    int insertIdentifyCode(String phoneNumber, int IdentifyCode);

    /**
     * 根据电话号码更新学生信息
     * @param user
     * @return
     */
    int updateGenInfoByPn(GeneralUser user);

    /**
     * 根据电话号码更新专家信息
     * @param user
     * @return
     */
    int updateExpInfoByPn(ExpertUser user);
}
