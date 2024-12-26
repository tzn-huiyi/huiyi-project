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
 * 权限表实体类
 * @TableName permission
 */
@TableName(value ="permission")
@Data
public class Permission extends BaseEntity implements Serializable {


    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 关联源数据id
     */
    private Long resourceId;

    /**
     * 关联源数据类型（如：menu菜单，file文件等）
     */
    private String resourceType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}