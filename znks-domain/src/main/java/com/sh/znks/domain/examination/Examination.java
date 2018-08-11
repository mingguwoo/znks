package com.sh.znks.domain.examination;

import java.util.Date;
import java.util.List;

import javax.jws.Oneway;

/**
 * Created by wuminggu on 2018/5/9.
 */
public class Examination {
    private Long id;                    //主键、自增id
    private String testId;              //试卷id
    private Integer totalScore;         //总分
    private Integer totalQuestions;     //题目数量
    private Integer testTime;           //考试时间（分钟）
    private String questionList;        //题目List(String)
    private List<Long> questions;       //题目List
    private String scoreList;           //分值List(String)
    private List<Integer> scores;       //分值List
    private Integer status;             //试卷状态:0未通过、1通过、2审核中
    private Date created;               //创建时间
    private Date modified;              //更新时间

    public Examination() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Integer getTestTime() {
        return testTime;
    }

    public void setTestTime(Integer testTime) {
        this.testTime = testTime;
    }

    public String getQuestionList() {
        return questionList;
    }

    public void setQuestionList(String questionList) {
        this.questionList = questionList;
    }

    public List<Long> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Long> questions) {
        this.questions = questions;
    }

    public String getScoreList() {
        return scoreList;
    }

    public void setScoreList(String scoreList) {
        this.scoreList = scoreList;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Examination{" +
                "id=" + id +
                ", testId='" + testId + '\'' +
                ", totalScore=" + totalScore +
                ", totalQuestions=" + totalQuestions +
                ", testTime=" + testTime +
                ", questionList='" + questionList + '\'' +
                ", questions=" + questions +
                ", scoreList='" + scoreList + '\'' +
                ", scores=" + scores +
                ", status=" + status +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
