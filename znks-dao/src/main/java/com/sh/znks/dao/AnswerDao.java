package com.sh.znks.dao;

import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.dto.AnswerCondition;
import com.sh.znks.domain.dto.JudgementParam;

import java.util.List;

/**
 * Created by wuminggu on 2018/7/13.
 */
public interface AnswerDao {
    /**
     * ��ѯ�������Ӧ�������List
     * @param condition
     * @return
     */
    public List<Answer> getAnswerInfos(AnswerCondition condition);

    /**
     * ȡ������idֵ
     * @return
     */
    public String getAnswerIdMax();

    /**
     * �ύ����Ϣ
     * @param answer
     * @return
     */
    public int insertAnswer(Answer answer);

    /**
     * ����questionIdListȡ�ô�List
     * @param userId
     * @param userZn
     * @param questionIdList
     * @return
     */
    public List<Answer> getAnswerInfoByQueList(String userId, String userZn, List<String> questionIdList);

    /**
     * �����жϴ�����״̬
     * @param param
     * @return
     */
    public int updateAnswerResult(JudgementParam param);
}
