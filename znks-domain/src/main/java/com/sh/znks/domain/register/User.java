package com.sh.znks.domain.register;

import java.util.Date;

/**
 * Created by wuminggu on 2018/5/10.
 */
public class User {
    private Long id;                    //主键、自增id
    private String zn;                  //ZN
    private String headImg;             //头像链接
    private String phoneNumber;         //电话号码（手机）
    private String signature;           //个性签名
    private String userDescribe;        //用户简介
    private String province;            //省份
    private String city;                //城市
    private String longitude;           //东经
    private String latitude;            //北纬
    private String accountNo;           //结算账户号
    private Integer accountType;        //账户类型:0银行卡、1支付宝、2微信
    private String additional;          //附加信息
    private Integer source;             //用户来源:0随机注册、1推荐、2机构导入、3其他
    private Integer status;             //用户状态:0无效、1有效、2审查中
    private Integer onOffLine;          //登录状态:0未登录、1已登录
    private Date created;               //创建时间
    private Date modified;              //更新时间
    private Integer registerType;       //注册类型:0学生注册、1专家注册
    private String password;            //明文密码
    private String passwordEncrypt;     //暗文密码

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZn() {
        return zn;
    }

    public void setZn(String zn) {
        this.zn = zn;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUserDescribe() {
        return userDescribe;
    }

    public void setUserDescribe(String userDescribe) {
        this.userDescribe = userDescribe;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOnOffLine() {
        return onOffLine;
    }

    public void setOnOffLine(Integer onOffLine) {
        this.onOffLine = onOffLine;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordEncrypt() {
        return passwordEncrypt;
    }

    public void setPasswordEncrypt(String passwordEncrypt) {
        this.passwordEncrypt = passwordEncrypt;
    }
}
