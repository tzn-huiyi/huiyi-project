package com.huiyi.huiyiproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huiyi.huiyiproject.dto.UserRequestDto;
import com.huiyi.huiyiproject.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huiyi.huiyiproject.entity.base.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
* @author TZN
* @description 针对表【user】的数据库操作Service
* @createDate 2024-06-25 17:19:44
*/
public interface UserService extends IService<User> {

    /**
     * 创建单条数据
     */
    int createOne(User user);
    /**
     * 创建单条数据
     */
    int updateOne(User user);
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
    IPage<User> findList(UserRequestDto userRequestDto);

    /**
     * 单条查询
     */
    User findOne(Long id);

}
