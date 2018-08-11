package com.sh.znks.common.base;

import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;

/**
 * Created by wuminggu on 2018/6/4.
 */
public class AuthorHolder {
    //ȫ��ʹ�õ��û���Ϣ����
    public static ThreadLocal<GeneralUser> GENERAHOLDER = new ThreadLocal<GeneralUser>();
    public static ThreadLocal<ExpertUser> EXPERTHOLDER = new ThreadLocal<ExpertUser>();

    //ѧ��
    public static GeneralUser getGeneralAuthor() {
        return GENERAHOLDER.get();
    }
    public static void setGeneralAuthor(GeneralUser user) {
        GENERAHOLDER.set(user);
    }

    //ר��
    public static ExpertUser getExpertAuthor() {
        return EXPERTHOLDER.get();
    }
    public static void setExpertAuthor(ExpertUser user) {
        EXPERTHOLDER.set(user);
    }
}
