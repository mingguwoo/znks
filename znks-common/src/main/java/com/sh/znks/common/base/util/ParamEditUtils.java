package com.sh.znks.common.base.util;

import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.AnswerParam;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.dto.QuestionParam;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wuminggu on 2018/7/5.
 */
public class ParamEditUtils {

    /**
     * 更新学生信息参数编辑
     */
    public static GeneralUser editUpdateGenInfos(GeneralUser user) {
        //编辑参数
        GeneralUser updateUser = new GeneralUser();
        updateUser.setPhoneNumber(user.getPhoneNumber());
        if (StringUtils.isNotBlank(user.getNickName()))
            updateUser.setNickName(user.getNickName());
        if (user.getAge() != null && user.getAge() > 0)
            updateUser.setAge(user.getAge());
        if (StringUtils.isNotBlank(user.getHeadImg()))
            updateUser.setHeadImg(user.getHeadImg());
        if (StringUtils.isNotBlank(user.getSignature()))
            updateUser.setSignature(user.getSignature());
        if (StringUtils.isNotBlank(user.getUserDescribe()))
            updateUser.setUserDescribe(user.getUserDescribe());
        if (user.getGrade() != null && user.getGrade() > 0)
            updateUser.setGrade(user.getGrade());
        if (StringUtils.isNotBlank(user.getCompany()))
            updateUser.setCompany(user.getCompany());
        if (StringUtils.isNotBlank(user.getProvince()))
            updateUser.setProvince(user.getProvince());
        if (StringUtils.isNotBlank(user.getCity()))
            updateUser.setCity(user.getCity());
        if (StringUtils.isNotBlank(user.getLongitude()))
            updateUser.setLongitude(user.getLongitude());
        if (StringUtils.isNotBlank(user.getLatitude()))
            updateUser.setLatitude(user.getLatitude());
        if (StringUtils.isNotBlank(user.getAdditional()))
            updateUser.setAdditional(user.getAdditional());

        return updateUser;
    }

    /**
     * 更新专家信息参数编辑
     */
    public static ExpertUser editUpdateExpInfos(ExpertUser user) {
        //编辑参数
        ExpertUser updateUser = new ExpertUser();
        updateUser.setPhoneNumber(user.getPhoneNumber());
        if (StringUtils.isNotBlank(user.getExpertNick()))
            updateUser.setExpertNick(user.getExpertNick());
        if (StringUtils.isNotBlank(user.getHeadImg()))
            updateUser.setHeadImg(user.getHeadImg());
        if (StringUtils.isNotBlank(user.getSignature()))
            updateUser.setSignature(user.getSignature());
        if (StringUtils.isNotBlank(user.getUserDescribe()))
            updateUser.setUserDescribe(user.getUserDescribe());
        if (StringUtils.isNotBlank(user.getProvince()))
            updateUser.setProvince(user.getProvince());
        if (StringUtils.isNotBlank(user.getCity()))
            updateUser.setCity(user.getCity());
        if (StringUtils.isNotBlank(user.getLongitude()))
            updateUser.setLongitude(user.getLongitude());
        if (StringUtils.isNotBlank(user.getLatitude()))
            updateUser.setLatitude(user.getLatitude());
        if (StringUtils.isNotBlank(user.getAdditional()))
            updateUser.setAdditional(user.getAdditional());

        return updateUser;
    }

    /**
     * 插入题目信息的参数编辑
     */
    public static Question editDeployQuestion(QuestionParam param, String questionId) {
        //编辑参数
        Question question = new Question();
        if (StringUtils.isEmpty(questionId))
            questionId = "1";
        else
            questionId = (Integer.parseInt(questionId) + 1) + Constant.BLANK;
        question.setQuestionId(questionId);
        //题目状态:0未通过、1通过、2审核中
        question.setStatus(Constant.TWO);
        if (param.getGrade() != null && Constant.GRADEK12.contains(param.getGrade()))
            question.setGrade(param.getGrade());
        if (param.getSubjectId() != null && Constant.SUBJECT.contains(param.getSubjectId()))
            question.setSubjectId(param.getSubjectId());
        if (param.getQuestionType() != null && Constant.QUESTIONTYPES.contains(param.getQuestionType()))
            question.setQuestionType(param.getQuestionType());
        if (StringUtils.isNotBlank(param.getTips()))
            question.setTips(param.getTips());
        if (param.getQuestionValue() != null && Constant.BLOOD.contains(param.getQuestionValue()))
            question.setQuestionValue(param.getQuestionValue());
        else
            question.setQuestionValue(Constant.ZERO);
        if (param.getDifficultyLevel() != null && Constant.DIFFICULTY_DEGREE.contains(param.getDifficultyLevel()))
            question.setDifficultyLevel(param.getDifficultyLevel());
        else
            question.setDifficultyLevel(Constant.ONE);
        if (StringUtils.isNotBlank(param.getExpertZn()))
            question.setExpertZn(param.getExpertZn());
        if (StringUtils.isNotBlank(param.getKnowledge()))
            question.setKnowledge(param.getKnowledge());
        question.setQuestionDescribe(param.getQuestionDescribe());
        question.setStandardAnswer(param.getStandardAnswer());
        question.setExpertId(param.getExpertId());
        question.setOption1(param.getOption1());
        question.setOption2(param.getOption2());
        question.setOption3(param.getOption3());
        question.setOption4(param.getOption4());
        question.setOption5(param.getOption5());
        question.setOption6(param.getOption6());
        question.setOption7(param.getOption7());
        question.setOption8(param.getOption8());
        question.setOption9(param.getOption9());
        question.setOption10(param.getOption10());

        return question;
    }

