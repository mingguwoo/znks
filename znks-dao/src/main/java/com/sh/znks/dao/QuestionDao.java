package com.sh.znks.dao;

import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.QuestionCondition;
import java.util.List;

/**
 * Created by wuminggu on 2018/7/5.
 */
public interface QuestionDao {
    /**
     * ����������Ϣ
     * @param question
     * @return
     */
    public int insertQuestion(Question question);

    /**
     * ȡ����������idֵ
     * @return
     */
    public String getQuestionIdMax();

    /**
     * ��ѯ��Ŀ����List
     * @return
     */
    public List<Question> getQuestionInfos(QuestionCondition condition);

    /**
     * ����questionIdListȡ������List
     * @param expertId
     * @param expertZn
     * @param questionIdList
     * @return
     */
    public List<Question> getQuestionInfoByQueList(String expertId, String expertZn, List<String> questionIdList);
}
