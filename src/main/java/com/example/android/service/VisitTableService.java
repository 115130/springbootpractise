package com.example.android.service;

import com.example.android.domain.VisitTable;
import com.example.android.domain.VisitTableExample;
import com.example.android.mapper.VisitTableMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class VisitTableService {
    @Resource
    VisitTableMapper visitTableMapper;

    public List<VisitTable> findAllVisitTable(){
        VisitTableExample  visitTableExample = new VisitTableExample();
        visitTableExample.createCriteria().andIdIsNotNull();
        return visitTableMapper.selectByExample(visitTableExample);
    }

    public VisitTable findOneVisitTable(Long id){
        return visitTableMapper.selectByPrimaryKey(id);
    }

    public int addVisitTable(VisitTable visitTable){
        visitTable.setCreatedDate(new Date());
        return visitTableMapper.insert(visitTable);
    }

    public int updateVisitTable(VisitTable visitTable1){
        VisitTable visitTable = visitTableMapper.selectByPrimaryKey(visitTable1.getId());
        visitTable.setLastModify(new Date());
        visitTable.setAccessedHostel(visitTable1.getAccessedHostel());
        visitTable.setAccessedStudent(visitTable1.getAccessedStudent());
        visitTable.setPhone(visitTable1.getPhone());
        return visitTableMapper.updateByPrimaryKey(visitTable);
    }
    public int deleteVisitTable(Long visitTableId){
        return visitTableMapper.deleteByPrimaryKey(visitTableId);
    }
}
