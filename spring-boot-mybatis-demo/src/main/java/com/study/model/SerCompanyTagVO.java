package com.study.model;

import org.apache.ibatis.type.Alias;

@Alias("SerCompanyTagVO")
public class SerCompanyTagVO {

    private int tagId;
    private String tagName;
    private String tagMsg;
    private int companyId;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagMsg() {
        return tagMsg;
    }

    public void setTagMsg(String tagMsg) {
        this.tagMsg = tagMsg;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
