package com.study.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

     private static Logger log = LoggerFactory.getLogger(DateUtil.class.getName());

    /**
     * 获取当前年份
     * @return
     */
    public static int getCurYear(){
        Calendar calendar = Calendar.getInstance();
        return  calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String formattedDate = formatter.format(date);
        return formattedDate;
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDay(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String formattedDate = formatter.format(date);
        return formattedDate;
    }

    /**
     * 获取两个日期之间的所有日期 (年月日)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getBetweenDate(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {
                // 把日期添加到集合
                list.add(sdf.format(startDate));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.DATE, 1);
                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取参数时间是否在开始时间的几天之后
     * @param startDay 开始时间
     * @param paramDay 参数时间
     * @param day 开始时间需要增加的天数
     *
     * @return
     */
    public static  Boolean getParamDayIsAfterDay(String startDay, String paramDay, int day){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(startDay);
            Date endDate = sdf.parse(paramDay);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            //把日期增加一天
            calendar.add(Calendar.DATE, day);
            long startTime = calendar.getTime().getTime();
            long endTime = endDate.getTime();
            if(startTime >= endTime){
                return  true;
            }else{
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 判断开始时间 是否在 结束时间之后
     * @param startDay 开始时间
     * @param endDay 结束时间
     * @return
     */
    public static boolean isAfterDay(String startDay, String endDay){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(startDay);
            Date endDate = sdf.parse(endDay);
            long startTime = startDate.getTime();
            long endTime = endDate.getTime();
            if(startTime > endTime){
                return  true;
            }else{
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 根据时间字符串获取时间对象
     * @param time
     * @return
     */
    public static  Date getDayByTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startDate = sdf.parse(time);
            return startDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给指定日期增加几天
     * @param day 日期
     * @param num 天数
     * @return
     */
    public static String addDay(String day, int num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Date startDate = sdf.parse(day);
            Calendar calendar = Calendar.getInstance();
            // 设置日期
            calendar.setTime(startDate);
            //把日期增加一天
            calendar.add(Calendar.DATE, num);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取明天的日期字符串
     *
     * @return
     */
    public static String tomorrowDateStr() {
        Date date = new Date();//取时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //把日期往后增加一天.整数往后推,负数往前移动(1:表示明天、-1：表示昨天，0：表示今天)
        calendar.add(Calendar.DATE, 1);

        //这个时间就是日期往后推一天的结果
        date = calendar.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }


    /**
     * 判断某天是星期几
     * @param time
     * @return
     */
    public static Integer getWeekDay(String time) {
        //必须yyyy-MM-dd
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate.getDayOfWeek().getValue();
    }

    /**
     * 比较俩日期
     * @param startDate
     * @param endDate
     * @return startDate>endDate = 1,  startDate=endDate = 0,  startDate<endDate = -1
     */
    public static int dateComPareTo(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int result = 0;
        try {
            Date date1 = sdf.parse(startDate);
            Date date2 = sdf.parse(endDate);
            if (date1.compareTo(date2) > 0) {
                result = 1;
            } else if (date1.compareTo(date2) < 0) {
                result = -1;
            } else if (date1.compareTo(date2) == 0) {
                result = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 时间转换为 小时:分钟
     * @param date
     * @return
     */
    public static String dateToHM(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String str = sdf.format(date);
        return str;
    }

    /**
     * 是否上午的时间 true-上午 false-下午
     * @param estimatedTimeStart 时间
     * @return boolean true-上午 false-下午
     */
    public static Boolean isAm(Date estimatedTimeStart) {
        try {
            if (estimatedTimeStart != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date currentTime = sdf.parse("12:00");
                String timeStart = dateToHM(estimatedTimeStart);
                Date start = sdf.parse(timeStart);
                return currentTime.after(start) ? true : false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            return true;
        }
    }



    public static void main(String[] args) {

        log.debug("getCurYear:{}", getCurYear());

        log.debug("getCurrentDay:{}", getCurrentDay());

        log.debug("getCurrentTime:{}", getCurrentTime());

        log.debug("getBetweenDate:{}", getBetweenDate("2023-09-01","2023-09-14" ));

        log.debug("getParamDayIsAfterDay:{}", getParamDayIsAfterDay("2023-09-14","2023-09-16", 2));

        log.debug("isAfterDay:{}", isAfterDay("2023-09-15","2023-09-14"));

        log.debug("getDayByTime:{}", getDayByTime("2023-07-19 13:54:44"));

        log.debug("addDay:{}", addDay("2023-09-15", 4));

        log.debug("tomorrowDateStr:{}", tomorrowDateStr());

        log.debug("getWeekDay:{}", getWeekDay("2023-09-18"));

        log.debug("dateComPareTo:{}", dateComPareTo("2023-09-19", "2023-09-18"));

        log.debug("isAm:{}", isAm(new Date()));




    }


}
