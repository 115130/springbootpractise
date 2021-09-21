package com.example.android.service;

import com.example.android.domain.ClassInfo;
import com.example.android.domain.ClassInfoExample;
import com.example.android.domain.VisitTable;
import com.example.android.domain.VisitTableExample;
import com.example.android.mapper.ClassInfoMapper;
import com.example.android.mapper.VisitTableMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassInfoService {
    @Resource
    ClassInfoMapper classInfoMapper;

    public List<ClassInfo> findAllClassInfo(){
        ClassInfoExample classInfoExample = new ClassInfoExample();
        classInfoExample.createCriteria().andIdIsNotNull();
        return classInfoMapper.selectByExample(classInfoExample);
    }

    public int addClassInfo(ClassInfo classInfo){
        return classInfoMapper.insert(classInfo);
    }

    public int updateClassInfo(ClassInfo classInfo){
        return classInfoMapper.updateByPrimaryKey(classInfo);
    }
    public int deleteClassInfo(Long classInfoId){
        return classInfoMapper.deleteByPrimaryKey(classInfoId);
    }
}
