package com.example.android.controller.teacher;

import com.example.android.domain.LateReturnTable;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.service.LateReturnTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("admin")
public class LateReturnTableController {
    @Resource
    LateReturnTableService lateReturnTableService;

    @PostMapping("addLateReturnTable")
    public ResponseData addLateReturnTable(Long hostel,Long studentId) {
        int i = lateReturnTableService.addLateReturnTable(hostel,studentId);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("findAllLateReturnTable")
    public ResponseData findAllLateReturnTable() {
        List<LateReturnTable> allLateReturnTable = lateReturnTableService.findAllLateReturnTable();
        return new ResponseData(ExceptionMsg.SUCCESS, allLateReturnTable);
    }

    @PostMapping("findOneLateReturnTable")
    public ResponseData findOneLateReturnTable(Long id) {
        LateReturnTable oneLateReturnTable = lateReturnTableService.findOneLateReturnTable(id);
        return new ResponseData(ExceptionMsg.SUCCESS, oneLateReturnTable);
    }

    @PostMapping("modifyReturnTable")
    public ResponseData modifyReturnTable(LateReturnTable lateReturnTable) {
        int i = lateReturnTableService.modifyReturnTable(lateReturnTable);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("modifyReturnTableToStudentId")
    public ResponseData modifyReturnTableToStudentId(Long studentId, Long lateReturnTableId) {
        int i = lateReturnTableService.modifyReturnTableToStudentId(studentId, lateReturnTableId);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("modifyReturnTableToHostel")
    public ResponseData modifyReturnTableToHostel(Long hostel, Long lateReturnTableId) {
        int i = lateReturnTableService.modifyReturnTableToHostel(hostel, lateReturnTableId);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("deleteLateReturnTableByLateReturnTableId")
    public ResponseData deleteLateReturnTableByLateReturnTableId(Long id) {
        int i = lateReturnTableService.deleteLateReturnTableByLateReturnTableId(id);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("deleteLateReturnTableByStudentId")
    public ResponseData deleteLateReturnTableByStudentId(Long studentId) {
        int i = lateReturnTableService.deleteLateReturnTableByStudentId(studentId);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }
}
