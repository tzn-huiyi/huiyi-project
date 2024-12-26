package com.huiyi.huiyiproject.mapper;

import com.huiyi.huiyiproject.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author TZN
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2024-11-02 14:04:52
* @Entity com.huiyi.huiyiproject.entity.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取菜单树
     * @return
     */
//    List<Menu> getMenuTree();
    List<Menu> getAuthorizedMenuTree(Long userId);

    /**
     * 批量更新菜单排序
     */
    int updateOrderNumBatch(@Param("menus") List<Menu> menus);

}




