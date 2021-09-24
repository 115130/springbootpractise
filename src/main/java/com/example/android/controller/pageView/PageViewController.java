package com.example.android.controller.pageView;

import com.example.android.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class PageViewController {

    @GetMapping("/")
    public String toLogin() {
        return "login";
    }

    @GetMapping("toRegister")
    public String toRegister() {
        return "register";
    }

    @GetMapping("toIndex")
    public String toIndex() {
        return "index";
    }

    @GetMapping("toStudentIndex")
    public String toStudentIndex() {

        return "studentIndex";
    }

    @GetMapping("toHead")
    public String toHead() {
        return "head";
    }

    @GetMapping("toLeft")
    public String toLeft() {
        return "left";
    }

    @GetMapping("toWelcome")
    public String toWelcome() {
        return "welcome";
    }

    @GetMapping("toStudentDetails")
    public String toStudentDetails() {
        return "studentDetails";
    }


//
//    @GetMapping("login.html")
//    public String toLogin() {
//        return "login";
//    }
//
//    @GetMapping("login.html")
//    public String toLogin() {
//        return "login";
//    }
//
//    @GetMapping("login.html")
//    public String toLogin() {
//        return "login";
//    }
//
//    @GetMapping("login.html")
//    public String toLogin() {
//        return "login";
//    }
//
//    @GetMapping("login.html")
//    public String toLogin() {
//        return "login";
//    }
//
//    @GetMapping("login.html")
//    public String toLogin() {
//        return "login";
//    }
//
//    @GetMapping("login.html")
//    public String toLogin() {
//        return "login";
//    }
}
