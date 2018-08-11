package com.sh.znks.common.base;

import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;

/**
 * Created by wuminggu on 2018/6/4.
 */
public class AuthorHolder {
    //全局使用的用户信息设置
    public static ThreadLocal<GeneralUser> GENERAHOLDER = new ThreadLocal<GeneralUser>();
    public static ThreadLocal<ExpertUser> EXPERTHOLDER = new ThreadLocal<ExpertUser>();

    //学生
    public static GeneralUser getGeneralAuthor() {
        return GENERAHOLDER.get();
    }
    public static void setGeneralAuthor(GeneralUser user) {
        GENERAHOLDER.set(user);
    }

    //专家
    public static ExpertUser getExpertAuthor() {
        return EXPERTHOLDER.get();
    }
    public static void setExpertAuthor(ExpertUser user) {
        EXPERTHOLDER.set(user);
    }
}
