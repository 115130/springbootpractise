package com.example.android.controller.pageView;

import com.example.android.domain.ClassInfo;
import com.example.android.domain.view.ClassView;
import com.example.android.domain.view.HostelView;
import com.example.android.service.ClassInfoService;
import com.example.android.service.HostelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class PageViewController {
    @Resource
    ClassInfoService classInfoService;

    @Resource
    HostelService hostelService;


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

    @GetMapping("toStudentLeft")
    public String toStudentLeft() {
        return "studentLeft";
    }

    @GetMapping("allStudent.html")
    public String allStudent() {
        return "allStudent";
    }

    @GetMapping("toViewClassInfo")
    public String toViewClassInfo(String studentNumber, HttpServletRequest request) {
        List<ClassView> classViewByStudentNumber = classInfoService.findClassViewByStudentNumber(studentNumber);
        request.setAttribute("classViews", classViewByStudentNumber);
        return "viewClassInfo";
    }

    @GetMapping("toAllClassInfo")
    public String toAllClassInfo() {
        return "allClassInfo";
    }

    @GetMapping("toAllHostel")
    public String toAllHostel() {
        return "allHostel";
    }

    @GetMapping("toAllLateReturn")
    public String toAllLateReturn() {
        return "allLateReturn";
    }

    @GetMapping("toAllVisit")
    public String toAllVisit() {
        return "allVisit";
    }

    @GetMapping("toViewHostel")
    public String toViewHostel(Long id, HttpServletRequest request) {
        List<HostelView> hostelByHostelId = hostelService.findHostelByHostelId(id);
        log.error(hostelByHostelId.toString());
        request.setAttribute("hostelViews", hostelByHostelId);
        return "viewHostel";
    }

    @GetMapping("toEditUser")
    public String toEditUser(Long id, HttpServletRequest request) {
        request.setAttribute("id", id);
        return "editUser";
    }

    @GetMapping("toEditClassInfo")
    public String toEditClassInfo(Long id, HttpServletRequest request) {
        request.setAttribute("id", id);
        return "editClassInfo";
    }

    @GetMapping("toEditVisit")
    public String toEditVisit(Long id, HttpServletRequest request) {
        request.setAttribute("id", id);
        return "editVisit";
    }

    @GetMapping("toEditHostel")
    public String toEditHostel(Long id, HttpServletRequest request) {
        request.setAttribute("id", id);
        return "editHostel";
    }

    @GetMapping("toEditLateReturn")
    public String toEditLateReturn(Long id, HttpServletRequest request) {
        request.setAttribute("id", id);
        return "editLateReturn";
    }

    @GetMapping("toAddHostel")
    public String toAddHostel() {
        return "addHostel";
    }

    @GetMapping("toAddClassInfo")
    public String toAddClassInfo() {
        return "addClassInfo";
    }

    @GetMapping("toAddLateReturn")
    public String toAddLateReturn() {
        return "addLateReturn";
    }

    @GetMapping("toAddVisit")
    public String toAddVisit() {
        return "addVisit";
    }

    @GetMapping("return")
    public String return1(HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }


}
