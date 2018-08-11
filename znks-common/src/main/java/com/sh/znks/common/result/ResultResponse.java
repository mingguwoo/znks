package com.sh.znks.common.result;

import java.util.HashMap;

/**
 * Created by wuminggu on 2018/4/19.
 */
public class ResultResponse<V> extends HashMap<String, Object> {
    protected static final String CODE = "code";            //返回码
    protected static final String MSG = "msg";              //返回message
    protected static final String DATA = "data";            //返回的结果对象
    protected String dataKey;

    //构造器
    public ResultResponse(int code, String msg) {
        setCode(code);
        setMsg(msg);
    }
    public ResultResponse(ResultCodeEnum codeEnum) {
        setCode(codeEnum.getCode());
        setMsg(codeEnum.getMsg());
    }
    public ResultResponse(ResultCodeEnum codeEnum, String dataKey, V data) {
        this(codeEnum);
        setData(dataKey, data);
    }
    public ResultResponse(ResultCodeEnum codeEnum, V data) {
        this(codeEnum);
        setData(data);
    }

    //success
    public static ResultResponse success() {
        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }
    public static <V> ResultResponse<V> success(V data) {
        return new ResultResponse(ResultCodeEnum.ZN_OK, data);
    }
    public static <V> ResultResponse<V> success(String key, V data) {
        return new ResultResponse(ResultCodeEnum.ZN_OK, key, data);
    }

    //failed
    public static ResultResponse failed(String msg) {
        return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR.getCode(), msg);
    }
    public static ResultResponse failed() {
        return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
    }
    public static ResultResponse failed(ResultCodeEnum codeEnum) {
        return new ResultResponse(codeEnum);
    }

    //isSuccess
    public boolean isSuccess() {
        return ResultCodeEnum.ZN_OK.getCode() == getCode();
    }

    //get、set
    public int getCode() {
        return Integer.valueOf(get(CODE).toString()).intValue();
    }
    public void setCode(int code) {
        put(CODE, code);
    }
    public String getMsg() {
        return get(MSG).toString();
    }
    public void setMsg(String msg) {
        put(MSG, msg);
    }
    public V getData() {
        return get(dataKey) != null ? (V) get(dataKey) : null;
    }
    public void setData(V data) {
        setData(DATA, data);
    }
    public void setData(String dataKey, V data) {
        this.dataKey = dataKey;
        put(dataKey, data);
    }

    //toString
    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        value.append('{').append(CODE).append('=').append(getCode())
                .append(", ").append(MSG).append("='").append(getMsg()).append('\'');

        if (dataKey != null) {
            value.append(", ").append(dataKey).append("='").append(getData()).append('\'');
        }
        value.append('}');
        return value.toString();
    }
}
