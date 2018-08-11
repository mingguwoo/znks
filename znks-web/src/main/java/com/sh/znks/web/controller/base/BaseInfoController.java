package com.sh.znks.web.controller.base;

import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.service.base.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuminggu on 2018/6/19.
 */
@Controller
@RequestMapping("/base")
public class BaseInfoController {
    private final static Logger log = LoggerFactory.getLogger(BaseInfoController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/sendIdentifyCode", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse sendIdentifyCode(String phoneNumber, Integer occasion) {
        //校验必填项
        if (StringUtils.isBlank(phoneNumber)
                || occasion == null) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return userService.sendIdentifyCode(phoneNumber, occasion);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse resetPassword(String phoneNumber, Integer identifyCode, String password, String passwordEncrypt, Integer platform) {
        //校验必填项
        if (StringUtils.isBlank(phoneNumber)
                || identifyCode == null
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(passwordEncrypt)
                || platform == null) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return userService.resetPassword(phoneNumber, identifyCode, password, passwordEncrypt, platform);
    }

    @RequestMapping(value = "/bindingAccount", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse bindingAccount(String phoneNumber, Integer identifyCode, String accountNo, Integer accountType, Integer platform) {
        if (StringUtils.isBlank(phoneNumber)
                || identifyCode == null
                || StringUtils.isBlank(accountNo)
                || accountType == null
                || platform == null) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return userService.bindingAccount(phoneNumber, identifyCode, accountNo, accountType, platform);
    }

    @RequestMapping(value = "/general/updateGenInfos", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse updateGenInfos(GeneralUser user) {
        if (user == null || StringUtils.isBlank(user.getPhoneNumber())) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return userService.updateGenInfos(user);
    }

    @RequestMapping(value = "/expert/updateExpInfos", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse updateExpInfos(ExpertUser user) {
        if (user == null || StringUtils.isBlank(user.getPhoneNumber())) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return userService.updateExpInfos(user);
    }
}
