package com.huiyi.huiyiproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.huiyi.huiyiproject.entity.base.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户表实体类
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User extends BaseEntity implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 是否启用
     */
    private Integer enabled;

    /**
     * 用户编码
     */
    private String code;

    /**
     * 用户昵称
     */
    private String nickname;


    /**
     * 用户角色
     */
    @TableField(exist = false)
    private String roleName;

    /**
     * 用户权限
     */
    @TableField(exist = false)
    private String permissionName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}