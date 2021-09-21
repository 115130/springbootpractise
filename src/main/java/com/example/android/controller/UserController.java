package com.example.android.controller;

import com.example.android.domain.User;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.service.HostelService;
import com.example.android.service.UserService;
import com.example.android.util.EncryptUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private HostelService hostelService;

    @PostMapping("existUsername")
    public ResponseData existUsername(String username){
        int i = userService.existUserName(username);
        if (i<0){
            return new ResponseData(ExceptionMsg.LoginNameNoteExists);
        }
        return  new ResponseData("000012","");
    }

    @PostMapping("login")
    public ResponseData login(String username, String password, HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User login = userService.login(user);
        session.setAttribute("user", login);
        if (login == null) {
            return new ResponseData(ExceptionMsg.LoginNameOrPasswordError);
        }
        if (login.getAdmin() == 0) {
            return new ResponseData(ExceptionMsg.StudentSuccess);
        }
        return new ResponseData(ExceptionMsg.TeacherSuccess);
    }

    @PostMapping("studentRegister")
    public ResponseData register(User user) {
        user.setAdmin(0);
        int register = userService.register(user);
        if (register > 0){
            hostelService.autoAllotHostel(user);
            return new ResponseData(ExceptionMsg.SUCCESS);
        }if (register==-1){
            return new ResponseData(ExceptionMsg.UserNameError);
        }
        return new ResponseData(ExceptionMsg.ParamError);
    }
}
