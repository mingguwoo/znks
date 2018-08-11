package com.sh.znks.dao.impl;

import com.sh.znks.dao.UserDao;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminggu on 2018/5/9.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    private final static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private SqlSessionTemplate znksSqlSession;

    @Override
    public int insertExpertUser(ExpertUser expertUser) {
        return znksSqlSession.insert("User.insertExpertUser", expertUser);
    }

    @Override
    public List<ExpertUser> getExpertUserByExpertId(List<String> suList) {
        return znksSqlSession.selectList("User.getExpertUserByExpertId", suList);
    }

    @Override
    public Long getCountOfExpertUser(ExpertUser su) {
        return znksSqlSession.selectOne("User.getCountOfExpertUser", su);
    }

    @Override
    public ExpertUser getExpertUserByPhone(String pn) {
        return znksSqlSession.selectOne("User.getExpertUserByPhone", pn);
    }

    @Override
    public GeneralUser getGeneralUserByPhone(String pn) {
        return znksSqlSession.selectOne("User.getGeneralUserByPhone", pn);
    }

    @Override
    public Integer getIdentifyCode(String phoneNumber) {
        return znksSqlSession.selectOne("User.getIdentifyCode", phoneNumber);
    }

    @Override
    public Long getCountOfGeneralUser(GeneralUser su) {
        return znksSqlSession.selectOne("User.getCountOfGeneralUser", su);
    }

    @Override
    public int insertGeneralUser(GeneralUser generalUser) {
        return znksSqlSession.insert("User.insertGeneralUser", generalUser);
    }

    @Override
    public int insertIdentifyCode(String phoneNumber, int identifyCode) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("identifyCode", identifyCode);
        queryMap.put("phoneNumber", phoneNumber);
        return znksSqlSession.insert("User.insertIdentifyCode", queryMap);
    }

    @Override
    public int updateGenInfoByPn(GeneralUser user) {
        return znksSqlSession.update("User.updateGenInfoByPn", user);
    }

    @Override
    public int updateExpInfoByPn(ExpertUser user) {
        return znksSqlSession.update("User.updateExpInfoByPn", user);
    }
}
