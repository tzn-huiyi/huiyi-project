package com.huiyi.huiyiproject.controller;

import com.huiyi.huiyiproject.dto.RoleRequestDto;
import com.huiyi.huiyiproject.entity.Role;
import com.huiyi.huiyiproject.entity.base.Result;
import com.huiyi.huiyiproject.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * role类控制层
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    public RoleService roleService;

    /**
     * 创建一条数据
     * @param role 用户实体类参数
     * @return 统一接口返回结果类
     */
    @PostMapping("/createOne")
    public Result<?> createOne(@RequestBody Role role){
        return Result.success(roleService.createOne(role));
    }

    /**
     * 修改一条数据
     * @param role 用户实体类参数
     * @return 统一接口返回结果类
     */
    @PostMapping("/updateOne")
    public Result<?> updateOne(@RequestBody Role role){
        return Result.success(roleService.updateOne(role));
    }

    /**
     * 删除一条数据
     * @param id 主键id
     * @return 统一接口返回结果类
     */
    @PostMapping("/deleteOne/{id}")
    public Result<?> deleteOne(@PathVariable Long id){
        return Result.success(roleService.deleteOne(id));
    }

    /**
     * 批量删除
     * @param idList 主键id集合
     * @return 统一接口返回结果类
     */
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Long> idList){
        return Result.success(roleService.deleteBatch(idList));
    }

    /**
     * 分页查询多条数据
     * @param roleRequestDto 请求实体类参数
     * @return 统一接口返回结果类
     */
    @PostMapping("/findList")
    public Result<?> findList(@RequestBody RoleRequestDto roleRequestDto){
        return Result.success(roleService.findList(roleRequestDto));
    }

    /**
     * 查询一条数据
     * @param id 主键id
     * @return 统一接口返回结果类
     */
    @GetMapping("/findOne/{id}")
    public Result<?> findOne(@PathVariable Long id){
        return Result.success(roleService.findOne(id));
    }



}
