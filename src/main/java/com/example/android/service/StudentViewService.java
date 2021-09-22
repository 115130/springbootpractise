package com.example.android.service;

import com.example.android.domain.*;
import com.example.android.domain.view.StudentView;
import com.example.android.mapper.ClassInfoMapper;
import com.example.android.mapper.HostelMapper;
import com.example.android.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class StudentViewService {
    @Resource
    UserMapper userMapper;

    @Resource
    HostelMapper hostelMapper;

    @Resource
    ClassInfoMapper classInfoMapper;

    public List<StudentView> findAllStudent() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameIsNotNull();
        List<User> users = userMapper.selectByExample(userExample);
        ArrayList<StudentView> studentViews = new ArrayList<>();
        for (User user : users) {
            studentViews.add(getStudentViewByUserName(user));
        }
        return studentViews;
    }

    public StudentView findStudentByUserId(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return getStudentViewByUserName(user);
    }

    public int deleteStudent(String studentNumber) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andStudentNumberEqualTo(studentNumber);
        return userMapper.deleteByExample(userExample);
    }

    public int updateStudentClassInfo(Long studentId, Long classInfoId) {
        User user = new User();
        user.setId(studentId);
        user.setClassInfo(classInfoId);
        user.setLastModify(new Date());
        return userMapper.updateByPrimaryKey(user);
    }

    public int updateStudentHostel( Long userId) {
        HostelExample hostelExample = new HostelExample();
        //找到所有住人数小于4的宿舍并排序再获取人数最多的（少）的宿舍
        hostelExample.createCriteria().andCountLessThan(4);
        List<Hostel> hostels = hostelMapper.selectByExample(hostelExample);
        hostels.sort(Comparator.comparingInt(Hostel::getCount));

        //如果没有空余宿舍就返回-3
        if (hostels.isEmpty()) {
            return -1;
        }
        Hostel newHostel = hostels.get(0);

        User user = userMapper.selectByPrimaryKey(userId);
        Long hostelId = user.getHostel();
        Hostel oldHostel = hostelMapper.selectByPrimaryKey(hostelId);
        oldHostel.setCount(oldHostel.getCount() - 1);
        //更新用户类宿舍号
        user.setHostel(newHostel.getId());
        userMapper.updateByPrimaryKey(user);

        newHostel.setCount(newHostel.getCount() + 1);
        int i = hostelMapper.updateByPrimaryKey(newHostel);

        hostelMapper.updateByPrimaryKey(oldHostel);
        return i;
    }

    private StudentView getStudentViewByUserName(User user) {
        StudentView studentView = new StudentView();
        studentView.setStudentNumber(user.getStudentNumber());
        studentView.setId(user.getId());
        studentView.setStatus(user.getStatus());
        Hostel hostel = hostelMapper.selectByPrimaryKey(user.getHostel());
        ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(user.getClassInfo());
        studentView.setHostel(hostel);
        studentView.setClassInfo(classInfo);
        return studentView;
    }

}
