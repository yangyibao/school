package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@SpringBootApplication
public class MyApplication {

    @RequestMapping("system/login")
    public Map<String, Object> doLogin(String userName, String passWord, HttpServletRequest request){
        Map<String, Object> rsMap = new HashMap<>();
        if(null != userName && "admin".equals(userName) && null !=passWord && "123".equals(passWord)){
            request.getSession().setAttribute("userName",userName);
            rsMap.put("flag", true);
        }else{
            rsMap.put("flag", false);
        }
        return rsMap;
    }

    /**
     * 测试接口 该接口在调用时需要进行防止sql注入的过滤
     * 8080是配置文件默认端口 可以在 application.properties中修改端口号
     * http://localhost:8080/queryData?str=and 1=1
     * @return
     */
    @RequestMapping("system/queryData")
    public Map<String, Object> queryData(String str){
        Map<String, Object> rsMap = new HashMap<>();
        rsMap.put("str", str);
        rsMap.put("time", System.currentTimeMillis());
        return rsMap;
    }

    /**
     * 测试接口 该接口在调用时无需防止sql注入
     * @param str
     * http://localhost:8080/system/dosql?str=and 1=1
     * @return
     */
    @RequestMapping("system/dosql")
    public Map<String, Object> doSql(String str){
        Map<String, Object> rsMap = new HashMap<>();
        rsMap.put("dosql", str);
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
