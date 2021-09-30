package com.example.android.service;

import com.example.android.domain.*;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.domain.view.StudentView;
import com.example.android.mapper.ClassInfoMapper;
import com.example.android.mapper.HostelMapper;
import com.example.android.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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


    public ResponseData updateStudentHostel(User user) {
        //如果所有信息为空将返回错误信息
        if (user==null){
            return new ResponseData(ExceptionMsg.ParamError);
        }

        //找到所有住人数小于4的宿舍并排序再获取人数最多的（少）的宿舍
        HostelExample hostelExample = new HostelExample();
        hostelExample.createCriteria().andCountLessThan(4);
        List<Hostel> hostels = hostelMapper.selectByExample(hostelExample);
        Collections.shuffle(hostels);
        //如果没有空余宿舍就错误信息
        if (hostels.isEmpty()) {
            return new ResponseData("000005","没有空宿舍了");
        }
        Hostel newHostel = hostels.get(0);

        //更新用户类宿舍号
        newHostel.setCount(newHostel.getCount() + 1);
        int i = hostelMapper.updateByPrimaryKey(newHostel);

        //等宿舍更换完成后将旧宿舍信息更新
        Long hostelId = user.getHostel();
        Hostel oldHostel = hostelMapper.selectByPrimaryKey(hostelId);
        if (oldHostel==null){
            //找不到老宿舍或者未分配宿舍时进行自动分配
            return assignHostel(user,newHostel);
        }
        oldHostel.setCount(oldHostel.getCount() - 1);
        hostelMapper.updateByPrimaryKey(oldHostel);

        user.setHostel(newHostel.getId());
        userMapper.updateByPrimaryKey(user);
        if (i>0){
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    private ResponseData  assignHostel(User user,Hostel hostel){
        user.setHostel(hostel.getId());
        user.setStatus("已入住");
        int i = userMapper.updateByPrimaryKey(user);
        hostel.setCount(hostel.getCount()+1);
        int i1 = hostelMapper.updateByPrimaryKey(hostel);
        if ((i|i1)<=0){
            return new ResponseData(ExceptionMsg.FAILED);
        }
        return new ResponseData(ExceptionMsg.SUCCESS);

    }

    private StudentView getStudentViewByUserName(User user) {
        StudentView studentView = new StudentView();
        studentView.setUsername(user.getUsername());
        studentView.setStudentNumber(user.getStudentNumber());
        studentView.setId(user.getId());

        studentView.setStatus(user.getStatus());
        Hostel hostel = hostelMapper.selectByPrimaryKey(user.getHostel());
        ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(user.getClassInfo());

        if(hostel!=null){
            studentView.setHostel(hostel);
        }
        if (classInfo!=null){
            studentView.setClassInfo(classInfo);
        }
        return studentView;
    }


}
