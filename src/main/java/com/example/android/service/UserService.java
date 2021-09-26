package com.example.android.service;


import com.example.android.domain.*;

import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.mapper.ClassInfoMapper;
import com.example.android.mapper.UserMapper;
import com.example.android.util.EncryptUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    @Resource HostelService hostelService;

    @Resource
    ClassInfoMapper classInfoMapper;


    public User login(User user) {
        String password = user.getPassword();
        String username = user.getUsername();
        password = EncryptUtil.encrypt(password);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPasswordEqualTo(password).andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        //如果找不到对应的用户就返回空
        return null;
    }

    public ResponseData register(User user) {
        ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(user.getClassInfo());
        if (classInfo==null){
            return new ResponseData("000005","班级不存在，注册失败，请重新填写班级");
        }

        ArrayList<String> strings = new ArrayList<String>(){{
            add(user.getUsername());
        }};
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameIn(strings);


        long userCount = userMapper.countByExample(userExample);
        if (userCount > 0) {
            return new ResponseData(ExceptionMsg.UserNameError);
        }
        userExample.clear();
        userExample.createCriteria().andStudentNumberEqualTo(user.getStudentNumber());
        long l = userMapper.countByExample(userExample);

        if (l>0){
            return new ResponseData("000004","学号错误");
        }

        if (Strings.trimToNull(user.getUsername()) != null) {
            user.setPassword(EncryptUtil.encrypt(user.getPassword()));
            user.setCreatedDate(new Date());
            user.setStatus("未入住");
            user.setAdmin(0);
            userMapper.insert(user);
            int i = hostelService.autoAllotHostel(user);
            switch (i){
                case 0: return new ResponseData(ExceptionMsg.SUCCESS);
                case -1: return new ResponseData("000005","用户不存在");
                case -2: return new ResponseData("000006","宿舍已满");
            }
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    public int existUserName(String username){
        UserExample existUsername = new UserExample();
        ArrayList<String> arrayList = new ArrayList<String>(){{
            add(username);
        }};
        existUsername.createCriteria().andUsernameIn(arrayList);
        if (userMapper.countByExample(existUsername)<=0){
            User existUser = new User();
            existUser.setId(-1L);
            return -1;
        }
        return 0;
    }

    public User findOneById(Long userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<User> findAllTeacher(){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIsNotNull().andAdminEqualTo(1);
        return userMapper.selectByExample(userExample);
    }



}
