package com.sh.znks.dao.impl;

import com.sh.znks.dao.QuestionDao;
import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.AnswerCondition;
import com.sh.znks.domain.dto.QuestionCondition;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminggu on 2018/7/5.
 */
@Repository("questionDao")
public class QuestionDaoImpl implements QuestionDao {
    private final static Logger log = LoggerFactory.getLogger(QuestionDaoImpl.class);

    @Autowired
    private SqlSessionTemplate znksSqlSession;

    @Override
    public int insertQuestion(Question question) {
        return znksSqlSession.insert("Question.insertQuestion", question);
    }

    @Override
    public String getQuestionIdMax() {
        return znksSqlSession.selectOne("Question.getQuestionIdMax");
    }

    @Override
    public List<Question> getQuestionInfos(QuestionCondition condition) {
        return znksSqlSession.selectList("Question.getQuestionInfos", condition);
    }

    @Override
    public List<Question> getQuestionInfoByQueList(String expertId, String expertZn, List<String> questionIdList) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("expertId", expertId);
        queryMap.put("expertZn", expertZn);
        queryMap.put("questionIdList", questionIdList);
        return znksSqlSession.selectList("Question.getQuestionInfoByQueList", queryMap);
    }
}
