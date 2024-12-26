package com.huiyi.huiyiproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huiyi.huiyiproject.dto.RoleRequestDto;
import com.huiyi.huiyiproject.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huiyi.huiyiproject.entity.User;

import java.util.List;

/**
* @author TZN
* @description 针对表【role】的数据库操作Service
* @createDate 2024-12-09 18:58:42
*/
public interface RoleService extends IService<Role> {

    /**
     * 创建单条数据
     */
    int createOne(Role role);
    /**
     * 创建单条数据
     */
    int updateOne(Role role);
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
    IPage<Role> findList(RoleRequestDto roleRequestDto);

    /**
     * 单条查询
     */
    Role findOne(Long id);
}
