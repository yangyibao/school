package com.study.controller;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("druidMsg")
public class DruidController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("queryDruidData")
    @ResponseBody
    public Map<String, Object> queryDruidData(){
        Map<String, Object> rsMap = new HashMap<>();

        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            System.out.println(connection);
            DruidDataSource druidDataSource = (DruidDataSource) dataSource;
            System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
            System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
            //关闭连接
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rsMap.put("data", System.currentTimeMillis());
        return rsMap;
    }
}
