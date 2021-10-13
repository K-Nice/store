package com.horizon.mapper;

import com.horizon.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        int count = userMapper.insert(user);
        System.out.println(count);
    }

    @Test
    public void findByUsername() {
        System.out.println(userMapper.findByUsername("tim"));
    }

    @Test
    public void updatePasswordByUid() {
        userMapper.updatePasswordByUid(3, "321", "管理员", new Date());
    }

    @Test
    public void findByUid() {
        System.out.println(userMapper.findByUid(3));
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(1);
        user.setEmail("123@tt.com");
        user.setGender(1);
        user.setPhone("123456");
        userMapper.updateInfo(user);
    }

    @Test
    public void updateAvatar() {
//        userMapper.updateAvatar(6, "/upload/avatar.png", "管理员", new Date());
    }
}
