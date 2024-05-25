package com.gdb.doordash.service;

import com.gdb.doordash.dto.UserLoginDTO;
import com.gdb.doordash.entity.User;

public interface UserService {

    /**
     * 微信登录
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}