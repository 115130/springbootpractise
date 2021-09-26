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
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "admin", method = RequestMethod.POST)
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
    public ResponseData deleteStudent(String studentNumber) {
        int i = studentViewService.deleteStudent(studentNumber);
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
    public ResponseData updateStudentClassInfo(User user) {
        User user1 = studentViewService.selectUserByUserId(user.getId());
        user1.setUsername(user.getUsername());
        user1.setPassword(EncryptUtil.encrypt(user.getPassword()));
        user1.setClassInfo(user.getClassInfo());
        user1.setHostel(user.getHostel());
        int c = studentViewService.updateStudentHostel(user1);
        int i1 = studentViewService.updateUserNameAndPassword(user1);
        log.error(user.toString());
        log.error(String.valueOf(i1));
        if ((i1|c)>0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }




}
