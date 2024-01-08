package com.study.controller;

import com.study.util.ZipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@RestController
public class ZipController {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * 压缩文件
     * @return
     */
    @RequestMapping("testZip")
    public Map<String, Object> testZip(){

        Map<String, Object> rsMap = new HashMap<>();
        ZipUtil zipUtil = new ZipUtil();

        //压缩后的文件名
        String zipFileName = "C:\\doc\\data.zip";

        //待压缩的文件夹路径
        File file = new File("C:\\doc\\exc");

        //待压缩的文件夹名称
        String folderName = "exc";

        //待压缩的文件夹路径
        String folderToCompress = file.getAbsolutePath();

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            // 压缩文件夹
            zipUtil.compressFolder(folderToCompress, folderName, zipOutputStream);
            log.info("Folder compressed successfully!");
            rsMap.put("flag", true);
            rsMap.put("path", zipFileName);
            return  rsMap;
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        rsMap.put("flag", false);
        return rsMap;
    }


}
