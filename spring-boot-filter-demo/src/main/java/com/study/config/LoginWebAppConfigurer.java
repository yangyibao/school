package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
@Configuration
public class LoginWebAppConfigurer implements WebMvcConfigurer {

	@Bean   //把我们的拦截器注入为bean
    public HandlerInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, /**的连接全部拦截 excludePathPatterns 用户排除拦截 资源文件排除 登录页面排除
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/resources/**","/","/system/login");
    }
	
}
