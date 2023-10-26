package com.study.util;

import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcelUtil {

    private static Logger log = LoggerFactory.getLogger(ReadExcelUtil.class.getName());

    public static List<DataVO1> read1(){
        String filePath="C:\\doc\\exc\\aa.xlsx";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(filePath).getPath());
            List<DataVO1> lineImportDtoList = EasyExcel.read(inputStream).head(DataVO1.class).sheet(0).doReadSync();
            // 保存路网数据到数据库
            if (!lineImportDtoList.isEmpty()) {
//                log.info("lineImportDtoList:" + JSON.toJSONString(lineImportDtoList));
                log.info("lineImportDtoList:{}" , lineImportDtoList.size());
                // todo 保存数据库。。。。。。。。
                return lineImportDtoList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<DataVO1> read2(){
        String filePath="C:\\doc\\exc\\bb.xlsx";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(filePath).getPath());
            List<DataVO1> lineImportDtoList = EasyExcel.read(inputStream).head(DataVO1.class).sheet(0).doReadSync();
            // 保存路网数据到数据库
            if (!lineImportDtoList.isEmpty()) {
                log.info("lineImportDtoList22:{}" , lineImportDtoList.size());
                // todo 保存数据库。。。。。。。。
                return lineImportDtoList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
        List<DataVO1> list1 =  read1();
        List<DataVO1> list2 =  read2();
        Map<String,DataVO1> rsMap = new HashMap<>();
        for (DataVO1 dataVO1: list2 ) {
            //培训机构_开始时间_结束时间_身份号
//            String key = dataVO1.getA2()+"_"+dataVO1.getA7()+"_"+dataVO1.getA8()+"_"+dataVO1.getA10();
            String key = dataVO1.getA2()+"_"+dataVO1.getA10();
//            System.out.println(key);
            rsMap.put(key, dataVO1);
        }

        for (DataVO1 dataVO1: list1 ) {

            String sfzh = dataVO1.getA4();
            if(null != sfzh && !sfzh.contains("'")){
                sfzh = "'"+sfzh;
            }

            String key = dataVO1.getA8()+"_"+sfzh;
            //System.out.println(key);
            if(rsMap.containsKey(key)){
                System.out.println(rsMap.get(key).getA5());
            }else{
                System.out.println("-");
            }
        }
    }

}
