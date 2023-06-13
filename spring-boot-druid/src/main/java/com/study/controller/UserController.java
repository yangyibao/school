package com.study.controller;

import com.study.model.SysUserVO;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;



    //http://localhost:8080/user/queryUserList
    @RequestMapping("queryUserList")
    @ResponseBody
    public List<SysUserVO> queryUserList(){
        return userService.queryUserList();
    }


    //http://localhost:8080/user/testCountNum
    @RequestMapping("testCountNum")
    @ResponseBody
    public Map<String, Object> testCountNum(){
        Map<String, Object> rsMap = new HashMap<>();
        rsMap.put("num", userService.testCountNum());
        return rsMap;
    }

}
