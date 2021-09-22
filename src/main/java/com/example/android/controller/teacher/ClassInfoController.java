package com.example.android.controller.teacher;

import com.example.android.domain.ClassInfo;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.domain.view.ClassView;
import com.example.android.service.ClassInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("admin")
public class ClassInfoController {
    @Resource
    ClassInfoService classInfoService;

    @PostMapping("findAllClassInfo")
    public ResponseData findAllClassInfo() {
        List<ClassInfo> list = classInfoService.findAllClassInfo();
        return new ResponseData(ExceptionMsg.SUCCESS, list);
    }

    @PostMapping("findClassViewByStudentNumber")
    public ResponseData findClassViewByStudentNumber(String studentNumber) {
        List<ClassView> classViewByStudentNumber = classInfoService.findClassViewByStudentNumber(studentNumber);
        if (classViewByStudentNumber == null) {
            return new ResponseData(ExceptionMsg.ParamError);
        }
        return new ResponseData(ExceptionMsg.SUCCESS, classViewByStudentNumber);
    }

    @PostMapping("addClassInfo")
    public ResponseData addClassInfo(ClassInfo classInfo) {
        int i = classInfoService.addClassInfo(classInfo);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("updateClassInfo")
    public ResponseData updateClassInfo(ClassInfo classInfo) {
        int i = classInfoService.updateClassInfo(classInfo);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("deleteClassInfo")
    public ResponseData deleteClassInfo(Long classInfoId) {
        int i = classInfoService.deleteClassInfo(classInfoId);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }
}
