package com.horizon.controller;

import com.horizon.controller.ex.*;
import com.horizon.service.ex.*;
import com.horizon.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {

    public static final Integer OK = 200;

    // 当前项目产生的异常被统一拦截到这个方法中，方法的返回值直接返给前端
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名已被注册");
        } else if(e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("插入数据时产生未知错误");
        } else if(e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("用户不存在");
        } else if(e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("密码错误");
        } else if(e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("更新时产生未知错误");
        } else if(e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("上传文件不能为空");
        } else if(e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件大小超过限制");
        } else if(e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("文件类型错误");
        } else if(e instanceof FileUploadIOException) {
            result.setState(6003);
            result.setMessage("上传文件时读写异常");
        } else if(e instanceof AddressCountLimitException) {
            result.setState(7000);
            result.setMessage("地址数目超过上限");
        } else if(e instanceof AddressNotFoundException) {
            result.setState(7001);
            result.setMessage(e.getMessage());
        } else if(e instanceof AccessDeniedException) {
            result.setState(7002);
            result.setMessage(e.getMessage());
        } else if(e instanceof DeleteException) {
            result.setState(7003);
            result.setMessage(e.getMessage());
        } else if(e instanceof ProductNotFoundException) {
            result.setState(8000);;
            result.setMessage(e.getMessage());
        }
        return result;
    }

    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
