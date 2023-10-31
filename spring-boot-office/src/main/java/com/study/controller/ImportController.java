package com.study.controller;

import com.study.service.BcSerXyzpGwglVOService;
import com.study.util.ExcelImportUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 导入excel示例
 */
@RestController
public class ImportController {

    @Resource
    private BcSerXyzpGwglVOService bcSerXyzpGwglVOService;

    /**
     * 导入excel数据 转换为 对象 list
     * @param file
     * @param userId
     * @url http://localhost:8080/importBcSerXyzpGwglVO
     * @return
     */
    @PostMapping(value = "/importBcSerXyzpGwglVO")
    public Map<String, Object> importBcSerXyzpGwglVO(@RequestParam("file") MultipartFile file, Integer userId){
        //File file = new File("C:\\doc\\gw_import.xlsx");
        Map<String, Object> resultMap = new HashMap<>();
        try{
            File file1 = ExcelImportUtil.saveTempFile(file);
            if(file !=null){
                Map<String, Object> rsMap = bcSerXyzpGwglVOService.importBcSerXyzpGwglVO(file1, userId);
                return rsMap;
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("flag",false);
            resultMap.put("message","导入失败!请与管理员联系!");
            return resultMap;
        }
        resultMap.put("flag",false);
        resultMap.put("message","导入失败!请与管理员联系!");
        return resultMap;
    }
}
