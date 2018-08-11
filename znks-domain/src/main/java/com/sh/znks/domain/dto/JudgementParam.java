package com.sh.znks.domain.dto;

import java.util.Date;

/**
 * Created by wuminggu on 2018/7/16.
 */
public class JudgementParam {
    private String answerId;            //回答id
    private String questionId;          //问题id
    private Integer result;             //结果:0错误、1正确
    private String basis;               //判断理由
    private Date judgeTime;             //判断时间
    private String judgementId;         //判断者id/专家id
    private String judgementZn;         //判断者名称/专家名称
    private Integer status;             //答题状态：0未审核、1审核中、2审核完成、3已结算

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

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
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

    public String getJudgementId() {
        return judgementId;
    }

    public void setJudgementId(String judgementId) {
        this.judgementId = judgementId;
    }

    public String getJudgementZn() {
        return judgementZn;
    }

    public void setJudgementZn(String judgementZn) {
        this.judgementZn = judgementZn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
