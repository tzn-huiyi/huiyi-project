package com.huiyi.huiyiproject.controller;

import com.huiyi.huiyiproject.dto.TechShareRequestDto;
import com.huiyi.huiyiproject.entity.TechShare;
import com.huiyi.huiyiproject.entity.base.Result;
import com.huiyi.huiyiproject.service.TechShareService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * techShare类控制层
 */
@RestController
@RequestMapping("/techShare")
public class TechShareController {

    @Resource
    public TechShareService techShareService;

    /**
     * 创建一条数据
     * @param techShare 用户实体类参数
     * @return 统一接口返回结果类
     */
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/createOne")
    public Result<?> createOne(@RequestBody TechShare techShare){
        return Result.success(techShareService.createOne(techShare));
    }

    /**
     * 修改一条数据
     * @param techShare 用户实体类参数
     * @return 统一接口返回结果类
     */
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/updateOne")
    public Result<?> updateOne(@RequestBody TechShare techShare){
        return Result.success(techShareService.updateOne(techShare));
    }

    /**
     * 删除一条数据
     * @param id 主键id
     * @return 统一接口返回结果类
     */
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/deleteOne/{id}")
    public Result<?> deleteOne(@PathVariable Long id){
        return Result.success(techShareService.deleteOne(id));
    }

    /**
     * 批量删除
     * @param idList 主键id集合
     * @return 统一接口返回结果类
     */
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Long> idList){
        return Result.success(techShareService.deleteBatch(idList));
    }

    /**
     * 分页查询多条数据
     * @param techShareRequestDto 用户请求实体类参数
     * @return 统一接口返回结果类
     */

    @PostMapping("/findList")
    public Result<?> findList(@RequestBody TechShareRequestDto techShareRequestDto){
        return Result.success(techShareService.findList(techShareRequestDto));
    }

    /**
     * 查询一条数据
     * @param id 主键id
     * @return 统一接口返回结果类
     */
    @GetMapping("/findOne/{id}")
    public Result<?> findOne(@PathVariable Long id){
        return Result.success(techShareService.findOne(id));
    }





}
