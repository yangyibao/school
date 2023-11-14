package com.study.model;

import com.alibaba.excel.annotation.ExcelProperty;

public class DataVO1 {

    @ExcelProperty({"数据导出大标题", "姓名"})
    private String a1;
    @ExcelProperty({"数据导出大标题", "日期"})
    private String a2;
    @ExcelProperty({"数据导出大标题", "年龄"})
    private String a3;
    @ExcelProperty({"数据导出大标题", "学校"})
    private String a4;
    @ExcelProperty({"数据导出大标题", "成绩"})
    private String a5;

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }

    @Override
    public String toString() {
        return "DataVO1{" +
                "a1='" + a1 + '\'' +
                ", a2='" + a2 + '\'' +
                ", a3='" + a3 + '\'' +
                ", a4='" + a4 + '\'' +
                ", a5='" + a5 + '\'' +
                '}';
    }
}
