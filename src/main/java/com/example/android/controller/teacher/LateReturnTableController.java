package com.example.android.controller.teacher;

import com.example.android.domain.LateReturnTable;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.service.LateReturnTableService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("admin")
public class LateReturnTableController {
    @Resource
    LateReturnTableService lateReturnTableService;

    @PostMapping("addLateReturnTable")
    public ResponseData addLateReturnTable(LateReturnTable lateReturnTable) {
        int i = lateReturnTableService.addLateReturnTable(lateReturnTable);
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

    @PostMapping("modifyReturnTable")
    public ResponseData modifyReturnTable(LateReturnTable lateReturnTable) {
        int i = lateReturnTableService.modifyReturnTable(lateReturnTable);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("addLateReturnTable")
    public ResponseData modifyReturnTableToStudentId(Long hostel, Long lateReturnTableId) {
        int i = lateReturnTableService.modifyReturnTableToStudentId(hostel, lateReturnTableId);
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
    public ResponseData deleteLateReturnTableByLateReturnTableId(Long lateReturnTableId) {
        int i = lateReturnTableService.deleteLateReturnTableByLateReturnTableId(lateReturnTableId);
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
