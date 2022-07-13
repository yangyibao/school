package com.study;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class MyApplication {

    /**
     * 测试接口 返回当前系统时间
     * 8080是配置文件默认端口 可以在 application.properties中修改端口号
     * http://localhost:8080/test
     * @return
     */
    @RequestMapping("/test")
    public Map<String, Object> test(){
        Map<String, Object> rsMap = new HashMap<>();
        rsMap.put("time", System.currentTimeMillis());
        return rsMap;
    }

    /**
     * 启动入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class);
    }

}
