package com.study.service.impl;

import com.study.dao.SerCompanyMapper;
import com.study.model.SerCompanyVO;
import com.study.service.SerCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerCompanyServiceImpl implements SerCompanyService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private SerCompanyMapper serCompanyMapper;

    @Override
    public List<SerCompanyVO> querySerCompanyList() {
        return serCompanyMapper.selectSerCompanyList();
    }

    @Override
    public int addCompany(SerCompanyVO serCompanyVO) {
        return serCompanyMapper.insertSerCompanyVO(serCompanyVO);
    }

    @Override
    public int addCompanyDynamic(SerCompanyVO serCompanyVO) {
        return serCompanyMapper.insertSerCompanyDynamic(serCompanyVO);
    }

    @Override
    public int addCompanyBatch(List<SerCompanyVO> list) {
        return serCompanyMapper.insertCompanyBatch(list);
    }

    @Override
    public SerCompanyVO querySerCompanyById(Integer companyId) {
        return serCompanyMapper.selectSerCompanyById(companyId);
    }

    @Override
    public int updateCompany(SerCompanyVO serCompanyVO) {
        return serCompanyMapper.updateCompany(serCompanyVO);
    }

    @Override
    public int updateCompanyDynamic(SerCompanyVO serCompanyVO) {
        return serCompanyMapper.updateCompanyDynamic(serCompanyVO);
    }

    @Override
    public int deleteCompanyById(Integer companyId) {
        return serCompanyMapper.deleteCompanyById(companyId);
    }


}
