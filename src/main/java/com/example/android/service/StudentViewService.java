package com.example.android.service;

import com.example.android.domain.*;
import com.example.android.domain.view.StudentView;
import com.example.android.mapper.ClassInfoMapper;
import com.example.android.mapper.HostelMapper;
import com.example.android.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Slf4j
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
        userExample.createCriteria().andUsernameIsNotNull().andAdminEqualTo(0);
        List<User> users = userMapper.selectByExample(userExample);
        log.error(users.toString());
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
    public User selectUserByUserId(Long id) {
        return userMapper.selectByPrimaryKey(id);

    }

    public int deleteStudent(String studentNumber) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andStudentNumberEqualTo(studentNumber);
        return userMapper.deleteByExample(userExample);
    }

    public int deleteStudentById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        Hostel hostel = hostelMapper.selectByPrimaryKey(user.getHostel());
        hostel.setCount(hostel.getCount()-1);
        hostelMapper.updateByPrimaryKey(hostel);
        return userMapper.deleteByPrimaryKey(id);
    }



    public int updateUserNameAndPassword(User user){
        user.setLastModify(new Date());
        return userMapper.updateByPrimaryKey(user);
    }

    public int updateStudentHostel(User user) {
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
        if (user==null){
            return -2;
        }
        Long hostelId = user.getHostel();
        Hostel oldHostel = hostelMapper.selectByPrimaryKey(hostelId);
        if (oldHostel==null){
            return -3;
        }
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
        studentView.setUsername(user.getUsername());
        studentView.setStudentNumber(user.getStudentNumber());
        studentView.setId(user.getId());

        studentView.setStatus(user.getStatus());
        Hostel hostel = hostelMapper.selectByPrimaryKey(user.getHostel());
        ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(user.getClassInfo());
        studentView.setHostel(hostel.getId());
        studentView.setClassInfo(classInfo.getId());
        return studentView;
    }

}
