package com.sh.znks.service.impl.base;

import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.common.base.http.HttpRequestUtils;
import com.sh.znks.common.base.util.JsonUtils;
import com.sh.znks.common.base.util.ParamEditUtils;
import com.sh.znks.common.base.util.RedisKeyConstant;
import com.sh.znks.common.base.util.RedisUtils;
import com.sh.znks.common.base.util.RegisterUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.dao.UserDao;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;
import com.sh.znks.service.base.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuminggu on 2018/5/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${zn.appid}")
    private String appid;
    @Value("${zn.appsecret}")
    private String secret;
    @Value("${zn.wx.url}")
    private String wxurl;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ResultResponse registerGeneralUser(GeneralUser user) {
        try {
            //��ѯ�û��Ƿ��Ѿ�ע��(���ݵ绰����)
            GeneralUser info = userDao.getGeneralUserByPhone(user.getPhoneNumber());
            //�û���ע��
            if (info != null && info.getUserId() != null)
                return new ResultResponse(ResultCodeEnum.ZN_USER_REGISTERED);

            //�������µ��û�λ��
            Long count = userDao.getCountOfGeneralUser(null);
            if (count == null)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);

            //У����������
            if (!validatePassword(user.getPassword(), user.getPasswordEncrypt()))
                return new ResultResponse(ResultCodeEnum.ZN_PWD_ERR);

            //�����༭
            GeneralUser generalUser = RegisterUtils.editParamGU(user,count);

            int res = userDao.insertGeneralUser(generalUser);
            if (res <= Constant.ZERO)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        } catch (Exception e) {
            log.error("L106_register e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }

    @Override
    public ResultResponse registerExpertUser(ExpertUser user) {
        try {
            //��ѯ�û��Ƿ��Ѿ�ע��(���ݵ绰����)
            ExpertUser info = userDao.getExpertUserByPhone(user.getPhoneNumber());
            //�û���ע��
            if (info != null && info.getExpertId() != null)
                return new ResultResponse(ResultCodeEnum.ZN_USER_REGISTERED);

            //�������µ��û�λ��
            Long count = userDao.getCountOfExpertUser(null);
            if (count == null)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);

            //У����������
            if (!validatePassword(user.getPassword(), user.getPasswordEncrypt()))
                return new ResultResponse(ResultCodeEnum.ZN_PWD_ERR);

            //�����༭
            ExpertUser expertUser = RegisterUtils.editParamEX(user,count);

            int res = userDao.insertExpertUser(expertUser);
            if (res <= Constant.ZERO)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        } catch (Exception e) {
            log.error("L106_register e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }

    @Override
    public ResultResponse getExpertUserInfo(String phoneNumber) {
        ExpertUser userInfo = userDao.getExpertUserByPhone(phoneNumber);
        if (userInfo == null)
            return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);

        return new ResultResponse(ResultCodeEnum.ZN_OK, Constant.EXPERT_USER_INFO, userInfo);
    }

    @Override
    public ResultResponse getGeneralUserInfo(String phoneNumber) {
        GeneralUser userInfo = userDao.getGeneralUserByPhone(phoneNumber);
        if (userInfo == null)
            return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);
        return new ResultResponse(ResultCodeEnum.ZN_OK, Constant.GENERAL_USER_INFO, userInfo);
    }

    @Override
    public ResultResponse getSmsExpLoginInfo(String phoneNumber, int identifyCode) {
        //У�������֤���Ƿ�����ݿ��б���һ��
        Integer code = userDao.getIdentifyCode(phoneNumber);
        if (code == null || code != identifyCode)
            return new ResultResponse(ResultCodeEnum.ZN_IDENTIFY_ERR);

        //ȡ�õ�¼��Ϣ
        return getExpertLoginInfo(phoneNumber);
    }

    @Override
    public ResultResponse getPwdExpLoginInfo(String phoneNumber, String passwordEncrypt) {
        //У�������Ƿ�һ��
        ExpertUser user = AuthorHolder.getExpertAuthor();
        if (user == null)
            return new ResultResponse(ResultCodeEnum.ZN_NO_LOGIN);
        if (!passwordEncrypt.equals(user.getPasswordEncrypt()))
            return new ResultResponse(ResultCodeEnum.ZN_PWD_ERR);

        //ȡ�õ�¼��Ϣ
        return getExpertLoginInfo(phoneNumber);
    }

    @Override
    public ResultResponse getSmsGenLoginInfo(String phoneNumber, int identifyCode) {
        //У�������֤���Ƿ�����ݿ��б���һ��
        Integer code = userDao.getIdentifyCode(phoneNumber);
        if (code == null || code != identifyCode)
            return new ResultResponse(ResultCodeEnum.ZN_IDENTIFY_ERR);

        //ȡ�õ�¼��Ϣ
        return getGeneralLoginInfo(phoneNumber);
    }

    @Override
    public ResultResponse getPwdGenLoginInfo(String phoneNumber, String passwordEncrypt) {
        //У�������Ƿ�һ��
        GeneralUser user = AuthorHolder.getGeneralAuthor();
        if (user == null)
            return new ResultResponse(ResultCodeEnum.ZN_NO_LOGIN);
        if (!passwordEncrypt.equals(user.getPasswordEncrypt()))
            return new ResultResponse(ResultCodeEnum.ZN_PWD_ERR);

        //ȡ�õ�¼��Ϣ
        return getGeneralLoginInfo(phoneNumber);
    }

    private ResultResponse getGeneralLoginInfo(String phoneNumber) {
        try {
            GeneralUser user = new GeneralUser();
            user.setPhoneNumber(phoneNumber);
            user.setOnOffLine(Constant.ONE);
            //���û�״̬��Ϊ��1�ѵ�¼
            Integer status = userDao.updateGenInfoByPn(user);
            if (status != Constant.ONE)
                return new ResultResponse(ResultCodeEnum.ZN_UPDATE_FAIL);
        } catch (Exception e) {
            log.error("L135_getGeneralLoginInfo e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK, userDao.getGeneralUserByPhone(phoneNumber));
    }

    private ResultResponse getExpertLoginInfo(String phoneNumber) {
        try {
            ExpertUser user = new ExpertUser();
            user.setPhoneNumber(phoneNumber);
            user.setOnOffLine(Constant.ONE);
            //���û�״̬��Ϊ��1�ѵ�¼
            Integer status = userDao.updateExpInfoByPn(user);
            if (status != Constant.ONE)
                return new ResultResponse(ResultCodeEnum.ZN_UPDATE_FAIL);
        } catch (Exception e) {
            log.error("L135_getExpertUserInfo e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK, userDao.getExpertUserByPhone(phoneNumber));
    }

    private boolean validatePassword(String pwd, String md5Pwd) {
        //У����������
        if (StringUtils.isNotBlank(pwd)) {
            String encryptPwd;
            try {
                String randomStr = pwd;
                MessageDigest md5 = MessageDigest.getInstance(Constant.MD5);
                BASE64Encoder base64en = new BASE64Encoder();
                encryptPwd = base64en.encode(md5.digest(randomStr.getBytes("utf-8")));
                if (!encryptPwd.equals(md5Pwd))
                    return false;
            } catch (NoSuchAlgorithmException e) {
                log.error("L249_validatePassword e:", e);
                return false;
            } catch (UnsupportedEncodingException e) {
                log.error("L252_validatePassword e:", e);
                return false;
            }
        }
        return true;
    }

    @Override
    public ResultResponse logoutGen() {
        try {
            GeneralUser user = new GeneralUser();
            user.setPhoneNumber(AuthorHolder.getGeneralAuthor().getPhoneNumber());
            user.setOnOffLine(Constant.ZERO);
            //���û�״̬��Ϊ��0�˳���¼
            Integer status = userDao.updateGenInfoByPn(user);
            if (status != Constant.ONE)
                return new ResultResponse(ResultCodeEnum.ZN_UPDATE_FAIL);
            //ɾ��redis�������
            redisUtils.del(RedisKeyConstant.KEY_GENERAL_USER_INFO);
        } catch (Exception e) {
            log.error("L301_logoutGen e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }

    @Override
    public ResultResponse logoutExp() {
        try {
            ExpertUser user = new ExpertUser();
            user.setPhoneNumber(AuthorHolder.getExpertAuthor().getPhoneNumber());
            user.setOnOffLine(Constant.ZERO);
            //���û�״̬��Ϊ��0�˳���¼
            Integer status = userDao.updateExpInfoByPn(user);
            if (status != Constant.ONE)
                return new ResultResponse(ResultCodeEnum.ZN_UPDATE_FAIL);
            //ɾ��redis�������
            redisUtils.del(RedisKeyConstant.KEY_EXPERT_USER_INFO);
        } catch (Exception e) {
            log.error("L311_logoutExp e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }

    @Override
    public ResultResponse sendIdentifyCode(String phoneNumber, Integer occasion) {
        int identifyCode = Constant.ZERO;
        try {
            switch (occasion) {
                case 0:
                    //ע�ᷢ����֤��
                    identifyCode = 1234;
                    break;
                case 1:
                    //ѧ����¼������������ʱ
                    GeneralUser resg = userDao.getGeneralUserByPhone(phoneNumber);
                    if (resg != null) {
                        //������֤��
                        identifyCode = 1234;
                    }
                    break;
                case 2:
                    //ר�ҵ�¼������������ʱ
                    ExpertUser rese = userDao.getExpertUserByPhone(phoneNumber);
                    if (rese != null) {
                        //������֤��
                        identifyCode = 1234;
                    }
                    break;
                default:
                    log.error("L348_sendIdentifyCode phoneNumber is {}, occasion is {}", phoneNumber, occasion);
                    break;
            }

            if (identifyCode == Constant.ZERO) {
                log.error("L353_sendIdentifyCode identifyCode is {}", identifyCode);
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
            }

            //����֤��������
            int res = userDao.insertIdentifyCode(phoneNumber, identifyCode);
            if (res <= Constant.ZERO) {
                log.error("L359_sendIdentifyCode phoneNumber is {}, identifyCode is {}", phoneNumber, identifyCode);
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
            }
        } catch (Exception e) {
            log.error("L363_logoutExp e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK, identifyCode);
    }

    @Override
    public ResultResponse resetPassword(String phoneNumber, Integer identifyCode, String password, String passwordEncrypt, Integer platform) {
        try {
            //У�������֤���Ƿ�����ݿ��б���һ��
            Integer code = userDao.getIdentifyCode(phoneNumber);
            if (code == null || identifyCode.intValue() != code.intValue())
                return new ResultResponse(ResultCodeEnum.ZN_IDENTIFY_ERR);
            //У����������
            if (!validatePassword(password, passwordEncrypt))
                return new ResultResponse(ResultCodeEnum.ZN_PWD_ERR);

            int res;
            if (platform == 0) {
                //��������
                GeneralUser user = new GeneralUser();
                user.setPhoneNumber(phoneNumber);
                user.setPassword(password);
                user.setPasswordEncrypt(passwordEncrypt);
                res = userDao.updateGenInfoByPn(user);
            } else if (platform == 1) {
                //��������
                ExpertUser user = new ExpertUser();
                user.setPhoneNumber(phoneNumber);
                user.setPassword(password);
                user.setPasswordEncrypt(passwordEncrypt);
                res = userDao.updateExpInfoByPn(user);
            } else {
                log.error("L389_resetPassword platform is {}", platform);
                return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
            }

            if (res <= Constant.ZERO) {
                log.error("L405_resetPassword phoneNumber is {}, platform is {}", phoneNumber, platform);
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
            }
        } catch (Exception e) {
            log.error("L410_resetPasswordExp e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }

    @Override
    public ResultResponse bindingAccount(String phoneNumber, Integer identifyCode, String accountNo, Integer accountType, Integer platform) {
        try {
            //У�������֤���Ƿ�����ݿ��б���һ��
            Integer code = userDao.getIdentifyCode(phoneNumber);
            if (code == null || identifyCode.intValue() != code.intValue())
                return new ResultResponse(ResultCodeEnum.ZN_IDENTIFY_ERR);

            int res;
            if (platform == 0) {
                //���½����˺�
                GeneralUser user = new GeneralUser();
                user.setPhoneNumber(phoneNumber);
                user.setAccountNo(accountNo);
                user.setAccountType(accountType);
                res = userDao.updateGenInfoByPn(user);
            } else if (platform == 1) {
                //���½����˺�
                ExpertUser user = new ExpertUser();
                user.setPhoneNumber(phoneNumber);
                user.setAccountNo(accountNo);
                user.setAccountType(accountType);
                res = userDao.updateExpInfoByPn(user);
            } else {
                log.error("L452_bindingAccount platform is {}", platform);
                return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
            }

            if (res <= Constant.ZERO) {
                log.error("L457_bindingAccount phoneNumber is {}, platform is {}", phoneNumber, platform);
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
            }
        } catch (Exception e) {
            log.error("L462_bindingAccountExp e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }

    @Override
    public ResultResponse updateGenInfos(GeneralUser user) {
        try {
            //�༭����
            GeneralUser updateUser = ParamEditUtils.editUpdateGenInfos(user);

            int res = userDao.updateGenInfoByPn(updateUser);
            if (res <= Constant.ZERO) {
                log.error("L505_updateGenInfos updateUser is {}", JsonUtils.toJson(updateUser));
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
            }
        } catch (Exception e) {
            log.error("L510_updateGenInfosExp e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }

    @Override
    public ResultResponse updateExpInfos(ExpertUser user) {
        try {
            //�༭����
            ExpertUser updateUser = ParamEditUtils.editUpdateExpInfos(user);

            int res = userDao.updateExpInfoByPn(updateUser);
            if (res <= Constant.ZERO) {
                log.error("L553_updateExpInfos updateUser is {}", JsonUtils.toJson(updateUser));
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
            }
        } catch (Exception e) {
            log.error("L538_updateExpInfosExp e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }

    @Override
    public ResultResponse getxAuthorizationGenLoginInfo(String authorizationCode) {
        Map<String, Object> resultMap = new HashMap<>();
        // ȡ������΢�ŵ�¼ƾ֤У��ӿڵ�url
        String wxUrl = ParamEditUtils.getWxUrl(wxurl, appid, secret, authorizationCode);
        try {
            Map resMap = HttpRequestUtils.httpGet(wxUrl);
//            Integer errcode = (Integer) resMap.get("errcode");
//            String errmsg = (String) resMap.get("errmsg");

            String openid = (String) resMap.get("openid");
            String sessionKey = (String) resMap.get("session_key");
            String unionId = (String) resMap.get("unionid");
//            openid = "wx3f42776e16a6d94b";
//            sessionKey = "7a4c32def6f73a1e229b05dd1894dbb3";
//            unionId = "zn123456789";
            if (StringUtils.isBlank(openid) || StringUtils.isBlank(sessionKey)) {
                log.error("L461_getxAuthorizationGenLoginInfo res is {}", JsonUtils.toJson(resMap));
                return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
            }

            boolean isRegistered = false;
            WxUser wxUser = new WxUser();
            if (StringUtils.isNotBlank(unionId)) {
                //��ѯ�Ƿ���ע���û���Ϣ
                wxUser = userDao.getWxUserByUnionid(unionId);
                if (StringUtils.isNotBlank(wxUser.getNickName())) {
                    isRegistered = true;
                }
            }
            //��openid��sessionKey�ŵ�AuthHolder��
            wxUser.setOpenid(openid);
            wxUser.setSessionKey(sessionKey);
            AuthorHolder.setWxAuthor(wxUser);

            //ȡ����Ȩ��¼token
            String token = ParamEditUtils.getToken(openid, sessionKey);

            resultMap.put("token", token);
            resultMap.put("isRegistered", isRegistered);
        } catch (Exception e) {
            log.error("L485_getxAuthorizationGenLoginInfoExp e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK, resultMap);
    }

    @Override
    public ResultResponse registerWxUser(WxUser user) {
        try {
            //��ѯ�û��Ƿ��Ѿ�ע��(����UnionId)
            WxUser wxUser = userDao.getWxUserByUnionid(user.getUnionId());
            //�û���ע��
            if (wxUser != null && StringUtils.isNotBlank(wxUser.getNickName()))
                return new ResultResponse(ResultCodeEnum.ZN_USER_REGISTERED);

            int res = userDao.insertWxUser(user);
            if (res <= Constant.ZERO)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        } catch (Exception e) {
            log.error("L505_register e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }
}
