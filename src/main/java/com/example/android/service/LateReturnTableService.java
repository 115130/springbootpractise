package com.example.android.service;

import com.example.android.domain.LateReturnTable;
import com.example.android.domain.LateReturnTableExample;
import com.example.android.mapper.LateReturnTableMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class LateReturnTableService {
    @Resource
    LateReturnTableMapper lateReturnTableMapper;

    public int addLateReturnTable(Long hostel,Long studentId) {
        LateReturnTable lateReturnTable= new LateReturnTable();
        lateReturnTable.setHostel(hostel);
        lateReturnTable.setStudentId(studentId);
        lateReturnTable.setCreatedDate(new Date());
        return lateReturnTableMapper.insert(lateReturnTable);
    }

    public List<LateReturnTable> findAllLateReturnTable() {
        LateReturnTableExample lateReturnTableExample = new LateReturnTableExample();
        lateReturnTableExample.createCriteria().andIdIsNotNull();
        return lateReturnTableMapper.selectByExample(lateReturnTableExample);
    }

    public int modifyReturnTable(LateReturnTable lateReturnTable1) {
        LateReturnTable lateReturnTable = lateReturnTableMapper.selectByPrimaryKey(lateReturnTable1.getId());
        if (lateReturnTable==null){
            return -1;
        }
        lateReturnTable.setStudentId(lateReturnTable1.getStudentId());
        lateReturnTable.setHostel(lateReturnTable1.getHostel());
        lateReturnTable.setLastModify(new Date());
        log.error(lateReturnTable.toString());
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
