package com.study.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * filter 防止sql注入过滤器
 * 在接口调用传参时 防止在参数中进行拼接语句 此处拦截带有sql关键字的参数
 *
 * @author yangyibao
 * @version 2.2
 * @date 2020年12月1日16:27:18
 */
@Component
@WebFilter(urlPatterns = "/ser/*,/sys/*", filterName = "SQLInjection") // urlPatterns:拦截的总路径 多个举例:/ser/*,/sys/*
public class SqlInjectFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(getClass().getName());
    public static final String RULESTR = "and|exec|insert|select|drop|grant|alter|delete|update|count|chr|mid|master|truncate|char|declare|or";
    private static Pattern pattern = Pattern.compile("\\b("+RULESTR+")\\b|(\\*\\+|'|%)");

    // 排除sql注入校验路径的集合
    private static List<String> exclude = new ArrayList<>();

    static {
        exclude.add("/system/dosql");
    }

    public Boolean checkRequestParam(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        Map<?,?> parametersMap = servletRequest.getParameterMap();
        for(Map.Entry<?,?> entry :parametersMap.entrySet()){
            Object key = entry.getKey();
            String[] value = (String[]) entry.getValue();
            for(String str: value){
                if (null != str && containsSqlInjection(str) && !key.equals("token")) {
                    String msg = "非法参数" + key + "=" + str + "!";
                    log.error(msg);
                    action(servletResponse, msg);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkJsonParam(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        byte[] requestBody = StreamUtils.copyToByteArray(servletRequest.getInputStream());//获取application/json 数据
        //验证json数据
        JSONObject jsonObject = JSON.parseObject(new String(requestBody, StandardCharsets.UTF_8));
        if (jsonObject != null) {
            for(Map.Entry<String, Object> tmpMap :jsonObject.entrySet()){
                String str = tmpMap.getKey();
                Object v = tmpMap.getValue();
                //因为token有+/等-所以过滤
                if (null != v && containsSqlInjection(v.toString()) && !str.equals("token")) {
                    String msg = "非法参数" + str + "=" + v + "!";
                    log.error(msg);
                    try {
                        action(servletResponse, msg);
                        return true;
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();

        if (!excludePathCheck(uri)) {
            if(Boolean.TRUE.equals(checkRequestParam(servletRequest,servletResponse))){
                return;
            }
            if(Boolean.TRUE.equals(checkJsonParam(servletRequest, servletResponse))){
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 验证参数中是否包含sql注入语句
     *
     * @param obj Object
     * @return boolean
     */
    private static boolean containsSqlInjection(Object obj) {
        Matcher matcher = pattern.matcher(obj.toString());
        return matcher.find();
    }

    private static boolean excludePathCheck(String uri) {
        for (int i = 0, size = exclude.size(); i < size; i++) {
            boolean result = exclude.get(i).equals(uri);
            if (result) {
                return result;
            }
        }
        return false;
    }

    /**
     * 如果包含sql注入返回json字符串
     * @param servletResponse
     * @param msg
     * @throws IOException
     */
    private void action(ServletResponse servletResponse, String msg) throws IOException {
        servletResponse.setContentType("application/json; charset=utf-8");
        servletResponse.setCharacterEncoding("UTF-8");
        Map<String, Object> map = new HashMap<>(2);
        map.put("flag", false);
        map.put("message", "您输入的参数存在安全隐患!" + msg);
        OutputStream out = servletResponse.getOutputStream();
        JSONObject json = (JSONObject) JSON.toJSON(map);
        out.write(json.toJSONString().getBytes(StandardCharsets.UTF_8));
        out.flush();
    }

}