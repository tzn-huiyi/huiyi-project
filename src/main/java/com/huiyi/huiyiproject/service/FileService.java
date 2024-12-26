package com.huiyi.huiyiproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huiyi.huiyiproject.dto.FileEntityRequestDto;
import com.huiyi.huiyiproject.dto.UserRequestDto;
import com.huiyi.huiyiproject.entity.FileEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author TZN
* @description 针对表【files】的数据库操作Service
* @createDate 2024-12-06 12:20:05
*/
public interface FileService extends IService<FileEntity> {

    /**
     * 创建单条数据
     */
    int createOne(FileEntity fileEntity);
    /**
     * 创建单条数据
     */
    int updateOne(FileEntity fileEntity);
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
    IPage<FileEntity> findList(FileEntityRequestDto fileEntityRequestDto);

    /**
     * 单条查询
     */
    FileEntity findOne(Long id);

    /**
     * 根据唯一uuid删除文件
     */
    int deleteByUniqueName(String uniqueName);

    /**
     * 刷新minio图片url
     */
    int freshURL(FileEntityRequestDto fileEntityRequestDto);

}
