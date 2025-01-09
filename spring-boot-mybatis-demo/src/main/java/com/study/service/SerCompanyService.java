package com.study.service;

import com.study.model.SerCompanyVO;

import java.util.List;

public interface SerCompanyService {

    List<SerCompanyVO> querySerCompanyList();

    int addCompany(SerCompanyVO serCompanyVO);

    int addCompanyDynamic(SerCompanyVO serCompanyVO);

    int addCompanyBatch(List<SerCompanyVO> list);

    SerCompanyVO querySerCompanyById(Integer companyId);

    int updateCompany(SerCompanyVO serCompanyVO);

    int updateCompanyDynamic(SerCompanyVO serCompanyVO);

    int deleteCompanyById(Integer companyId);

    List<SerCompanyVO> querySerCompanyListTagJoin();

    List<SerCompanyVO> querySerCompanyListTagAsso();

    SerCompanyVO querySerCompanyByIdStr(String companyId);
}
