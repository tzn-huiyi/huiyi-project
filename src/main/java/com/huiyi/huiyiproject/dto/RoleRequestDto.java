package com.huiyi.huiyiproject.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 角色请求dto类
 */
@Data
public class RoleRequestDto {

    /**
     * 分页查询参数
     */
    private Integer currentPage = 1;
    private Integer pageSize = 10;

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
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;


}
