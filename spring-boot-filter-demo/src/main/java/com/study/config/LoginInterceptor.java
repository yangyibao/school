package com.study.config;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 登录判断拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

	private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	public void returnJson(HttpServletResponse response){
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			response.setCharacterEncoding("UTF-8");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("flag", false);
			jsonObject.put("message", "please login first!");
			jsonObject.put("code", 500);
			outputStream.write(jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
	    String requestUri = request.getRequestURI();
	    String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());

		if(url.contains("login") || url.contains("error")){
			return true;
		}

		String userName =  (String) request.getSession().getAttribute("userName");
		if(null == userName || "".equals(userName)){
			returnJson(response);
			return false;
		}else{
			return true;
		}
	}

}
