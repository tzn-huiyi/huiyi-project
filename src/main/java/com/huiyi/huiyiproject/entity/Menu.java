package com.huiyi.huiyiproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huiyi.huiyiproject.entity.base.BaseEntity;
import lombok.Data;

/**
 * 菜单表实体类
 * @TableName menu
 */
@TableName(value ="menu")
@Data
public class Menu extends BaseEntity implements Serializable {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单索引
     */
    private String menuIndex;

    /**
     * 类型（父节点：extend；子节点：content）
     */
    private String type;

    /**
     * 父节点的id
     */
    private Long parentId;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 排序编号
     */
    private Integer orderNum;

    /**
     * 菜单图标（elementUI)
     */
    private String iconEle;


    /**
     * 子节点
     */
    @TableField(exist = false)
    List<Menu> children = new ArrayList<>();

    /**
     * 权限字符串
     */
    @TableField(exist = false)
    String permissionString;

    /**
     * 权限列表
     */
    @TableField(exist = false)
    List<Permission> permissionList= new ArrayList<>();

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}