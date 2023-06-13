package com.study;
import com.study.model.TestDataVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@SpringBootApplication
public class MyApplication {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 测试接口 将time存入redis 从redis取出该值并返回
     * 38081是配置文件默认端口 可以在 application.properties中修改端口号
     * http://localhost:38081/addKey
     * @return
     */
    @RequestMapping("addKey")
    public Map<String, Object> addKey(){
        Map<String, Object> rsMap = new HashMap<>();
        long time = System.currentTimeMillis();
        String key = "time";
        //time 为key 存入 redis 2分钟后失效 不失效请取消后两个参数
        redisTemplate.opsForValue().set(key,time,2, TimeUnit.MINUTES);
        //将键为time的值取出 存入返回map
        rsMap.put("time", redisTemplate.opsForValue().get(key));
        return rsMap;
    }

    /**
     * 测试接口 将对象数组存入redis 从redis取出该值后返回
     * http://localhost:38081/addList
     * @return
     */
    @RequestMapping("addList")
    public Map<String, List<TestDataVO>> addList(){
        Map<String, List<TestDataVO>> rsMap = new HashMap<>();
        TestDataVO testDataVO = new TestDataVO("张三", 16);
        TestDataVO testDataVO1 = new TestDataVO("李四", 18);
        List<TestDataVO> list = new ArrayList<>();
        list.add(testDataVO);
        list.add(testDataVO1);
        //将集合存入到list中
        redisTemplate.opsForList().rightPushAll("testList", list);

        //从redis中取出数据
        List<TestDataVO> redisData = redisTemplate.opsForList().range("testList",0,-1);
        rsMap.put("testList", redisData);
        return rsMap;
    }

    /**
     * 测试接口 删除key
     * http://localhost:38081/removeKey
     * @return
     */
    @RequestMapping("removeKey")
    public Map<String, Object> removeKey(){
        Boolean rs = redisTemplate.delete("testList");
        Map<String, Object> rsMap = new HashMap<>();
        rsMap.put("rs", rs);
        return rsMap;
    }

    @RequestMapping("testJson")
    public Map<String,Object> testJson(String name, String age){

        Map<String,Object> rsMap = new HashMap<>();
        rsMap.put("name", name);
        rsMap.put("age", age);
        return rsMap;
    }

    @PostMapping("testFile")
    public Map<String, Object> testFile(@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2, String fileName){
        Map<String,Object> rsMap = new HashMap<>();

        rsMap.put("file1Name", file1.getOriginalFilename());
        rsMap.put("file1Size", file1.getSize());

        rsMap.put("file2Name", file2.getOriginalFilename());
        rsMap.put("file2Size", file2.getSize());

        rsMap.put("fileName", fileName);

        String filePath1 = "D:\\study\\filedir" + File.separator + file1.getOriginalFilename();
        String filePath2 = "D:\\study\\filedir" + File.separator + file2.getOriginalFilename();
        File desFile1 = new File(filePath1);
        File desFile2 = new File(filePath2);
        try {
            file1.transferTo(desFile1);
            file2.transferTo(desFile2);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }


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
