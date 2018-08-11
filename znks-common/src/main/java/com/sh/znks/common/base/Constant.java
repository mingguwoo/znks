package com.sh.znks.common.base;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wuminggu on 2018/5/10.
 */
public class Constant {
    //空字符串
    public static final String BLANK = "";
    //项目名缩写
    public static final String PJN = "zn";
    //MD5
    public static final String MD5 = "MD5";
    //注册号基数
    public static final int BASE_NO = 100000000;
    //异常默认值
    public static final int EXC_NO = -1;
    //常量0
    public static final int ZERO = 0;
    //常量1
    public static final int ONE = 1;
    //常量2
    public static final int TWO = 2;
    //常量3
    public static final int THREE = 3;
    //常量10
    public static final int TEN = 10;
    //年级K12
    public static final List<Integer> GRADEK12 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12));
    //科目（0数、1语、2外）
    public static final List<Integer> SUBJECT = new ArrayList<Integer>(Arrays.asList(0,1,2));
    //题型
    public static final List<Integer> QUESTIONTYPES = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16));
    //问题价值（0-5滴血）
    public static final List<Integer> BLOOD = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5));
    //难度系数（1-5颗星）
    public static final List<Integer> DIFFICULTY_DEGREE = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
    //答题者个人信息JSON字段名
    public static final String GENERAL_USER_INFO = "generalUserInfo";
    //专家个人信息JSON字段名
    public static final String EXPERT_USER_INFO = "expertUserInfo";
}
