package com.example.android.controller.student;

import com.example.android.domain.User;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.domain.view.StudentView;
import com.example.android.service.StudentViewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("student")
public class StudentController {
    @Resource
    StudentViewService studentViewService;

    @PostMapping("index")
    private ResponseData studentIndex(HttpSession session){
        User user = (User)session.getAttribute("user");
        if (user==null){
            return new ResponseData(ExceptionMsg.FAILED);
        }
        StudentView studentByUserId = studentViewService.findStudentByUserId(user.getId());
        return new ResponseData(ExceptionMsg.SUCCESS,studentByUserId);
    }


}
