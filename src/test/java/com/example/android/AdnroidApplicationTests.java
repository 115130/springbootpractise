package com.example.android;

import com.example.android.domain.ClassInfoExample;
import com.example.android.domain.User;
import com.example.android.domain.UserExample;
import com.example.android.mapper.ClassInfoMapper;
import com.example.android.mapper.LateReturnTableMapper;
import com.example.android.mapper.UserMapper;
import com.example.android.service.ClassInfoService;
import com.example.android.service.UserService;
import com.example.android.util.EncryptUtil;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@SpringBootTest
class AdnroidApplicationTests {

    @Resource
    UserMapper studentMapper;
    @Resource
    UserService loginService;

    @Resource
    ClassInfoService classInfoService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test1");
        System.out.println(loginService.login(user));
    }

    @Test
    void zxc(){
        User user = new User();
        user.setAdmin(0);
        user.setUsername("test12");
        user.setPassword(EncryptUtil.encrypt("321"));
        user.setStudentNumber("202102102010201012");
        studentMapper.insert(user);
        System.out.println();
    }

    @Test
    void ceshi(){

        UserExample userExample =new UserExample();

        List<User> users = studentMapper.selectByExample(userExample);
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("test");
//        UserExample userExample = new UserExample();
//        userExample.createCriteria().andUsernameIsNull();
//        long l = studentMapper.countByExample(userExample);
        System.out.println(users);
    }

    @Test
    void b(){
//        User user = new User();
//        user.setUsername("test");
//        user.setPassword("test");
//        System.out.println(loginService.login(user));
        System.out.println(EncryptUtil.encrypt("321"));

    }

    @Test
    void z (){
        User user = new User();
//        user.setUsername("test1");
//        user.setPassword("test");
//        System.out.println(loginService.register(user));
    }

    @Test
    void a(){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameIsNotNull();
        for (User user : studentMapper.selectByExample(userExample)) {
            System.out.println(user);
        }
        System.out.println(new Date());
    }

    @Test
    void c(){
        System.out.println(classInfoService.findClassViewByStudentNumber("201710244104"));
    }

    @Resource
    LateReturnTableMapper mapper;
    @Test
    void c1(){
//        ClassInfoExample classInfoExample = new ClassInfoExample();
//        classInfoExample.createCriteria().andClassesEqualTo("c");
//        System.out.println(mapper.selectByPrimaryKey(1L));
//            if (==null){
//                throw new RuntimeException();
//            }
          System.out.println(mapper.selectByPrimaryKey(3L));
//        System.out.println(mapper.selectByExample(classInfoExample));
    }

}
