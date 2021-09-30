package com.example.android.controller.teacher;

import com.example.android.domain.VisitTable;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.service.VisitTableService;
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
public class VisitTableController {
    @Resource
    VisitTableService visitTableService;

    @PostMapping("findAllVisitTable")
    public ResponseData findAllVisitTable() {
        List<VisitTable> allVisitTable = visitTableService.findAllVisitTable();
        return new ResponseData(ExceptionMsg.SUCCESS, allVisitTable);
    }
    @PostMapping("findOneVisitTable")
    public ResponseData findOneVisitTable(Long id) {
        VisitTable oneVisitTable = visitTableService.findOneVisitTable(id);
        return new ResponseData(ExceptionMsg.SUCCESS, oneVisitTable);
    }

    @PostMapping("addVisitTable")
    public ResponseData addVisitTable(VisitTable visitTable) {
        int i = visitTableService.addVisitTable(visitTable);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("updateVisitTable")
    public ResponseData updateVisitTable(VisitTable visitTable) {
        int i = visitTableService.updateVisitTable(visitTable);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("deleteVisitTable")
    public ResponseData deleteVisitTable(Long id) {
        int i = visitTableService.deleteVisitTable(id);
        if (i > 0) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

}
