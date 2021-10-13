package com.horizon.service;

import com.horizon.dto.LoginDto;
import com.horizon.entity.User;
import com.horizon.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    IUserService userService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("yuanxin02");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        LoginDto dto = userService.login("test01", "123");
        System.out.println(dto);
    }

    @Test
    public void changePassword() {
        userService.changePassword(6, "管理员", "321", "123");
    }

    @Test
    public void getByUid() {
        System.out.println(userService.getByUid(6));
    }

    @Test
    public void updateInfo() {
        User user = new User();
        user.setEmail("2541@123.com");
        user.setGender(1);
        user.setPhone("258741");
        userService.changeInfo(2, "yuanxin01", user);
    }

    @Test
    public void changeAvatar() {
        userService.changeAvatar(6, "/upload/test.png", "张三");
    }
}