package com.study.service.impl;

import com.study.dao.UserMapper;
import com.study.model.SysUserVO;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<SysUserVO> queryUserList() {
        return userMapper.selectSysUserList();
    }

    @Override
    public Integer testCountNum() {
        return userMapper.testCountNum();
    }
}
