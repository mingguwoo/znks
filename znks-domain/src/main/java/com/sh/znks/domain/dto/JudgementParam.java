package com.sh.znks.domain.dto;

import java.util.Date;

/**
 * Created by wuminggu on 2018/7/16.
 */
public class JudgementParam {
    private String answerId;            //�ش�id
    private String questionId;          //����id
    private Integer result;             //���:0����1��ȷ
    private String basis;               //�ж�����
    private Date judgeTime;             //�ж�ʱ��
    private String judgementId;         //�ж���id/ר��id
    private String judgementZn;         //�ж�������/ר������
    private Integer status;             //����״̬��0δ��ˡ�1����С�2�����ɡ�3�ѽ���

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