    /**
     * 编辑查询题目的入参
     */
    public static QuestionCondition editGetQuestionList(QuestionCondition condition) {
        QuestionCondition qc = new QuestionCondition();
        //编辑参数
        if (condition != null) {
            if (Constant.GRADEK12.contains(condition.getGrade()))
                qc.setGrade(condition.getGrade());
            if (Constant.SUBJECT.contains(condition.getSubjectId()))
                qc.setSubjectId(condition.getSubjectId());
            if (StringUtils.isNotBlank(condition.getQuestionDescribe()) && condition.getQuestionDescribe().length() > 0)
                qc.setQuestionDescribe(condition.getQuestionDescribe());
            if (Constant.QUESTIONTYPES.contains(condition.getQuestionType()))
                qc.setQuestionType(condition.getQuestionType());
            if (Constant.BLOOD.contains(condition.getQuestionValue()))
                qc.setQuestionValue(condition.getQuestionValue());
            if (Constant.DIFFICULTY_DEGREE.contains(condition.getDifficultyLevel()))
                qc.setDifficultyLevel(condition.getDifficultyLevel());
            if (condition.getQuestionId() != null)
                qc.setQuestionId(condition.getQuestionId());
            if (StringUtils.isNotBlank(condition.getExpertZn()))
                qc.setExpertZn(condition.getExpertZn());
            if (StringUtils.isNotBlank(condition.getExpertId()))
                qc.setExpertId(condition.getExpertId());
        }
        qc.setCreated(condition.getCreated());
        qc.setModified(condition.getModified());
        qc.setStatus(condition.getStatus());
        qc.setStart(condition.getStart());
        qc.setSize(condition.getSize());
        qc.setTaskStatus(condition.getTaskStatus());

        return qc;
    }

    /**
     * 插入答案信息的参数编辑
     */
    public static Answer editDeployAnswer(AnswerParam param, Integer answerId) {
        //编辑参数
        Answer an = new Answer();
        an.setAnswerId(answerId+ Constant.BLANK);
        an.setUserId(param.getUserId());
        an.setUserZn(param.getUserZn());
        an.setQuestionId(param.getQuestionId());
        an.setAnswerDetail(param.getAnswerDetail());
        an.setStatus(Constant.ONE);

        return an;
    }

    /**
     * 生成微信登录凭证校验接口url
     * @param authorizationCode
     * @return
     */
    public static String getWxUrl(String wxurl, String appid, String secret, String authorizationCode) {
        StringBuilder wxUrlStr = new StringBuilder();
        wxUrlStr.append(wxurl);
        wxUrlStr.append("appid=");
        wxUrlStr.append(appid);
        wxUrlStr.append("&secret=");
        wxUrlStr.append(secret);
        wxUrlStr.append("&js_code=");
        wxUrlStr.append(authorizationCode);
        wxUrlStr.append("&grant_type=authorization_code");

        return wxUrlStr.toString();
    }

    /**
     * 获取用户登录授权token
     * @param openid
     * @param sessionKey
     * @return
     */
    public static String getToken(String openid, String sessionKey) {
        Date now = new Date();
        String timeStamp = String.valueOf(now.getTime());
        String token = openid + "_" + sessionKey + "_" + timeStamp;
        return token;
    }

    public static boolean checkTokenAvailability(String token) {
        boolean isAvailable = false;
        if (StringUtils.isBlank(token)) {
            return isAvailable;
        }

        String[] tokenParam = token.split("_");
        String openid = tokenParam[0];
        String sessionKey = tokenParam[1];
        if (StringUtils.isBlank(openid) || StringUtils.isBlank(sessionKey)) {
            return isAvailable;
        }

        WxUser wxUser = AuthorHolder.getWxAuthor();
        if (wxUser != null && openid.equals(wxUser.getOpenid()) && sessionKey.equals(wxUser.getSessionKey())) {
            isAvailable = true;
        }

        return isAvailable;
    }
}
