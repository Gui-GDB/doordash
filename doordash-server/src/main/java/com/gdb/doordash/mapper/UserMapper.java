package com.gdb.doordash.mapper;

import com.gdb.doordash.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 插入数据
     */
    void insert(User user);

    /**
     * 通过主键获取用户信息
     */
    @Select("select * from user")
    User getById(Long userId);
}