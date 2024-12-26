package com.huiyi.huiyiproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.huiyi.huiyiproject.entity.base.BaseEntity;
import lombok.Data;

/**
 * 
 * @TableName role_permission
 */
@TableName(value ="role_permission")
@Data
public class RolePermission extends BaseEntity implements Serializable {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}