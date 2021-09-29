package com.example.android.controller.teacher;

import com.example.android.domain.ClassInfo;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.domain.view.ClassView;
import com.example.android.service.ClassInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
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
    @PostMapping("findClassById")
    public ResponseData findClassById(Long id) {
        ClassInfo oneClassInfo = classInfoService.findOneClassInfo(id);
        if (oneClassInfo == null) {
            return new ResponseData(ExceptionMsg.ParamError);
        }
        return new ResponseData(ExceptionMsg.SUCCESS, oneClassInfo);
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

    @PostMapping("deleteClassInfoById")
    public ResponseData deleteClassInfo(Long id) {
        return classInfoService.deleteClassInfo(id);
    }

}
