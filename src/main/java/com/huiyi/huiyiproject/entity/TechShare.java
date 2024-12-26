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
 * @TableName tech_share 技术分享表
 */
@TableName(value ="tech_share")
@Data
public class TechShare extends BaseEntity implements Serializable {


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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}