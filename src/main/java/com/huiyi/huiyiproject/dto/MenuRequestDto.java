package com.huiyi.huiyiproject.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 菜单请求dto类
 */
@Data
public class MenuRequestDto{


    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记
     */
    private Integer delFlag;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单索引
     */
    private String index;

    /**
     * 类型（父节点：extend；子节点：content）
     */
    private String type;

    /**
     * 父节点的id
     */
    private Integer parentId;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 排序编号
     */
    private Integer order;



}
