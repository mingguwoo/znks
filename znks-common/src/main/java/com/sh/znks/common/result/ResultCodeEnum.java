package com.sh.znks.common.result;

/**
 * Created by wuminggu on 2018/4/19.
 */
public enum  ResultCodeEnum {
    ZN_OK(0, "success"),
    ZN_FALSE(1, "false"),
    ZN_PARAM_NUll(2, "param is null"),
    ZN_USER_REGISTERED(3, "user has registered"),
    ZN_PARAM_ERR(4, "param is error"),
    ZN_PWD_ERR(5, "password is error"),
    ZN_PHONE_IS_NULL(6, "phoneNumber is null"),
    ZN_IDENTIFY_ERR(7, "Identify code is Invalid"),
    ZN_NO_DATA(8, "no data"),
    ZN_UPDATE_FAIL(9, "update is fail"),
    ZN_SYS_ERR(-1, "system error"),
    ZN_QUESTION_EXIST(10, "This question has exist"),
    ZN_NO_LOGIN(11, "no login"),
    ZN_UNIONID_IS_NULL(12, "UnionId is null");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
