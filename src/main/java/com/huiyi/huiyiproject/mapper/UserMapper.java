package com.huiyi.huiyiproject.mapper;

import com.huiyi.huiyiproject.dto.UserRequestDto;
import com.huiyi.huiyiproject.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author TZN
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-06-25 17:19:44
* @Entity com.huiyi.huiyiproject.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {


}




