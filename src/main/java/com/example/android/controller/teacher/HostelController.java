package com.example.android.controller.teacher;

import com.example.android.domain.Hostel;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.service.HostelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("admin")
public class HostelController {

    @Resource
    HostelService hostelService;

    @PostMapping("findAllHostel")
    public ResponseData findAllHostel(){
        List<Hostel> allHostel = hostelService.findAllHostel();
        return new ResponseData(ExceptionMsg.SUCCESS,allHostel);
    }

    @PostMapping("addHostel")
    public ResponseData addHostel(Hostel hostel){
        int i = hostelService.addHostel(hostel);
        if (i==0){
            return new ResponseData(ExceptionMsg.SUCCESS);
        }else if (i==-2){
            return new ResponseData(ExceptionMsg.ParamError);
        }else if(i==-1){
            return new ResponseData("000013","添加失败");
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("score")
    public ResponseData score(Hostel hostel){
        int score = hostelService.updateHostel(hostel);
        if (score<0){
            return new ResponseData(ExceptionMsg.FAILED);
        }
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    @PostMapping("updateHostel")
    public ResponseData updateHostel(Hostel hostel){
        int score = hostelService.updateHostel(hostel);
        if (score<0){
            return new ResponseData(ExceptionMsg.FAILED);
        }
        return new ResponseData(ExceptionMsg.SUCCESS);
    }
}
