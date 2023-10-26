package com.study.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description :身份证校验工具类
 */
public class IdCardCheckUtil {

    /**
     * 位权值数组
     */
    private static int[] weightCode = new int[17];

    /**
     * 身份证校验码（末尾）
     * 用来比对计算的余数
     */
    private static final String[] CHECK_CODE = {"1","0","X","9","8","7","6","5","4","3","2"};

    /**
     * 除数11（身份证算法求余关键值）
     */
    private static final int DIVIDER = 11;

    /**
     * 身份证前6位占的字符数（地区6位）
     */
    private static final int AREA_NUMBER = 6;

    /**
     * 新身份证年份标志(比如：1989年取19)
     * 旧身份证号与新身份证号的区别是：新的去掉年份和最后一位就是旧的证件号
     */
    private static final String YEAR_PREFIX = "19";

    private static final int OLD_ID_CARD_LENGTH = 15;

    private static final int NEW_ID_CARD_LENGTH = 18;


    static {
        //1. 初始化位权值数组
        setWiBuffer();
    }

    /**
     * description :获取位权值数组
     * @param
     * @return void
     * @author susu
     */
    private static void setWiBuffer() {
        for (int i = 0; i < weightCode.length; i++) {
            int k = (int) Math.pow(2, (weightCode.length - i));
            weightCode[i] = (k % DIVIDER);
        }
    }

    /**
     * description :校验身份证入口
     * @param idCard
     * @return boolean
     * @author susu
     */
    public static boolean checkIdCardProc(final String idCard) {
        if (checkLengthAndBirthday(idCard)) {
            return idCard.length() == OLD_ID_CARD_LENGTH ? true : checkIdCard18(idCard);
        }
        return false;
    }

    /**
     * description :2.校验身份证长度 和 出生日期 和正则校验
     * @param idCard
     * @return boolean
     * @author susu
     */
    private static boolean checkLengthAndBirthday(final String idCard) {
        if ((idCard.length() == OLD_ID_CARD_LENGTH) || (idCard.length() == NEW_ID_CARD_LENGTH)) {
            return regexCheckAndCheckBirthday(idCard);
        }
        return false;
    }

    /**
     * description :3.校验身份证中的日期是否合法（含正则校验）
     * @param idCard
     * @return boolean
     * @author susu
     */
    private static boolean regexCheckAndCheckBirthday(final String idCard) {
        String birthday = "";
        /**
         * 1. 加一层正则校验
         * 2. 获取证件号的出生日期的字符串：格式如：20221121
         */
        if (idCard.length() == OLD_ID_CARD_LENGTH) {
            //15位的身份证号没有校验码，所以最好用正则校验一下
            if (idCard15RegexCheck(idCard)) {
                birthday = YEAR_PREFIX + idCard.substring(AREA_NUMBER, AREA_NUMBER + 6);
            }
        } else {
            //18位的不用正则校验也行
            if (idCard18RegexCheck(idCard)) {
                birthday = idCard.substring(AREA_NUMBER, AREA_NUMBER + 8);
            }
        }
        return checkStrDate(birthday);
    }

    /**
     * description :最后，校验身份证最后一位检验码是否正确
     * @param idCard
     * @return boolean
     * @author susu
     */
    public static boolean checkIdCard18(final String idCard) {
        //1.获取余数
        int dividedResult = getDividedResult(idCard);
        //2.根据余数获取对应的身份证校验码
        String code = CHECK_CODE[dividedResult];
        //3.获取身份证的最后一位（第18位），然后校验
        String lastStr = idCard.substring(idCard.length() - 1);
        if (code.equals(lastStr)) {
            return true;
        }
        return false;
    }

    /**
     * description :根据前17位加权求和 获取余数
     * @param idCard
     * @return int
     * @author susu
     */
    public static int getDividedResult(String idCard) {
        //先获取前17位数
        String[] idCardNum = idCard.substring(0, 17).split("");
        int sum = 0;
        for (int i = 0; i < idCardNum.length; i++) {
            sum += Integer.parseInt(idCardNum[i]) * weightCode[i];
        }
        return sum % DIVIDER;
    }

    /*
     * "\\d{8}"                  1~6位分别代表省市县，只校验是否数字。
     *                              7~8位代表年份后两位数字
     * "(0[1-9]|1[012])"          9~10位代表月份，01~12月
     *     "(0[1-9]|[12]\\d|3[01])"  11~12位代表日期，1~31日
     *     "\\d{3}"                  13~15位为三位顺序号
     */
    private static boolean idCard15RegexCheck(String idCard) {
        String reg = "^(\\d{8}(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3})$";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(idCard);
        return (m.matches()) ? true : false;
    }

    /*
     * "\\d{6}"                  1~6位分别代表省市县，只校验是否数字。
     *     "(18|19|20)\\d{2}"       7~10位代表年份，先管18，19，20，下个世纪的让下个世纪的人去校验
     *     "(0[1-9]|1[012])"        11~12位代表月份，01~12月
     *     "(0[1-9]|[12]\\d|3[01])"  13~14位代表日期，1~31日
     *     "\\d{3}"                  15~17位为三位顺序号
     *     "(\\d|X|x)"               18位为校验位数字，允许字母x和X
     */
    private static boolean idCard18RegexCheck(String idCard) {
        String reg = "^(\\d{6}(18|19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X|x))$";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(idCard);
        return (m.matches()) ? true : false;
    }

    /**
     * description :校验8位日期是否合法（比如：20021122）
     * @param strDate
     * @return boolean
     * @author susu
     */
    private static boolean checkStrDate(final String strDate) {
        try {
            LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {

        String id2 = "220105199103152816";

        boolean b2 = checkIdCardProc(id2);
        System.out.println(b2);


    }

}
