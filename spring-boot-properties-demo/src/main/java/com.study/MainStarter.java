package com.study;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@Configuration
@PropertySource(value = {"classpath:config/application-config.properties","file:config/application-config.properties"}, ignoreResourceNotFound = true)
public class MainStarter {

    @Value("${file_path}")
    private String filePath;
    @Value("${file_max}")
    private Integer fileMax;
    @Value("${upload_url}")
    private String uploadUrl;
    @Value("${file_test}")
    private Integer fileTest;


    @RequestMapping("getConf")
    public Map<String, Object> getConf(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("filePath", filePath);
        map.put("fileMax", fileMax);
        map.put("uploadUrl", uploadUrl);
        map.put("fileTest", fileTest);
        return map;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainStarter.class, args);
    }

}
