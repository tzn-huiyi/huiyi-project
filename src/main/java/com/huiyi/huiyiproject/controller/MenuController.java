package com.huiyi.huiyiproject.controller;

import com.huiyi.huiyiproject.dto.MenuRequestDto;
import com.huiyi.huiyiproject.entity.Menu;
import com.huiyi.huiyiproject.entity.base.Result;
import com.huiyi.huiyiproject.service.MenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * menu类控制层
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    public MenuService menuService;

    /**
     * 创建一条数据
     * @param menu 用户实体类参数
     * @return 统一接口返回结果类
     */
    @PostMapping("/createOne")
    public Result<?> createOne(@RequestBody Menu menu){
        return Result.success(menuService.createOne(menu));
    }

    /**
     * 修改一条数据
     * @param menu 用户实体类参数
     * @return 统一接口返回结果类
     */
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/updateOne")
    public Result<?> updateOne(@RequestBody Menu menu){
        return Result.success(menuService.updateOne(menu));
    }

    /**
     * 删除一条数据
     * @param id 主键id
     * @return 统一接口返回结果类
     */
    @PostMapping("/deleteOne/{id}")
    public Result<?> deleteOne(@PathVariable Long id){
        return Result.success(menuService.deleteOne(id));
    }

    /**
     * 批量删除
     * @param idList 主键id集合
     * @return 统一接口返回结果类
     */
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Long> idList){
        return Result.success(menuService.deleteBatch(idList));
    }

    /**
     * 分页查询多条数据
     * @param menuRequestDto 用户请求实体类参数
     * @return 统一接口返回结果类
     */

    @PostMapping("/findList")
    public Result<?> findList(@RequestBody MenuRequestDto menuRequestDto){
        return Result.success(menuService.findList(menuRequestDto));
    }

    /**
     * 查询一条数据
     * @param id 主键id
     * @return 统一接口返回结果类
     */
    @GetMapping("/findOne/{id}")
    public Result<?> findOne(@PathVariable Long id){
        return Result.success(menuService.findOne(id));
    }

    /**
     * 获取菜单树
     */
    @PostMapping("/getMenuTree")
    public Result<?> getMenuTree(){
        return Result.success(menuService.getMenuTree());
    }

    /**
     * 批量更新排序编号
     */
    @PostMapping("/updateMenuOrder")
    public Result<?> updateMenuOrder(@RequestBody List<Menu> menus) {
        menuService.updateMenuOrder(menus);
        return Result.success();
    }


}
