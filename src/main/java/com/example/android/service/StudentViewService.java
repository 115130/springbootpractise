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
        //?????????????????????????????????????????????
        if (user==null){
            return new ResponseData(ExceptionMsg.ParamError);
        }

        //???????????????????????????4????????????????????????????????????????????????????????????
        HostelExample hostelExample = new HostelExample();
        hostelExample.createCriteria().andCountLessThan(4);
        List<Hostel> hostels = hostelMapper.selectByExample(hostelExample);
        Collections.shuffle(hostels);
        //???????????????????????????????????????
        if (hostels.isEmpty()) {
            return new ResponseData("000005","??????????????????");
        }
        Hostel newHostel = hostels.get(0);

        //????????????????????????
        newHostel.setCount(newHostel.getCount() + 1);
        int i = hostelMapper.updateByPrimaryKey(newHostel);

        //????????????????????????????????????????????????
        Long hostelId = user.getHostel();
        Hostel oldHostel = hostelMapper.selectByPrimaryKey(hostelId);
        if (oldHostel==null){
            //????????????????????????????????????????????????????????????
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
        user.setStatus("?????????");
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
