package com.huiyi.huiyiproject.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class FileEntityRequestDto {

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
     * 原始文件名
     */
    private String name;

    /**
     * minio中的唯一文件名
     */
    private String uniqueName;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件访问url路径
     */
    private String url;

    /**
     * 关联源数据id
     */
    private Long resourceId;

    /**
     * 关联源数据类型
     */
    private String resourceType;

    /**
     * 分页查询参数
     */
    private Integer currentPage = 1;
    private Integer pageSize = 10;

}
