package com.example.android.controller.teacher;

import com.example.android.domain.Hostel;
import com.example.android.domain.User;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.domain.view.StudentView;
import com.example.android.service.StudentViewService;
import com.example.android.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "admin")
public class UserController {
    @Resource
    StudentViewService studentViewService;

    @PostMapping("findAllStudent")
    public ResponseData findAllStudent() {
        List<StudentView> allStudent = studentViewService.findAllStudent();
        return new ResponseData(ExceptionMsg.SUCCESS, allStudent);
    }

    @PostMapping("findStudentByUserId")
    public ResponseData findStudentByUserId(Long id) {
        StudentView studentByUserId = studentViewService.findStudentByUserId(id);
        if (studentByUserId == null) {
            return new ResponseData(ExceptionMsg.ParamError);
        }
        return new ResponseData(ExceptionMsg.SUCCESS, studentByUserId);
    }

    @PostMapping("deleteStudent")
    public ResponseData deleteStudent(Long id) {
        int i = studentViewService.deleteStudentById(id);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }


    @GetMapping("deleteStudentById")
    public ResponseData deleteStudentById(Long id) {
        int i = studentViewService.deleteStudentById(id);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("updateStudent")
    public ResponseData updateStudentClassInfo(User user1) {

        User user = studentViewService.selectUserByUserId(user1.getId());
        user.setUsername(user1.getUsername());
        user.setPassword(EncryptUtil.encrypt(user1.getPassword()));
        user.setClassInfo(user1.getClassInfo());
        ResponseData responseData = studentViewService.updateStudentHostel(user);
        if (!responseData.getRspCode().equals("000000")){
            return responseData;
        }
        int i1 = studentViewService.updateUserNameAndPassword(user);
        if ((i1)>0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }
}
