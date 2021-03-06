package com.example.android.service;

import com.example.android.domain.Hostel;
import com.example.android.domain.HostelExample;
import com.example.android.domain.User;
import com.example.android.domain.UserExample;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.domain.view.HostelView;
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
public class HostelService {
    @Resource
    HostelMapper hostelMapper;

    @Resource
    UserMapper userMapper;

    /**
     * 自动为学生安排宿舍
     *
     * @param user 学生参数
     * @return
     */
    public int autoAllotHostel(User user) {
        HostelExample hostelExample = new HostelExample();
        //找到所有住人数小于4的宿舍并排序再获取人数最多的（少）的宿舍
        hostelExample.createCriteria().andCountLessThan(4);
        List<Hostel> hostels = hostelMapper.selectByExample(hostelExample);
        if (hostels.isEmpty()) {
            return -2;
        }
        hostels.sort(Comparator.comparingInt(Hostel::getCount));
        Hostel hostel = hostels.get(0);
        hostel.setCount(hostel.getCount() + 1);
        hostelMapper.updateByPrimaryKey(hostel);

        //获取用户名对应的用户
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> users = userMapper.selectByExample(userExample);

        //分配成功
        if (!users.isEmpty()) {
            User user1 = users.get(0);
            user1.setHostel(hostel.getId());
            user1.setStatus("已入住");
            userMapper.updateByPrimaryKey(user1);
            List<String> list = new ArrayList<>();
            list.add(user.getUsername());
            userExample.clear();
            userExample.createCriteria().andUsernameIn(list);
            userMapper.updateByExample(user1, userExample);
            return 0;
        }
        //如果用户不存在
        return -1;
    }

    /**
     * 查看id不为空的宿舍
     *
     * @return
     */
    public List<Hostel> findAllHostel() {
        HostelExample hostelExample = new HostelExample();
        hostelExample.createCriteria().andIdIsNotNull();
        return hostelMapper.selectByExample(hostelExample);
    }

    public Hostel findOneHostel(Long id) {
        return hostelMapper.selectByPrimaryKey(id);
    }

    public int addHostel() {
        Hostel hostel = new Hostel();
        hostel.setGrade(0.0);
        hostel.setCount(0);
        hostel.setCreatedDate(new Date());
        int insert = hostelMapper.insert(hostel);
        //添加成功
        if (insert > 0) {
            return 1;
            //添加失败
        } else if (insert == 0) {
            return -1;
        }
        //操作失败
        return -3;
    }

    public List<HostelView> findHostelByHostelId(Long hostelId) {
        List<HostelView> hostelViewList = new ArrayList<>();
        Hostel hostel = hostelMapper.selectByPrimaryKey(hostelId);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andHostelEqualTo(hostelId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.isEmpty()) {
            return null;
        }
        for (User user : users) {
            hostelViewList.add(new HostelView(user, hostel));
        }
        return hostelViewList;
    }

    public int updateHostel(Hostel hostel) {
        Long id = hostel.getId();
        Hostel hostel1 = hostelMapper.selectByPrimaryKey(id);
        hostel1.setLastModify(new Date());
        hostel1.setGrade(hostel.getGrade());
        return hostelMapper.updateByPrimaryKey(hostel1);
    }

    public int updateHostelByCount(Hostel hostel) {
        Long id = hostel.getId();
        Hostel hostel1 = hostelMapper.selectByPrimaryKey(id);
        hostel1.setCount(hostel.getCount());
        hostel1.setLastModify(new Date());
        return hostelMapper.updateByPrimaryKey(hostel1);
    }

    public ResponseData deleteHostelById(Long id) {
        int i = hostelMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }


}
