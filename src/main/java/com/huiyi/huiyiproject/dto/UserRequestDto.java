package com.huiyi.huiyiproject.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 用户请求dto类
 */
@Data
public class UserRequestDto {

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
     * 分页查询参数
     */
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
