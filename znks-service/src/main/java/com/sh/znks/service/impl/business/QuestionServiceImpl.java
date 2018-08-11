package com.sh.znks.service.impl.business;

import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.common.base.util.JsonUtils;
import com.sh.znks.common.base.util.ParamEditUtils;
import com.sh.znks.common.base.util.RedisKeyConstant;
import com.sh.znks.common.base.util.RedisUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.dao.AnswerDao;
import com.sh.znks.dao.QuestionDao;
import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.AnswerCondition;
import com.sh.znks.domain.dto.JudgementParam;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.dto.QuestionParam;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.service.business.QuestionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by wuminggu on 2018/7/5.
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    private final static Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private RedisUtils redisUtils;

    private List<Question> questionList = new ArrayList<Question>();

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public ResultResponse deployQuestion(QuestionParam param) {
        try {
            //查询是否已存在同一题目
            QuestionCondition condition = new QuestionCondition();
            condition.setQuestionDescribe(param.getQuestionDescribe());
            condition.setSize(1);
            List<Question> resQ = questionDao.getQuestionInfos(condition);
            if (CollectionUtils.isNotEmpty(resQ) || resQ.size() > 0)
                return new ResultResponse(ResultCodeEnum.ZN_QUESTION_EXIST);

            //编辑参数
            String questionId = questionDao.getQuestionIdMax();
            Question question = ParamEditUtils.editDeployQuestion(param,questionId);

            int res = questionDao.insertQuestion(question);
            if (res <= Constant.ZERO)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        } catch (Exception e) {
            log.error("L65_deployQuestion e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }

    @Override
    public ResultResponse getQuestionList(QuestionCondition condition) {
        try {
//            List<Question> resRedis = (List<Question>) redisUtils.get(RedisKeyConstant.KEY_QUESTIONS_LIST);
//            log.error("L65_getQuestionList resRedis is{}", JsonUtils.toJson(resRedis));
//            if (CollectionUtils.isEmpty(resRedis)) {
            //查询条件编辑
            QuestionCondition qc = ParamEditUtils.editGetQuestionList(condition);
            List<Question> resQueList = questionDao.getQuestionInfos(qc);
            if (CollectionUtils.isEmpty(resQueList))
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);

            List<String> questionIdList = new ArrayList<String>();
            for (Question question : resQueList)
                questionIdList.add(question.getQuestionId());
            //放在redis中保存1s,一秒内的请求返回值不变(即使查询条件变化也是一样)
//            redisUtils.set(RedisKeyConstant.KEY_QUESTIONS_LIST, resList, Constant.TEN);
            //可以用定时任务不断的刷数据,将返回结果直接放在内存中,这样每次请求都能拿到最新的
//                resRedis = resList;
//            }

            //返回值编辑
            List<Question> resultList = new ArrayList<Question>();
            if (qc.getTaskStatus() != null) {
                GeneralUser user = AuthorHolder.getGeneralAuthor();
                if (user == null)
                    return new ResultResponse(ResultCodeEnum.ZN_NO_LOGIN);

                List<Answer> resAnsList = answerDao.getAnswerInfoByQueList(user.getUserId(), user.getZn(), questionIdList);
                List<String> questionIdByAnswerList = new ArrayList<String>();
                if (CollectionUtils.isNotEmpty(resAnsList)) {
                    for (Answer aa : resAnsList)
                        questionIdByAnswerList.add(aa.getQuestionId());
                }

                for (Question ql : resQueList) {
                    //未做题筛选
                    if (qc.getTaskStatus() == 0) {
                        if (CollectionUtils.isNotEmpty(questionIdByAnswerList) && questionIdByAnswerList.contains(ql.getQuestionId()))
                            continue;
                    }
                    //已做题筛选
                    if (qc.getTaskStatus() == 1) {
                        if (CollectionUtils.isNotEmpty(questionIdByAnswerList) && !questionIdByAnswerList.contains(ql.getQuestionId()))
                            continue;
                    }
                    resultList.add(ql);
                }
            } else {
                resultList = resQueList;
            }

            // 全局变量
            this.setQuestionList(resultList);

            return new ResultResponse(ResultCodeEnum.ZN_OK, resultList);
        } catch (Exception e) {
            log.error("L140_getQuestionList e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse questionDetail(String questionId) {
        try {
            GeneralUser user = AuthorHolder.getGeneralAuthor();
            if (user == null)
                return new ResultResponse(ResultCodeEnum.ZN_NO_LOGIN);
            //查询条件编辑
            QuestionCondition qc = new QuestionCondition();
            qc.setQuestionId(questionId);
            List<Question> queList = questionDao.getQuestionInfos(qc);
            if (CollectionUtils.isEmpty(queList))
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);

            //返回值编辑，拿到作者信息查询对应的提交答案记录List
            AnswerCondition ac = new AnswerCondition();
            ac.setQuestionId(questionId);
            ac.setUserId(user.getUserId());
            ac.setUserZn(user.getZn());

            List<Answer> ansList = answerDao.getAnswerInfos(ac);

            Map<String, Object> resmap = new HashedMap();
            resmap.put("questionInfo", queList.get(0));
            resmap.put("answerInfo", ansList);

            return new ResultResponse(ResultCodeEnum.ZN_OK, resmap);
        } catch (Exception e) {
            log.error("L114_questionDetail e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse submitAnswer(String questionId, String answerDetail, String userId, String userZn) {
        //参数校验
        GeneralUser user = AuthorHolder.getGeneralAuthor();
        if (user == null)
            return new ResultResponse(ResultCodeEnum.ZN_NO_LOGIN);

        if (!userId.equals(user.getUserId()))
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        try {
            //参数编辑
            String answerId = answerDao.getAnswerIdMax();
            Answer an = ParamEditUtils.editDeployAnswer(questionId, answerDetail, userId, userZn, answerId);

            int res = answerDao.insertAnswer(an);
            if (res <= Constant.ZERO)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
            return new ResultResponse(ResultCodeEnum.ZN_OK);
        } catch (Exception e) {
            log.error("L158_submitAnswer e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse getJudgementList(QuestionCondition condition) {
        //查询条件编辑
        AnswerCondition ac = new AnswerCondition();
        ac.setExpertId(condition.getExpertId());
        ac.setExpertZn(condition.getExpertZn());
        ac.setStatus(Constant.ONE);
        ac.setStart(condition.getStart());
        ac.setSize(condition.getSize());

        try {
            List<Answer> ansList = answerDao.getAnswerInfos(ac);
            if (CollectionUtils.isEmpty(ansList))
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);

            Set<String> idSet = new HashSet<>();
            for (Answer an : ansList)
                idSet.add(an.getQuestionId());
            List<String> idList = new ArrayList<String>(idSet);

            List<Question> resultList = questionDao.getQuestionInfoByQueList(null, null, idList);
            return new ResultResponse(ResultCodeEnum.ZN_OK, resultList);
        } catch (Exception e) {
            log.error("L221_getJudgementList e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse judgementDetail(String questionId) {
        try {
            //查询条件编辑
            QuestionCondition qc = new QuestionCondition();
            qc.setQuestionId(questionId);
            List<Question> queList = questionDao.getQuestionInfos(qc);
            if (CollectionUtils.isEmpty(queList))
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);

            //返回值编辑，拿到作者信息查询对应的提交答案记录List
            AnswerCondition ac = new AnswerCondition();
            ac.setQuestionId(questionId);
            List<Answer> ansList = answerDao.getAnswerInfos(ac);

            Map<String, Object> resmap = new HashedMap();
            resmap.put("questionInfo", queList.get(0));
            resmap.put("answerInfo", ansList);

            return new ResultResponse(ResultCodeEnum.ZN_OK, resmap);
        } catch (Exception e) {
            log.error("L114_judgementDetail e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse submitJudgementResult(JudgementParam param) {
        ExpertUser user = AuthorHolder.getExpertAuthor();
        if (user == null)
            return new ResultResponse(ResultCodeEnum.ZN_NO_LOGIN);

        //参数编辑
        param.setJudgementId(user.getExpertId());
        param.setJudgementZn(user.getZn());
        param.setJudgeTime(new Date());
        param.setStatus(Constant.TWO);

        try {
            int res = answerDao.updateAnswerResult(param);
            if (res <= Constant.ZERO)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);

            return new ResultResponse(ResultCodeEnum.ZN_OK);
        } catch (Exception e) {
            log.error("L270_submitJudgementResult e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

}
