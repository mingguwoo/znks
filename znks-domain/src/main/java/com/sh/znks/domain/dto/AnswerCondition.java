package com.sh.znks.domain.dto;

import java.util.Date;

/**
 * Created by wuminggu on 2018/7/13.
 */
public class AnswerCondition {
    private String answerId;            //回答id
    private String questionId;          //问题id
    private String userId;              //用户id/答题者id
    private String userZn;              //用户zn/答题者zn
    private String answerDetail;        //提交答案描述
    private Integer result;             //结果:0错误、1正确
    private String expertId;            //出题者id/专家id
    private String expertZn;            //出题者名称/专家名称
    private String basis;               //判断理由
    private Date judgeTime;             //判断时间
    private Date created;               //创建时间
    private Date modified;              //更新时间
    private Integer status;             //答题状态：0未审核、1审核中、2审核完成、3已结算
    private Integer start = 0;          //查询开始条数（默认:0开始）
    private Integer size = 10;          //查询限制条数（默认:10条）

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserZn() {
        return userZn;
    }

    public void setUserZn(String userZn) {
        this.userZn = userZn;
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getExpertZn() {
        return expertZn;
    }

    public void setExpertZn(String expertZn) {
        this.expertZn = expertZn;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public Date getJudgeTime() {
        return judgeTime;
    }

    public void setJudgeTime(Date judgeTime) {
        this.judgeTime = judgeTime;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
