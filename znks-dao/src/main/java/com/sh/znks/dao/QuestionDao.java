package com.sh.znks.dao;

import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.QuestionCondition;
import java.util.List;

/**
 * Created by wuminggu on 2018/7/5.
 */
public interface QuestionDao {
    /**
     * 插入问题信息
     * @param question
     * @return
     */
    public int insertQuestion(Question question);

    /**
     * 取得最大的问题id值
     * @return
     */
    public String getQuestionIdMax();

    /**
     * 查询题目详情List
     * @return
     */
    public List<Question> getQuestionInfos(QuestionCondition condition);

    /**
     * 根据questionIdList取得问题List
     * @param expertId
     * @param expertZn
     * @param questionIdList
     * @return
     */
    public List<Question> getQuestionInfoByQueList(String expertId, String expertZn, List<String> questionIdList);
}
