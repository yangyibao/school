package com.study.model;

import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("SerCompanyVO")
public class SerCompanyVO {

    private int companyId;
    private String companyName;
    private String companyCode;
    private String companyMsg;
    private String companyCrdt;
    private List<SerCompanyTagVO> list;

    private SerCompanyTagVO serCompanyTagVO;


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

    public List<SerCompanyTagVO> getList() {
        return list;
    }

    public void setList(List<SerCompanyTagVO> list) {
        this.list = list;
    }

    public SerCompanyTagVO getSerCompanyTagVO() {
        return serCompanyTagVO;
    }

    public void setSerCompanyTagVO(SerCompanyTagVO serCompanyTagVO) {
        this.serCompanyTagVO = serCompanyTagVO;
    }
}
