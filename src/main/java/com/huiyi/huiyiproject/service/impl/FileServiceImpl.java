package com.huiyi.huiyiproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huiyi.huiyiproject.dto.FileEntityRequestDto;
import com.huiyi.huiyiproject.dto.UserRequestDto;
import com.huiyi.huiyiproject.entity.FileEntity;
import com.huiyi.huiyiproject.service.FileService;
import com.huiyi.huiyiproject.mapper.FileMapper;
import com.huiyi.huiyiproject.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
* @author TZN
* @description 针对表【files】的数据库操作Service实现
* @createDate 2024-12-06 12:20:05
*/
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity>
    implements FileService {

    @Resource
    public FileMapper filesMapper;

    @Autowired
    private MinioUtil minioUtil;

    @Override
    public int createOne(FileEntity fileEntity) {
        Assert.notNull(fileEntity, "文件实体类参数不能为空");

        return filesMapper.insert(fileEntity);
    }

    @Override
    public int updateOne(FileEntity fileEntity) {
        Assert.notNull(fileEntity, "文件实体类参数不能为空");
        return filesMapper.updateById(fileEntity);
    }

    @Override
    public int deleteOne(Long id) {
        return 0;
    }

    @Override
    public int deleteBatch(List<Long> idList) {
        return 0;
    }

    @Override
    public IPage<FileEntity> findList(FileEntityRequestDto fileEntityRequestDto) {

        Assert.notNull(fileEntityRequestDto, "文件请求实体类参数不能为空");

        // 分页参数
        Page<FileEntity> page = new Page<>(
                fileEntityRequestDto.getCurrentPage() == null ? 1 : fileEntityRequestDto.getCurrentPage(),
                fileEntityRequestDto.getPageSize() == null ? 10 : fileEntityRequestDto.getPageSize()
        );

        QueryWrapper<FileEntity> queryWrapper = new QueryWrapper<>();

        if (fileEntityRequestDto.getResourceId() != null) {
            queryWrapper.eq("resource_id", fileEntityRequestDto.getResourceId());
        }
        if (fileEntityRequestDto.getResourceType() != null) {
            queryWrapper.eq("resource_type", fileEntityRequestDto.getResourceType());
        }
        return filesMapper.selectPage(page, queryWrapper);
    }

    @Override
    public FileEntity findOne(Long id) {
        return null;
    }

    @Override
    public int deleteByUniqueName(String uniqueName) {
        Assert.notNull(uniqueName, "文件唯一uuid不能为空");

        //逻辑删除minio文件的记录
        UpdateWrapper<FileEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("unique_name", uniqueName);
        updateWrapper.set("del_flag", 0);
        int update = filesMapper.update(null, updateWrapper);

        return update;

    }

    @Override
    public int freshURL(FileEntityRequestDto fileEntityRequestDto) {

        IPage<FileEntity> list = findList(fileEntityRequestDto);
        for (FileEntity fileEntity : list.getRecords()) {
            // 设置预签名 URL 有效期为7天（单位：秒）
            String fileUrl = null;
            try {
                fileUrl = minioUtil.getFileUrl(fileEntity.getUniqueName(), 7 * 24 * 60 * 60);
                fileEntity.setUrl(fileUrl);
                updateOne(fileEntity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println(fileUrl);
        }

        return 1;
    }
}




