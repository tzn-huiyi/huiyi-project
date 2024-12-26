package com.huiyi.huiyiproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huiyi.huiyiproject.dto.MenuRequestDto;
import com.huiyi.huiyiproject.dto.UserRequestDto;
import com.huiyi.huiyiproject.entity.CustomUserDetails;
import com.huiyi.huiyiproject.entity.Menu;
import com.huiyi.huiyiproject.entity.Permission;
import com.huiyi.huiyiproject.entity.User;
import com.huiyi.huiyiproject.mapper.PermissionMapper;
import com.huiyi.huiyiproject.mapper.UserMapper;
import com.huiyi.huiyiproject.service.MenuService;
import com.huiyi.huiyiproject.mapper.MenuMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author TZN
* @description 针对表【menu】的数据库操作Service实现
* @createDate 2024-11-02 14:04:52
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @Resource
    public MenuMapper menuMapper;
    @Resource
    public PermissionMapper permissionMapper;

    @Override
    public int createOne(Menu menu) {
        Assert.notNull(menu.getName(), "菜单名称不能为空");

        // 获取同级菜单的最大 menu_index 和 order_num
        List<Menu> siblingMenus = menuMapper.selectList(new QueryWrapper<Menu>()
                .eq("parent_id", menu.getParentId())
                .eq("del_flag", 0)
                .orderByDesc("order_num"));

        String newMenuIndex;
        int newOrderNum;

        if (siblingMenus.isEmpty()) {
            // 如果没有同级菜单，初始化 menu_index 和 order_num
            newMenuIndex = menu.getParentId() == null ? "1" : menuMapper.selectById(menu.getParentId()).getMenuIndex() + "-1";
            newOrderNum = 1;
        } else {
            // 如果有同级菜单，则获取最后一个同级菜单
            Menu lastSibling = siblingMenus.get(0);
            newMenuIndex = incrementMenuIndex(lastSibling.getMenuIndex());
            newOrderNum = lastSibling.getOrderNum() + 1;
        }

        menu.setMenuIndex(newMenuIndex);
        menu.setOrderNum(newOrderNum);
        menu.setDelFlag(0);

        // 保存菜单
        return menuMapper.insert(menu);
    }

    private String incrementMenuIndex(String menuIndex) {
        String[] parts = menuIndex.split("-");
        parts[parts.length - 1] = String.valueOf(Integer.parseInt(parts[parts.length - 1]) + 1);
        return String.join("-", parts);
    }

    @Override
    @Transactional
    public int updateOne(Menu menu) {
        //更新菜单的基本信息
        int resultInt = menuMapper.updateById(menu);
        //更新菜单对应的权限信息
        // 更新权限字符串，如果存在的话
        Permission permission = permissionMapper.selectByResourceIdAndType(menu.getId(), "menu");
        if (permission != null) {
            permission.setName(menu.getPermissionString());
            permissionMapper.updateById(permission);
        } else if (menu.getPermissionString() != null) {
            // 新增权限
            Permission newPermission = new Permission();
            newPermission.setResourceId(menu.getId());
            newPermission.setResourceType("menu");
            newPermission.setName(menu.getPermissionString());
            permissionMapper.insert(newPermission);
        }

        return resultInt;
    }

    @Override
    @Transactional
    public int deleteOne(Long id) {
        Assert.notNull(id, "主键id不能为空");

        // 检查是否存在子菜单
        Long childCount = menuMapper.selectCount(
                new QueryWrapper<Menu>().eq("parent_id", id).eq("del_flag", 0)
        );
        if (childCount != null && childCount > 0) {
            throw new IllegalArgumentException("该菜单存在子菜单，无法删除");
        }

        //删除菜单
        UpdateWrapper<Menu> updateWrapper = new UpdateWrapper<>();
        //根据id指定单条数据
        updateWrapper.lambda().eq(Menu::getId,id);
        //将 删除标记 置为1
        updateWrapper.lambda().set(Menu::getDelFlag,1);
        int resultInt = menuMapper.update(updateWrapper);

        //同时删除对应的权限记录
        UpdateWrapper<Permission> updateWrapper2 = new UpdateWrapper<>();
        updateWrapper2.lambda()
                .eq(Permission::getResourceId,id)
                .eq(Permission::getResourceType,"menu")
                .set(Permission::getDelFlag,1);
        permissionMapper.update(updateWrapper2);

        return resultInt;
    }

    @Override
    public int deleteBatch(List<Long> idList) {
        Assert.notEmpty(idList, "主键id的list集合不能为空");

        UpdateWrapper<Menu> updateWrapper = new UpdateWrapper<>();
        //根据idList指定若干条数据
        updateWrapper.lambda().in(Menu::getId,idList);
        //将 删除标记 置为1
        updateWrapper.lambda().set(Menu::getDelFlag,1);
        int resultInt = menuMapper.update(updateWrapper);

        // 批量逻辑删除对应的权限记录
        UpdateWrapper<Permission> permissionWrapper = new UpdateWrapper<>();
        permissionWrapper.lambda()
                .in(Permission::getResourceId, idList)
                .eq(Permission::getResourceType, "menu")
                .set(Permission::getDelFlag, 1);
        permissionMapper.update(null, permissionWrapper);

        return menuMapper.update(updateWrapper);
    }

    @Override
    public List<Menu> findList(MenuRequestDto menuRequestDto) {
        Assert.notNull(menuRequestDto, "请求实体类参数不能为空");

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();

        return menuMapper.selectList(queryWrapper);
    }

    @Override
    public Menu findOne(Long id) {
        Assert.notNull(id, "主键id不能为空");

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getId,id);
        return menuMapper.selectOne(queryWrapper);
    }


    //获取菜单树
    @Override
    public List<Menu> getMenuTree() {
        Long userId = getCurrentUserIdIfLoggedIn();

        // 获取菜单数据（传入 null 表示未登录用户）
        List<Menu> menus = menuMapper.getAuthorizedMenuTree(userId);

        //获取所有菜单数据
//        List<Menu> menus = menuMapper.getMenuTree();
        //获取所有根节点菜单
        List<Menu> rootMenus = menus.stream()
                .filter(menu -> menu.getParentId() == null)
                .collect(Collectors.toList());
        //递归设置每个根节点的子节点
        for(Menu rootMenu : rootMenus){
            rootMenu.setChildren(getChildren(rootMenu,menus));
        }

        return rootMenus;
    }

    private Long getCurrentUserIdIfLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getPrincipal())) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getId(); // 返回用户ID
        }
        return null; // 未登录用户返回 null
    }

    //递归获取子节点
    private List<Menu> getChildren(Menu parent, List<Menu> menus){

        List<Menu> children =
                menus.stream()
                        .filter(menu -> parent.getId() == menu.getParentId())
                        .sorted(Comparator.comparingInt(Menu::getOrderNum))
                        .collect(Collectors.toList());

        //递归获取每个子节点的子节点
        for(Menu child : children){
            child.setChildren(getChildren(child,menus));
        }

        return children;
    }


    @Override
    @Transactional
    public void updateMenuOrder(List<Menu> menus) {
        if (menus == null || menus.isEmpty()) {
            throw new IllegalArgumentException("菜单列表不能为空");
        }

        // 调用批量更新方法
        menuMapper.updateOrderNumBatch(menus);
    }
}




