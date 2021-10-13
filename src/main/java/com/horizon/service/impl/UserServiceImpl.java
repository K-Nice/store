package com.horizon.service.impl;

import com.horizon.dto.InfoDto;
import com.horizon.dto.LoginDto;
import com.horizon.entity.User;
import com.horizon.mapper.UserMapper;
import com.horizon.service.IUserService;
import com.horizon.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();

        // 判断用户名是否已被注册
        User result = userMapper.findByUsername(username);
        if(result != null) {
            throw new UsernameDuplicatedException("用户名已被占用");
        }

        // 密码加密处理
        // 盐值+密码+盐值 --》 md5加密，盐值是一个随机的字符串
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(oldPassword, salt);

        // 补全数据
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getCreatedUser());
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());

        // 判断是否插入成功
        Integer row = userMapper.insert(user);
        if(row != 1) {
            throw new InsertException("在注册过程中产生未知异常");
        }
    }

    @Override
    public LoginDto login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }

        String oldPassword = result.getPassword();
        String salt = result.getSalt();
        String newPassWord = getMD5Password(password, salt);
        if(!oldPassword.equals(newPassWord)) {
            throw new PasswordNotMatchException("密码错误");
        }

        LoginDto dto = new LoginDto(result.getUid(), result.getUsername(), result.getAvatar());

        return dto;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User user = userMapper.findByUid(uid);
        if(user == null || user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldMD5Password = getMD5Password(oldPassword, user.getSalt());
        if(!user.getPassword().equals(oldMD5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
        String newMD5Password = getMD5Password(newPassword, user.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMD5Password, username, new Date());
        if(rows != 1) {
            throw new UpdateException("更新数据时产生未知异常");
        }
    }

    @Override
    public InfoDto getByUid(Integer uid) {
        User user = userMapper.findByUid(uid);
        if(user == null || user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        InfoDto dto = new InfoDto();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setGender(user.getGender());
        return dto;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        int rows = userMapper.updateInfo(user);
        if(rows != 1) {
            throw new UpdateException("更新数据时产生未知错误");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User user = userMapper.findByUid(uid);
        if(user == null || user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatar(uid, avatar, username, new Date());
        if(rows != 1) {
            throw new UpdateException("更新用户头像时产生异常");
        }
    }

    private String getMD5Password(String password, String salt) {
        // 3次加密
        for(int i = 0; i < 3; ++i) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes(StandardCharsets.UTF_8)).toUpperCase();
        }
        return password;
    }

}
