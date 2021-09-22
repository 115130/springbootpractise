package com.example.android.service;

import com.example.android.domain.LateReturnTable;
import com.example.android.domain.LateReturnTableExample;
import com.example.android.mapper.LateReturnTableMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class LateReturnTableService {
    @Resource
    LateReturnTableMapper lateReturnTableMapper;

    public int addLateReturnTable(LateReturnTable lateReturnTable) {
        lateReturnTable.setCreatedDate(new Date());
        return lateReturnTableMapper.insert(lateReturnTable);
    }

    public List<LateReturnTable> findAllLateReturnTable() {
        LateReturnTableExample lateReturnTableExample = new LateReturnTableExample();
        lateReturnTableExample.createCriteria().andIdIsNotNull();
        return lateReturnTableMapper.selectByExample(lateReturnTableExample);
    }

    public int modifyReturnTable(LateReturnTable lateReturnTable) {
        lateReturnTable.setLastModify(new Date());
        return lateReturnTableMapper.updateByPrimaryKey(lateReturnTable);
    }

    public int modifyReturnTableToStudentId(Long studentId, Long lateReturnTableId) {
        LateReturnTableExample lateReturnTableExample = new LateReturnTableExample();
        lateReturnTableExample.createCriteria().andIdEqualTo(lateReturnTableId).andStudentIdEqualTo(studentId);
        LateReturnTable lateReturnTable = new LateReturnTable();
        lateReturnTable.setLastModify(new Date());
        lateReturnTable.setStudentId(studentId);
        return lateReturnTableMapper.updateByExample(lateReturnTable, lateReturnTableExample);
    }

    public int modifyReturnTableToHostel(Long hostel, Long lateReturnTableId) {
        LateReturnTableExample lateReturnTableExample = new LateReturnTableExample();
        lateReturnTableExample.createCriteria().andIdEqualTo(lateReturnTableId).andHostelEqualTo(hostel);
        LateReturnTable lateReturnTable = new LateReturnTable();
        lateReturnTable.setLastModify(new Date());
        lateReturnTable.setHostel(hostel);
        return lateReturnTableMapper.updateByExample(lateReturnTable, lateReturnTableExample);
    }

    public int deleteLateReturnTableByLateReturnTableId(Long lateReturnTableId) {
        return lateReturnTableMapper.deleteByPrimaryKey(lateReturnTableId);
    }

    public int deleteLateReturnTableByStudentId(Long studentId) {
        LateReturnTableExample lateReturnTableExample = new LateReturnTableExample();
        lateReturnTableExample.createCriteria().andStudentIdEqualTo(studentId);
        return lateReturnTableMapper.deleteByExample(lateReturnTableExample);
    }


}
