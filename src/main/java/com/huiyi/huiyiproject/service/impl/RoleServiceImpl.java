package com.huiyi.huiyiproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huiyi.huiyiproject.dto.RoleRequestDto;
import com.huiyi.huiyiproject.entity.Role;
import com.huiyi.huiyiproject.service.RoleService;
import com.huiyi.huiyiproject.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author TZN
* @description 针对表【role】的数据库操作Service实现
* @createDate 2024-12-09 18:58:42
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int createOne(Role role) {
        return 0;
    }

    @Override
    public int updateOne(Role role) {
        return 0;
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
    public IPage<Role> findList(RoleRequestDto roleRequestDto) {
        Assert.notNull(roleRequestDto, "请求实体类参数不能为空");

        //分页参数
        Page<Role> page = new Page<>(
                roleRequestDto.getCurrentPage()==null?1:roleRequestDto.getCurrentPage(),
                roleRequestDto.getPageSize()==null?10:roleRequestDto.getPageSize()
        );

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();

        return roleMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Role findOne(Long id) {
        return null;
    }
}




