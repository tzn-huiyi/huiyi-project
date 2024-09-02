package com.huiyi.huiyiproject.config.springsecurity;

import com.alibaba.fastjson2.JSON;
import com.huiyi.huiyiproject.entity.base.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;

/**
 * 认证入口点处理器
 * 用于定义当用户尝试访问受保护的资源但没有进行身份验证时应该执行的操作
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        //获取异常信息
        String localizedMessage = authException.getLocalizedMessage();

        HashMap result = new HashMap();
        result.put("data",localizedMessage);
        result.put("extra","无权限");
        //将异常信息放入自定义result
        Result<Object> failureResult = Result.failure(result);
        //返回结果转为json字符串
        String jsonResult = JSON.toJSONString(failureResult);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(jsonResult);

    }
}