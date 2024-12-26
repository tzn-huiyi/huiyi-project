package com.huiyi.huiyiproject.controller;

import com.huiyi.huiyiproject.dto.FileEntityRequestDto;
import com.huiyi.huiyiproject.entity.FileEntity;
import com.huiyi.huiyiproject.entity.base.Result;
import com.huiyi.huiyiproject.service.FileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * FileEntity类控制层
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    public FileService filesService;

    /**
     * 创建一条数据
     * @param fileEntity 用户实体类参数
     * @return 统一接口返回结果类
     */
    @PostMapping("/createOne")
    public Result<?> createOne(@RequestBody FileEntity fileEntity){
        return Result.success(filesService.createOne(fileEntity));
    }

    /**
     * 修改一条数据
     * @param fileEntity 用户实体类参数
     * @return 统一接口返回结果类
     */
    @PostMapping("/updateOne")
    public Result<?> updateOne(@RequestBody FileEntity fileEntity){
        return Result.success(filesService.updateOne(fileEntity));
    }

    /**
     * 删除一条数据
     * @param id 主键id
     * @return 统一接口返回结果类
     */
    @PostMapping("/deleteOne/{id}")
    public Result<?> deleteOne(@PathVariable Long id){
        return Result.success(filesService.deleteOne(id));
    }

    /**
     * 批量删除
     * @param idList 主键id集合
     * @return 统一接口返回结果类
     */
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Long> idList){
        return Result.success(filesService.deleteBatch(idList));
    }

    /**
     * 分页查询多条数据
     * @param fileEntityRequestDto 用户请求实体类参数
     * @return 统一接口返回结果类
     */
    @PostMapping("/findList")
    public Result<?> findList(@RequestBody FileEntityRequestDto fileEntityRequestDto){
        return Result.success(filesService.findList(fileEntityRequestDto));
    }

    /**
     * 查询一条数据
     * @param id 主键id
     * @return 统一接口返回结果类
     */
    @GetMapping("/findOne/{id}")
    public Result<?> findOne(@PathVariable Long id){
        return Result.success(filesService.findOne(id));
    }

    /**
     * 刷新minio文件url
     */
    @PostMapping("/freshURL")
    public Result<?> freshURL(@RequestBody FileEntityRequestDto fileEntityRequestDto){
        return Result.success(filesService.freshURL(fileEntityRequestDto));
    }


}