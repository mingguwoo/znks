package com.sh.znks.domain.user;

import com.sh.znks.domain.register.User;

/**
 * Created by wuminggu on 2018/5/8.
 */
public class GeneralUser extends User {

    private String userId;              //用户id
    private String nickName;            //用户昵称
    private Integer sex;                //性别：0女、1男
    private Integer age;                //年龄
    private Integer userType;           //用户类型:0社会人、1学生、2老师、3其他
    private Integer grade;              //年级（K1-K12）
    private String company;             //用户所属单位
    private Integer rankings;           //排位:8宇宙最强、7战圣、6战神、5英雄、4带头大哥、3二当家、2三弟、1小弟、0无名氏

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getRankings() {
        return rankings;
    }

    public void setRankings(Integer rankings) {
        this.rankings = rankings;
    }
}
