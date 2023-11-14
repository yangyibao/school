package com.study.controller;

import com.alibaba.excel.EasyExcel;
import com.study.model.DataVO1;
import com.study.service.BcSerXyzpGwglVOService;
import com.study.util.ExcelImportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导入excel示例
 */
@RestController
public class ImportController {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

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


    /**
     * easyExcel  excel导入 读取为list
     * @param file
     * @return
     */
    @PostMapping(value = "/easyExcelImplTest")
    public Map<String, Object> easyExcelImplTest(@RequestParam("file") MultipartFile file){
        //String filePath="C:\\doc\\exc\\aa.xlsx";
        Map<String, Object> resultMap = new HashMap<>();
        InputStream inputStream = null;
        try {
            File file1 = ExcelImportUtil.saveTempFile(file);
            inputStream = new FileInputStream(file1.getPath());
            List<DataVO1> lineImportDtoList = EasyExcel.read(inputStream).head(DataVO1.class).sheet(0).doReadSync();
            // 保存路网数据到数据库
            if (!lineImportDtoList.isEmpty()) {
                log.info("lineImportDtoList:{}" , lineImportDtoList);
                // todo 保存数据库。。。。。。。。
            }
            resultMap.put("flag", true);
            return resultMap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        resultMap.put("flag", false);
        resultMap.put("message","导入失败!请与管理员联系!");
        return resultMap;
    }


}
