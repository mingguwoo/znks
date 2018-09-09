package com.sh.znks.web.controller;

import com.sh.znks.common.base.http.HttpRequestUtils;
import com.sh.znks.service.base.UserService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuminggu on 2018/4/19.
 */
@Controller
@RequestMapping("/demo")
public class DemoFirstController {
    private final static Logger log = LoggerFactory.getLogger(DemoFirstController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/first/demoFirst", method = RequestMethod.POST)
    @ResponseBody
    public String demoFirst() {
        String url = "http://localhost:8080/login/expert/pwdExpLogin";
        Map<String, String> param = new HashMap<String, String>();
        param.put("phoneNumber", "18702103770");
        param.put("passwordEncrypt", "z2c1WjMz5uFDQ5FhrcLYLg==");
        JSONObject ob = JSONObject.fromObject(param);
        JSONObject js = HttpRequestUtils.httpPost(url, ob);
        if (js == null)
            return null;

        return js.toString();
    }
}

//    @RequestMapping(value = "/first/getUserInfo", method = RequestMethod.GET)
//    public ResultResponse getUserInfo() {
////        List<Author> result = authorService.searchAuthorList();
//        ExpertUser expertUser = new ExpertUser();
////        int res = userService.insertExpertUser(expertUser);
////        log.error("L35_getUserInfo result is {}", JSON.toJSONString(res));
////        ResultResponse rr = new ResultResponse(ResultCodeEnum.ZN_OK, res);
//        return rr;
//    }

    //导入Excel文件
    //public ResultResponse batchExcelCreate(@RequestParam(required = true) MultipartFile excel, @RequestParam(required = true) String activityKey) {
    /*POIUtils poi = new POIUtils();
    List<Long> result;
        try {
        //外层List是每一行的数据，内层List每一个行的单元格
        List<List<String>> data = poi.getExcelData(excel, excel.getOriginalFilename(), 0);
        if (CollectionUtils.isEmpty(data))
            return ResultResponse.failed(ApiCodeEnum.PARAMETER_ERROR);


    }*/
