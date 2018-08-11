package com.sh.znks.web.controller.base;

import com.sh.znks.common.base.util.RegisterUtils;
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
        //�༭ѧ��ע����Ϣ
        GeneralUser user = RegisterUtils.InputGeneralParamSet(request);

        //У�������
        if (StringUtils.isBlank(user.getPhoneNumber())) {
            return new ResultResponse(ResultCodeEnum.ZN_PHONE_IS_NULL);
        }

        //ע��
        return userService.registerGeneralUser(user);
    }

    @RequestMapping(value = "/expert", method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse expert(HttpServletRequest request, HttpServletResponse response) {
        //�༭ר��ע����Ϣ
        ExpertUser user = RegisterUtils.InputExpertParamSet(request);

        //У�������
        if (StringUtils.isBlank(user.getPhoneNumber())) {
            return new ResultResponse(ResultCodeEnum.ZN_PHONE_IS_NULL);
        }

        //ע��
        return userService.registerExpertUser(user);
    }
}
