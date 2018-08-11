package com.sh.znks.domain.aq;

import java.util.Date;

/**
 * Created by wuminggu on 2018/5/9.
 */
public class Memorandum {
    private Long id;                    //主键、自增id
    private String questionId;          //问题id
    private String answerId;            //回答id
    private String userId;              //用户id/答题者id
    private Integer type;               //收藏类型:0错题、1经典题
    private Integer status;             //状态:0已收藏、1取消收藏
    private Date created;               //创建时间
    private Date modified;              //更新时间

    public Memorandum() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        return "Memorandum{" +
                "id=" + id +
                ", questionId='" + questionId + '\'' +
                ", answerId='" + answerId + '\'' +
                ", userId='" + userId + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
