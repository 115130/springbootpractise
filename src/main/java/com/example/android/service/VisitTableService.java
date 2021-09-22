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

    public int addVisitTable(VisitTable visitTable){
        visitTable.setCreatedDate(new Date());
        return visitTableMapper.insert(visitTable);
    }

    public int updateVisitTable(VisitTable visitTable){
        visitTable.setLastModify(new Date());
        return visitTableMapper.updateByPrimaryKey(visitTable);
    }
    public int deleteVisitTable(Long visitTableId){
        return visitTableMapper.deleteByPrimaryKey(visitTableId);
    }
}
