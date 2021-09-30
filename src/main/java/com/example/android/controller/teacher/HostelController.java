package com.example.android.controller.teacher;

import com.example.android.domain.Hostel;
import com.example.android.domain.result.ExceptionMsg;
import com.example.android.domain.result.ResponseData;
import com.example.android.domain.view.HostelView;
import com.example.android.service.HostelService;
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
public class HostelController {

    @Resource
    HostelService hostelService;

    @PostMapping("findHostelByHostelId")
    public ResponseData findHostelByHostelId(Long hostelId){
        List<HostelView> hostelByHostelId = hostelService.findHostelByHostelId(hostelId);
        if (hostelByHostelId==null){
            return new ResponseData(ExceptionMsg.ParamError);
        }
        return new ResponseData(ExceptionMsg.SUCCESS,hostelByHostelId);
    }

    @PostMapping("findHostelInfoById")
    public ResponseData v(Long id){
        Hostel oneHostel = hostelService.findOneHostel(id);
        if (oneHostel==null){
            return new ResponseData(ExceptionMsg.ParamError);
        }
        return new ResponseData(ExceptionMsg.SUCCESS,oneHostel);
    }

    @PostMapping("findAllHostel")
    public ResponseData findAllHostel(){
        List<Hostel> allHostel = hostelService.findAllHostel();
        return new ResponseData(ExceptionMsg.SUCCESS,allHostel);
    }

    @PostMapping("addHostel")
    public ResponseData addHostel(){
        int i = hostelService.addHostel();
        if (i>0){
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
        if (score>0){
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("updateHostel")
    public ResponseData updateHostel(Hostel hostel){
        int score = hostelService.updateHostelByCount(hostel);
        if (score>0){
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        return new ResponseData(ExceptionMsg.FAILED);
    }

    @PostMapping("deleteHostelById")
    public ResponseData deleteHostelById(Long id){
        return hostelService.deleteHostelById(id);
    }

}
