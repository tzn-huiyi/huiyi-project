package com.huiyi.huiyiproject.service;

import com.huiyi.huiyiproject.dto.TechShareRequestDto;
import com.huiyi.huiyiproject.entity.TechShare;
import com.huiyi.huiyiproject.entity.TechShare;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author TZN
* @description 针对表【tech_share】的数据库操作Service
* @createDate 2024-12-17 22:48:59
*/
public interface TechShareService extends IService<TechShare> {

    /**
     * 创建单条数据
     */
    int createOne(TechShare techShare);
    /**
     * 创建单条数据
     */
    int updateOne(TechShare techShare);
    /**
     * 删除单条数据
     */
    int deleteOne(Long id);

    /**
     * 批量删除
     */
    int deleteBatch(List<Long> idList);


    /**
     * 查询多条数据
     */
    List<TechShare> findList(TechShareRequestDto techShareRequestDto);

    /**
     * 单条查询
     */
    TechShare findOne(Long id);

}
