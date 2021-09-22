package com.example.android.controller.teacher;

import com.example.android.domain.Hostel;
import com.example.android.domain.User;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.domain.view.StudentView;
import com.example.android.service.StudentViewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("admin")
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

    @PostMapping("updateStudentClassInfo")
    public ResponseData updateStudentClassInfo(Long studentId, Long classInfoId) {
        int i = studentViewService.updateStudentClassInfo(studentId, classInfoId);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("updateStudentHostel")
    public ResponseData updateStudentHostel(Long userId) {
        int i = studentViewService.updateStudentHostel(userId);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }


}
