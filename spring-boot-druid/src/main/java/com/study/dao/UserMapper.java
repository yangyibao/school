package com.study.dao;

import com.study.model.SysUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<SysUserVO> selectSysUserList();

    public Integer testCountNum();

}
