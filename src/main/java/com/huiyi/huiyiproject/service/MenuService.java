package com.huiyi.huiyiproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huiyi.huiyiproject.dto.MenuRequestDto;
import com.huiyi.huiyiproject.dto.UserRequestDto;
import com.huiyi.huiyiproject.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huiyi.huiyiproject.entity.User;

import java.util.List;

/**
* @author TZN
* @description 针对表【menu】的数据库操作Service
* @createDate 2024-11-02 14:04:52
*/
public interface MenuService extends IService<Menu> {

    /**
     * 创建单条数据
     */
    int createOne(Menu menu);
    /**
     * 创建单条数据
     */
    int updateOne(Menu menu);
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
    List<Menu> findList(MenuRequestDto menuRequestDto);

    /**
     * 单条查询
     */
    Menu findOne(Long id);

    /**
     * 获取菜单树
     */
    List<Menu> getMenuTree();

    /**
     * 批量更新菜单排序
     */
    void updateMenuOrder(List<Menu> menus);

}
