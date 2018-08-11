package com.sh.znks.service.base;

import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.register.User;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;

/**
 * Created by wuminggu on 2018/5/9.
 */
public interface UserService {
    /**
     * ע��ѧ����Ϣ
     * @param user
     * @return
     */
    ResultResponse registerGeneralUser(GeneralUser user);

    /**
     * ע��ר����Ϣ
     * @param user
     * @return
     */
    ResultResponse registerExpertUser(ExpertUser user);

    /**
     * ���ݵ绰�����ѯ�û���Ϣ
     * @param phoneNumber
     * @return
     */
    ResultResponse getGeneralUserInfo(String phoneNumber);

    /**
     * ���ݵ绰�����ѯר����Ϣ
     * @param phoneNumber
     * @return
     */
    ResultResponse getExpertUserInfo(String phoneNumber);

    /**
     * ȡ��ר�ҵ�¼��ķ���ֵ��Ϣ(��֤���¼)
     * @param phoneNumber
     * @param identifyCode
     * @return
     */
    ResultResponse getSmsExpLoginInfo(String phoneNumber, int identifyCode);

    /**
     * ȡ�õ�¼��ķ���ֵ��Ϣ(�����¼)
     * @param phoneNumber
     * @param passwordEncrypt
     * @return
     */
    ResultResponse getPwdExpLoginInfo(String phoneNumber, String passwordEncrypt);

    /**
     * ȡ��ѧ����¼��ķ���ֵ��Ϣ(��֤���¼)
     * @param phoneNumber
     * @param identifyCode
     * @return
     */
    ResultResponse getSmsGenLoginInfo(String phoneNumber, int identifyCode);

    /**
     * ȡ�õ�¼��ķ���ֵ��Ϣ(�����¼)
     * @param phoneNumber
     * @param passwordEncrypt
     * @return
     */
    ResultResponse getPwdGenLoginInfo(String phoneNumber, String passwordEncrypt);

    /**
     * �˳���¼(ѧ��)
     * @return
     */
    ResultResponse logoutGen();

    /**
     * �˳���¼(ר��)
     * @return
     */
    ResultResponse logoutExp();

    /**
     * ���Ͷ�����֤��
     * @param phoneNumber
     * @param occasion
     * @return
     */
    ResultResponse sendIdentifyCode(String phoneNumber, Integer occasion);

    /**
     * ��������
     * @param phoneNumber
     * @param identifyCode
     * @param password
     * @param passwordEncrypt
     * @return
     */
    ResultResponse resetPassword(String phoneNumber, Integer identifyCode, String password, String passwordEncrypt, Integer platform);

    /**
     * �󶨽����˺ţ��޸ģ�
     * @param phoneNumber
     * @param identifyCode
     * @param accountNo
     * @param accountType
     * @return
     */
    ResultResponse bindingAccount(String phoneNumber, Integer identifyCode, String accountNo, Integer accountType, Integer platform);

    /**
     * ����ѧ��������Ϣ
     * @param user
     * @return
     */
    ResultResponse updateGenInfos(GeneralUser user);

    /**
     * ����ר�һ�����Ϣ
     * @param user
     * @return
     */
    ResultResponse updateExpInfos(ExpertUser user);
}
