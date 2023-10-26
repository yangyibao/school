package com.study.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 验证工具类
 */
public class ValidateUitl {

    private static Logger log = LoggerFactory.getLogger(ValidateUitl.class.getName());

    /**
     * 校验list是否为空
     * @param list
     * @return
     */
    public static boolean isEmptyList(List<?> list){
        if(null == list || list.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 校验字符串是否空
     * @param str
     * @return
     */
    public static boolean isEmptyStr(String str){
        if(null == str || str.isEmpty()||str.trim().length() == 0){
            return true;
        }
        return false;
    }

    /**
     * 校验手机号
     * @param str
     * @return
     */
    public static boolean isPhoneStr(String str){
        if (str == null || str.length() != 11) {
            return false;
        }
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        String phone = "13812345678";
        boolean isPhone = Pattern.matches(regex, phone);
        return isPhone;
    }

    /**
     * 校验身份证号
     * @param str
     * @return
     */
    public static boolean isIdCard(String str){
        return IdCardCheckUtil.checkIdCardProc(str);
    }



    public static void main(String[] args) {
        List<Integer> idList =  new ArrayList<>();
        idList.add(11);
        String str = "123";
        String phoneStr = "18166825130";
        String id2 = "220105199103152817";

        log.debug("isEmptyList: {}", isEmptyList(idList));
        log.debug("isEmptyStr: {}", isEmptyStr(str));
        log.debug("isPhoneStr: {}", isPhoneStr(phoneStr));
        log.debug("isIdCard: {}", isIdCard(id2));
    }



}
