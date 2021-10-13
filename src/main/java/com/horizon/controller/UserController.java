package com.horizon.controller;

import com.horizon.controller.ex.FileEmptyException;
import com.horizon.controller.ex.FileSizeException;
import com.horizon.controller.ex.FileTypeException;
import com.horizon.controller.ex.FileUploadException;
import com.horizon.dto.InfoDto;
import com.horizon.dto.LoginDto;
import com.horizon.entity.User;
import com.horizon.service.IUserService;
import com.horizon.service.ex.InsertException;
import com.horizon.service.ex.UsernameDuplicatedException;
import com.horizon.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    public static final String IMG_PATH = "E:" + File.separator + "SpringBoot" + File.separator + "avatar";
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK);
    }
//    @RequestMapping("reg")
//    public JsonResult<Void> reg(User user) {
//        JsonResult<Void> result = new JsonResult<>();
//        try {
//            userService.reg(user);
//        } catch (UsernameDuplicatedException e) {
//            result.setState(4000);
//            result.setMessage("用户名已被占用");
//        } catch(InsertException e) {
//            result.setState(5000);
//            result.setMessage("产生未知异常");
//        }
//        result.setState(200);
//        result.setMessage("用户注册成功");
//        return result;
//    }

    @RequestMapping("login")
    public JsonResult<LoginDto> login(String username, String password, HttpSession session) {
        LoginDto dto = userService.login(username, password);

        session.setAttribute("uid", dto.getUid());
        session.setAttribute("username", dto.getUsername());

        return new JsonResult<LoginDto>(OK, dto);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        String username = getUsernameFromSession(session);
        Integer uid = getUidFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<InfoDto> getByUid(HttpSession session) {
        InfoDto dto = userService.getByUid(getUidFromSession(session));
        return new JsonResult<>(OK, dto);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file) {
        System.out.println(file.getContentType());
        if(file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        } else if(file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件大小超过限制");
        } else if(!AVATAR_TYPE.contains(file.getContentType())) {
            throw new FileTypeException("不支持该文件类型");
        }
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        String path = IMG_PATH + File.separator + filename;
        System.out.println(path);
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            throw new FileUploadException("文件读写异常");
        }

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeAvatar(uid, path, username);
        return new JsonResult<String>(OK, path);
    }
}