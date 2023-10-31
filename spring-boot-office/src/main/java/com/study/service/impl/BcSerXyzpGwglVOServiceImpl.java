package com.study.service.impl;

import com.study.model.BcSerXyzpGwglVO;
import com.study.service.BcSerXyzpGwglVOService;
import com.study.util.ExcelImportUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BcSerXyzpGwglVOServiceImpl implements BcSerXyzpGwglVOService {

    @Override
    public Map<String, Object> importBcSerXyzpGwglVO(File file, Integer userId) {
        Map<String, Object> rsMap = new HashMap<>();
        ExcelImportUtil excelImportUtil = new ExcelImportUtil();
        List<BcSerXyzpGwglVO> list = excelImportUtil.getBcSerXyzpGwglVOByFile(file,userId);
        //这里将list批量插入至数据库即可
        rsMap.put("data", list);
        rsMap.put("flag", true);
        return rsMap;
    }
}
