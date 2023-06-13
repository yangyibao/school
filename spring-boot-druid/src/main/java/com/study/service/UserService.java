package com.study.service;

import com.study.model.SysUserVO;

import java.util.List;

public interface UserService {

    public List<SysUserVO> queryUserList();

    public Integer testCountNum();
}
