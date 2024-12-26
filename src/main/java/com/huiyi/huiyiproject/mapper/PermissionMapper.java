package com.huiyi.huiyiproject.mapper;

import com.huiyi.huiyiproject.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author TZN
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2024-11-04 15:56:14
* @Entity com.huiyi.huiyiproject.entity.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据资源 ID 和类型查询权限
     */
    Permission selectByResourceIdAndType(@Param("resourceId") Long resourceId, @Param("resourceType") String resourceType);

    /**
     * 根据资源 ID 和类型删除权限
     */
    void deleteByResourceIdAndType(@Param("resourceId") Long resourceId, @Param("resourceType") String resourceType);
}




