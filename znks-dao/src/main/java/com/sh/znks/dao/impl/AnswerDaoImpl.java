package com.sh.znks.dao.impl;

import com.sh.znks.dao.AnswerDao;
import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.dto.AnswerCondition;
import com.sh.znks.domain.dto.JudgementParam;

import org.apache.commons.collections.map.HashedMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminggu on 2018/7/13.
 */
@Repository("AnswerDao")
public class AnswerDaoImpl implements AnswerDao {
    private final static Logger log = LoggerFactory.getLogger(AnswerDaoImpl.class);

    @Autowired
    private SqlSessionTemplate znksSqlSession;

    @Override
    public List<Answer> getAnswerInfos(AnswerCondition condition) {
        return znksSqlSession.selectList("Answer.getAnswerInfos", condition);
    }

    @Override
    public String getAnswerIdMax() {
        return znksSqlSession.selectOne("Answer.getAnswerIdMax");
    }

    @Override
    public int insertAnswer(Answer answer) {
        return znksSqlSession.insert("Answer.insertAnswer", answer);
    }

    @Override
    public List<Answer> getAnswerInfoByQueList(String userId, String userZn, List<String> questionIdList) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("userId", userId);
        queryMap.put("userZn", userZn);
        queryMap.put("questionIdList", questionIdList);
        return znksSqlSession.selectList("Answer.getAnswerInfoByQueList", queryMap);
    }

    @Override
    public int updateAnswerResult(JudgementParam param) {
        return znksSqlSession.update("Answer.updateAnswerResult", param);
    }
}
