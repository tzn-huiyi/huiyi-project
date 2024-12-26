package com.huiyi.huiyiproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.huiyi.huiyiproject.entity.base.BaseEntity;
import lombok.Data;

/**
 * 
 * @TableName files
 */
@TableName(value ="files")
@Data
public class FileEntity extends BaseEntity implements Serializable {

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}