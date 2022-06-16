package com.study.model;

import org.apache.ibatis.type.Alias;

@Alias("SerCompanyVO")
public class SerCompanyVO {

    private int companyId;
    private String companyName;
    private String companyCode;
    private String companyMsg;
    private String companyCrdt;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyMsg() {
        return companyMsg;
    }

    public void setCompanyMsg(String companyMsg) {
        this.companyMsg = companyMsg;
    }

    public String getCompanyCrdt() {
        return companyCrdt;
    }

    public void setCompanyCrdt(String companyCrdt) {
        this.companyCrdt = companyCrdt;
    }
}
