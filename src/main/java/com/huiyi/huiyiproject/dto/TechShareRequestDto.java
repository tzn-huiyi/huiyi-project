package com.huiyi.huiyiproject.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 技术分享 请求dto类
 */
@Data
public class TechShareRequestDto {


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
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章编号
     */
    private String code;

    /**
     * 文章类型
     */
    private String type;

    /**
     * 分页查询参数
     */
    private Integer currentPage = 1;
    private Integer pageSize = 10;


}
