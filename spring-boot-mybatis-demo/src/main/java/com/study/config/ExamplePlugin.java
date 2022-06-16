package com.study.config;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;


/**
 * @author Yangyibao
 * mybatis 拦截器示例:当调用 update 方法时 进行拦截 可以在这里处理sql语句等
 */
@Component
@Intercepts({@Signature(
        type= Executor.class,
        method = "update",
        args = {MappedStatement.class,Object.class})})
public class ExamplePlugin implements Interceptor {

    private Properties properties = new Properties();

    private Logger log = LoggerFactory.getLogger(ExamplePlugin.class.getName());

    public Object intercept(Invocation invocation) throws Throwable {
        if(log.isDebugEnabled()){
            log.debug("proceed begin ...");
        }
        Object returnObject = invocation.proceed();
        if(log.isDebugEnabled()){
            log.debug("proceed end.");
        }
        return returnObject;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}