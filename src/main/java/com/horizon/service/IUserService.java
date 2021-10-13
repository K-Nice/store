package com.horizon.service;

import com.horizon.dto.InfoDto;
import com.horizon.dto.LoginDto;
import com.horizon.entity.User;

public interface IUserService {
    /**
     * 用户注册
     * @param user
     */
    void reg(User user);

    LoginDto login(String username, String password);

    void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    InfoDto getByUid(Integer uid);

    void changeInfo(Integer uid, String username, User user);

    void changeAvatar(Integer uid, String avatar, String username);

}
