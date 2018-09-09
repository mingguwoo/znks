package com.sh.znks.common.base;

import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;

/**
 * Created by wuminggu on 2018/6/4.
 */
public class AuthorHolder {
    //全局使用的用户信息设置
    public static ThreadLocal<GeneralUser> GENERAHOLDER = new ThreadLocal<GeneralUser>();
    public static ThreadLocal<ExpertUser> EXPERTHOLDER = new ThreadLocal<ExpertUser>();
    public static ThreadLocal<WxUser> WXHOLDER = new ThreadLocal<WxUser>();

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

    //微信授权用户
    public static WxUser getWxAuthor() {
        return WXHOLDER.get();
    }
    public static void setWxAuthor(WxUser user) {
        WXHOLDER.set(user);
    }
}
