package com.huiyi.huiyiproject.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordEncodeConfig {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//
//        //默认编码器为BCrypt
//        String defaultEncode = "bcrypt";
//        Map<String, PasswordEncoder> encoderMap = new HashMap<>();
//
//        // 添加BCrypt编码器，并自定义工作因子
//        encoderMap.put(defaultEncode, new BCryptPasswordEncoder(4));
//        // 添加NoOp编码器
//        encoderMap.put("noop", NoOpPasswordEncoder.getInstance());
//
//        return new DelegatingPasswordEncoder(defaultEncode, encoderMap);
//    }

}
