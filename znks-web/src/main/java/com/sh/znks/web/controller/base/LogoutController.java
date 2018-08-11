package com.sh.znks.web.controller.base;

import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.service.base.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuminggu on 2018/6/14.
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
    private final static Logger log = LoggerFactory.getLogger(LogoutController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/general/logoutGen", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse logoutGen() {
            return userService.logoutGen();
    }

    @RequestMapping(value = "/expert/logoutExp", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse logoutExp() {
        return userService.logoutExp();
    }
}
