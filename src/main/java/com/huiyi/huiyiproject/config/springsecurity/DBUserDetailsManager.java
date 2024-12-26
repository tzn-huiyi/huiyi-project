package com.huiyi.huiyiproject.config.springsecurity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.huiyi.huiyiproject.entity.CustomUserDetails;
import com.huiyi.huiyiproject.entity.User;
import com.huiyi.huiyiproject.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户身份验证管理类
 */
@Component
public class DBUserDetailsManager implements UserDetailsService {

    @Resource
    private UserMapper userMapper;



    /**
     * 从数据库中获取用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        //根据username查出其基本信息和角色、权限列表
        List<User> userAuthorityByUserName = userMapper.selectUserByUsername(username);
        if(userAuthorityByUserName.isEmpty()){
            throw new UsernameNotFoundException("用户未找到");
        }
        //角色和权限去重
        Set<String> roles = userAuthorityByUserName.stream()
                .map(User::getRoleName)
                .filter(role -> role != null && !role.trim().isEmpty()) // 过滤空角色
                .collect(Collectors.toSet());

        Set<String> permissions = userAuthorityByUserName.stream()
                .map(User::getPermissionName)
                .filter(permission -> permission != null && !permission.trim().isEmpty()) // 过滤空权限
                .collect(Collectors.toSet());


        // 构建权限集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));

        //用户基本信息
        User user = userAuthorityByUserName.get(0);

            return new CustomUserDetails(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    "1".equals(user.getEnabled().toString()),
                    authorities,
                    user.getNickname()
            );


        //        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.eq("username",username);

//        User user = userMapper.selectOne(userQueryWrapper);

//        if(Objects.isNull(user)){
//            throw new UsernameNotFoundException("用户 " + username + " 未找到");
//        }else{

            // 获取用户角色和权限
//            List<GrantedAuthority> authorities = getUserAuthorities(user);

//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(user.getUsername())
//                    .password(user.getPassword())
//                    .disabled("1".equals(user.getEnabled()))
//                    .credentialsExpired(false)
//                    .accountLocked(false)
//                    .authorities(authorities)
//                    .build();

//            return new CustomUserDetails(
//                    user.getUsername(),
//                    user.getPassword(),
//                    "1".equals(user.getEnabled().toString()),
//                    authorities,
//                    user.getNickname()
//            );


//        }
    }

//    /**
//     * 获取用户权限
//     * @param user 用户实体
//     * @return 用户权限集合
//     */
//    private List<GrantedAuthority> getUserAuthorities(User user) {
//        // 创建一个空的权限列表
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        //添加用户权限，从角色_权限关联表获取
//
//
//        // 添加用户的权限（暂不从数据库获取）
//        authorities.add(new SimpleGrantedAuthority("ADMIN"));
//
//        return authorities;
//    }

}
