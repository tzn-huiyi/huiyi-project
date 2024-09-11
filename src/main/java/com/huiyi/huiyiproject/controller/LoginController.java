package com.huiyi.huiyiproject.controller;


import com.huiyi.huiyiproject.utils.JwtUtil;
import com.huiyi.huiyiproject.utils.RedisUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口控制层
 */
@RestController
@RequestMapping("")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 用于生成Token的jwt工具类
     */
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/myLogin")
    public String myLogin(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        try {
            // 尝试认证
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            // 登录成功，生成 JWT Token
            String token = jwtUtil.generateToken(username);

            // 返回 Token
            return "登录成功, Token: " + token;

        } catch (AuthenticationException ex) {
            // 登录失败，返回错误信息
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "登录失败: " + ex.getMessage();
        }
    }
}
