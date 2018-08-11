package com.sh.znks.web.controller.business;

import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.dto.JudgementParam;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.dto.QuestionParam;
import com.sh.znks.service.business.QuestionService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuminggu on 2018/6/14.
 */
@Controller
@RequestMapping("/business")
public class TaskInfoController {
    private final static Logger log = LoggerFactory.getLogger(TaskInfoController.class);

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/deployQuestion", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse deployQuestion(QuestionParam param) {
        //校验必填项
        if (param == null
                || StringUtils.isBlank(param.getQuestionDescribe())
                || StringUtils.isBlank(param.getStandardAnswer())
                || StringUtils.isBlank(param.getExpertId())) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return questionService.deployQuestion(param);
    }

    @RequestMapping(value = "/getQuestionList", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse getQuestionList(QuestionCondition condition) {
        //校验必填项(无)
        return questionService.getQuestionList(condition);
    }

    @RequestMapping(value = "/questionDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse questionDetail(String questionId) {
        //校验必填项
        if (StringUtils.isBlank(questionId))
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);

        return questionService.questionDetail(questionId);
    }

    @RequestMapping(value = "/submitAnswer", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse submitAnswer(String questionId, String answerDetail, String userId, String userZn) {
        //校验必填项
        if (StringUtils.isBlank(questionId)
                || StringUtils.isBlank(answerDetail)
                || StringUtils.isBlank(userId)
                || StringUtils.isBlank(userZn)) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return questionService.submitAnswer(questionId,answerDetail,userId,userZn);
    }

    @RequestMapping(value = "/getJudgementList", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse getJudgementList(QuestionCondition condition) {
        //校验必填项(无)
        return questionService.getJudgementList(condition);
    }

    @RequestMapping(value = "/judgementDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse judgementDetail(String questionId) {
        //校验必填项
        if (StringUtils.isBlank(questionId))
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);

        return questionService.judgementDetail(questionId);
    }

    @RequestMapping(value = "/submitJudgementResult", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse submitJudgementResult(JudgementParam param) {
        //校验必填项
        if (param == null
                || StringUtils.isBlank(param.getQuestionId())
                || StringUtils.isBlank(param.getAnswerId())
                || param.getResult() == null) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return questionService.submitJudgementResult(param);
    }



}
