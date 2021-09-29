package com.example.android.service;

import com.example.android.domain.ClassInfo;
import com.example.android.domain.ClassInfoExample;
import com.example.android.domain.User;
import com.example.android.domain.UserExample;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.domain.view.ClassView;
import com.example.android.mapper.ClassInfoMapper;
import com.example.android.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ClassInfoService {
    @Resource
    private ClassInfoMapper classInfoMapper;

    @Resource
    private UserMapper userMapper;

    public List<ClassInfo> findAllClassInfo() {
        ClassInfoExample classInfoExample = new ClassInfoExample();
        classInfoExample.createCriteria().andIdIsNotNull();
        return classInfoMapper.selectByExample(classInfoExample);
    }

    public ClassInfo findOneClassInfo(Long id) {
        return classInfoMapper.selectByPrimaryKey(id);
    }

    public int addClassInfo(ClassInfo classInfo) {
        return classInfoMapper.insert(classInfo);
    }

    public int updateClassInfo(ClassInfo classInfo1) {
        Long id = classInfo1.getId();
        log.error(id + "");
        ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(id);
        classInfo.setClasses(classInfo1.getClasses());
        classInfo.setGrade(classInfo1.getGrade());
        classInfo.setSpeciality(classInfo1.getSpeciality());
        return classInfoMapper.updateByPrimaryKey(classInfo);
    }

    public ResponseData deleteClassInfo(Long classInfoId) {
        int i = classInfoMapper.deleteByPrimaryKey(classInfoId);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        } else {
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    public List<ClassView> findClassViewByStudentNumber(String studentNumber) {
        List<ClassView> assignClassAllStudent = new ArrayList<>();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andStudentNumberEqualTo(studentNumber);
        List<User> users = userMapper.selectByExample(userExample);
        //学号没有对应的学生(学号错误)
        if (users.isEmpty()) {
            return null;
        }
        User student = users.get(0);
        ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(student.getClassInfo());

        userExample.clear();
        userExample.createCriteria().andClassInfoEqualTo(classInfo.getId());
        List<User> classAllStudent = userMapper.selectByExample(userExample);
        for (User user : classAllStudent) {
            assignClassAllStudent.add(new ClassView(user, classInfo));
        }
        return assignClassAllStudent;
    }
}
