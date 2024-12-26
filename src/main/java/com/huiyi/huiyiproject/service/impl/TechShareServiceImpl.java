package com.huiyi.huiyiproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huiyi.huiyiproject.dto.MenuRequestDto;
import com.huiyi.huiyiproject.dto.TechShareRequestDto;
import com.huiyi.huiyiproject.entity.Menu;
import com.huiyi.huiyiproject.entity.TechShare;
import com.huiyi.huiyiproject.service.TechShareService;
import com.huiyi.huiyiproject.mapper.TechShareMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
* @author TZN
* @description 针对表【tech_share】的数据库操作Service实现
* @createDate 2024-12-17 22:48:59
*/
@Service
public class TechShareServiceImpl extends ServiceImpl<TechShareMapper, TechShare>
    implements TechShareService{

    @Resource
    private TechShareMapper techShareMapper;

    @Override
    public int createOne(TechShare techShare) {
        return 0;
    }

    @Override
    public int updateOne(TechShare techShare) {
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
    public List<TechShare> findList(TechShareRequestDto techShareRequestDto) {
        Assert.notNull(techShareRequestDto, "请求实体类参数不能为空");

        QueryWrapper<TechShare> queryWrapper = new QueryWrapper<>();
        if(techShareRequestDto.getType() != null){
            queryWrapper.lambda().eq(TechShare::getType,techShareRequestDto.getType());
        }
        queryWrapper.lambda().select(
                TechShare::getId,
                TechShare::getCreateTime,
                TechShare::getUpdateTime,
                TechShare::getType,
                TechShare::getCode,
                TechShare::getTitle
        );

        return techShareMapper.selectList(queryWrapper);
    }

    @Override
    public TechShare findOne(Long id) {
        Assert.notNull(id, "主键id不能为空");

        QueryWrapper<TechShare> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TechShare::getId,id);
        return techShareMapper.selectOne(queryWrapper);
    }


}




