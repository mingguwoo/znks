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
     * 查询单个题对应解析结果List
     * @param condition
     * @return
     */
    public List<Answer> getAnswerInfos(AnswerCondition condition);

    /**
     * 取得最大答案id值
     * @return
     */
    public String getAnswerIdMax();

    /**
     * 提交答案信息
     * @param answer
     * @return
     */
    public int insertAnswer(Answer answer);

    /**
     * 根据questionIdList取得答案List
     * @param userId
     * @param userZn
     * @param questionIdList
     * @return
     */
    public List<Answer> getAnswerInfoByQueList(String userId, String userZn, List<String> questionIdList);

    /**
     * 更新判断答题结果状态
     * @param param
     * @return
     */
    public int updateAnswerResult(JudgementParam param);
}
