package com.sh.znks.service.business;

import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.dto.JudgementParam;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.dto.QuestionParam;

/**
 * Created by wuminggu on 2018/7/5.
 */
public interface QuestionService {
    /**
     * 发布题目
     * @param param
     * @return
     */
    public ResultResponse deployQuestion(QuestionParam param);

    /**
     * 查询题目列表
     * 包括已做题、未做题筛选
     * @param condition
     * @return
     */
    public ResultResponse getQuestionList(QuestionCondition condition);

    /**
     * 取得指定题目的详情
     * @param questionId
     * @return
     */
    public ResultResponse questionDetail(String questionId);

    /**
     * 提交答案
     * @param questionId
     * @param answerDetail
     * @param userId
     * @param userZn
     * @return
     */
    public ResultResponse submitAnswer(String questionId, String answerDetail, String userId, String userZn);

    /**
     * 查询已答题列表
     * 专家判断
     * @param condition
     * @return
     */
    public ResultResponse getJudgementList(QuestionCondition condition);

    /**
     * 取得指定题目的详情
     * 专家判断
     * @param questionId
     * @return
     */
    public ResultResponse judgementDetail(String questionId);

    /**
     * 更新答题结果状态
     * @param param
     * @return
     */
    public ResultResponse submitJudgementResult(JudgementParam param);


}
