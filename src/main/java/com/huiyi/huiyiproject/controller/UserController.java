package com.huiyi.huiyiproject.controller;

import com.huiyi.huiyiproject.dto.UserRequestDto;
import com.huiyi.huiyiproject.entity.User;
import com.huiyi.huiyiproject.entity.base.Result;
import com.huiyi.huiyiproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * user类控制层
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    public UserService userService;

    /**
     * 创建一条数据
     * @param user 用户实体类参数
     * @return 统一接口返回结果类
     */
    @PostMapping("/createOne")
    public Result<?> createOne(@RequestBody User user){
        return Result.success(userService.createOne(user));
    }

    /**
     * 修改一条数据
     * @param user 用户实体类参数
     * @return 统一接口返回结果类
     */
    @PostMapping("/updateOne")
    public Result<?> updateOne(@RequestBody User user){
        return Result.success(userService.updateOne(user));
    }

    /**
     * 删除一条数据
     * @param id 主键id
     * @return 统一接口返回结果类
     */
    @PostMapping("/deleteOne/{id}")
    public Result<?> deleteOne(@PathVariable Long id){
        return Result.success(userService.deleteOne(id));
    }

    /**
     * 批量删除
     * @param idList 主键id集合
     * @return 统一接口返回结果类
     */
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Long> idList){
        return Result.success(userService.deleteBatch(idList));
    }

    /**
     * 分页查询多条数据
     * @param userRequestDto 用户请求实体类参数
     * @return 统一接口返回结果类
     */
    @PostMapping("/findList")
    public Result<?> findList(@RequestBody UserRequestDto userRequestDto){
        return Result.success(userService.findList(userRequestDto));
    }

    /**
     * 查询一条数据
     * @param id 主键id
     * @return 统一接口返回结果类
     */
    @GetMapping("/findOne/{id}")
    public Result<?> findOne(@PathVariable Long id){
        return Result.success(userService.findOne(id));
    }

}