package com.study.service.impl;

import com.study.model.BcSerXyzpGwglVO;
import com.study.service.BcSerXyzpGwglVOService;
import com.study.util.ExcelImportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BcSerXyzpGwglVOServiceImpl implements BcSerXyzpGwglVOService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Map<String, Object> importBcSerXyzpGwglVO(File file, Integer userId) {
        Map<String, Object> rsMap = new HashMap<>();
        ExcelImportUtil excelImportUtil = new ExcelImportUtil();
        List<BcSerXyzpGwglVO> list = excelImportUtil.getBcSerXyzpGwglVOByFile(file,userId);
        log.debug("list:{}", list);
        //这里将list批量插入至数据库即可


        //返回数据
        rsMap.put("data", list);
        rsMap.put("flag", true);
        return rsMap;
    }
}
