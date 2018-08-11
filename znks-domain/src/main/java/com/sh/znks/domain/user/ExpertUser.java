package com.sh.znks.domain.user;

import com.sh.znks.domain.register.User;

/**
 * Created by wuminggu on 2018/5/8.
 */
public class ExpertUser extends User {
    private String expertId;            //专家id
    private String expertNick;          //用户昵称
    private Integer expertType;         //专家类型：0普通老师、1名师、2专家、3权威专家
    private Integer level;              //权威等级：1段、2段、3段、4段、5段

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getExpertNick() {
        return expertNick;
    }

    public void setExpertNick(String expertNick) {
        this.expertNick = expertNick;
    }

    public Integer getExpertType() {
        return expertType;
    }

    public void setExpertType(Integer expertType) {
        this.expertType = expertType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
