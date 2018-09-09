package com.sh.znks.web.controller.base;

import com.sh.znks.common.base.util.RegisterUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;
import com.sh.znks.service.base.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuminggu on 2018/5/8.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    private final static Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/general", method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse general(HttpServletRequest request, HttpServletResponse response) {
        //编辑学生注册信息
        GeneralUser user = RegisterUtils.InputGeneralParamSet(request);

        //校验必填项
        if (StringUtils.isBlank(user.getPhoneNumber())) {
            return new ResultResponse(ResultCodeEnum.ZN_PHONE_IS_NULL);
        }

        //注册
        return userService.registerGeneralUser(user);
    }

    @RequestMapping(value = "/expert", method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse expert(HttpServletRequest request, HttpServletResponse response) {
        //编辑专家注册信息
        ExpertUser user = RegisterUtils.InputExpertParamSet(request);

        //校验必填项
        if (StringUtils.isBlank(user.getPhoneNumber())) {
            return new ResultResponse(ResultCodeEnum.ZN_PHONE_IS_NULL);
        }

        //注册
        return userService.registerExpertUser(user);
    }

    @RequestMapping(value = "/wxAuthorizationGenRegiste", method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse wxAuthorizationGenRegiste(HttpServletRequest request, HttpServletResponse response) {
        //编辑专家注册信息
        WxUser user = RegisterUtils.InputWxParamSet(request);

        //校验必填项
        if (StringUtils.isBlank(user.getUnionId())) {
            return new ResultResponse(ResultCodeEnum.ZN_UNIONID_IS_NULL);
        }

        //注册
        return userService.registerWxUser(user);
    }
}
