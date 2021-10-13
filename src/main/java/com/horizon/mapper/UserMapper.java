package com.horizon.mapper;

import com.horizon.dto.InfoDto;
import com.horizon.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {

    Integer insert(User user);

    User findByUsername(String username);

    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    User findByUid(Integer uid);

    Integer updateInfo(User user);

    Integer updateAvatar(@Param("uid") Integer uid,
                         @Param("avatar") String avatar,
                         @Param("modifiedUser") String modifiedUser,
                         @Param("modifiedTime") Date modifiedTime);
}
