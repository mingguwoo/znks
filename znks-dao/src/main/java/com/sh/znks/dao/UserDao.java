package com.sh.znks.dao;

import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;

import java.util.List;

/**
 * Created by wuminggu on 2018/5/9.
 */
public interface UserDao {
    /**
     * ����ר�Ҹ�����Ϣ
     * @param expertUser
     * @return
     */
    int insertExpertUser(ExpertUser expertUser);

    /**
     * ����ר��id��ѯר����Ϣ����
     * @param suList
     * @return
     */
    List<ExpertUser> getExpertUserByExpertId(List<String> suList);

    /**
     * ȡ��ר������
     * @param su
     * @return
     */
    Long getCountOfExpertUser(ExpertUser su);

    /**
     * ���ݵ绰�����ѯר����Ϣ
     * @param pn
     * @return
     */
    ExpertUser getExpertUserByPhone(String pn);

    /**
     * ���ݵ绰�����ѯ�û���Ϣ
     * @param pn
     * @return
     */
    GeneralUser getGeneralUserByPhone(String pn);

    /**
     * ȡ����֤��
     * @param phoneNumber
     * @return
     */
    Integer getIdentifyCode(String phoneNumber);

    /**
     * ȡ��ѧ������
     * @param su
     * @return
     */
    Long getCountOfGeneralUser(GeneralUser su);

    /**
     * ����ר�Ҹ�����Ϣ
     * @param generalUser
     * @return
     */
    int insertGeneralUser(GeneralUser generalUser);

    /**
     * ����֤����뵽����
     * @param phoneNumber
     * @param IdentifyCode
     * @return
     */
    int insertIdentifyCode(String phoneNumber, int IdentifyCode);

    /**
     * ���ݵ绰�������ѧ����Ϣ
     * @param user
     * @return
     */
    int updateGenInfoByPn(GeneralUser user);

    /**
     * ���ݵ绰�������ר����Ϣ
     * @param user
     * @return
     */
    int updateExpInfoByPn(ExpertUser user);
}
