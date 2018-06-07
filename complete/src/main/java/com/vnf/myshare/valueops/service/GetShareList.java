package com.vnf.myshare.valueops.service;
import com.vnf.myshare.valueops.controller.FILEDSVALUEController;
import com.vnf.myshare.valueops.controller.FILEDSVALUEIDController;
import com.vnf.myshare.valueops.controller.UserProjectController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vnf.myshare.valueops.model.BO_PROJECT;
import com.vnf.myshare.valueops.model.BO_FILEDSVALUEID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class GetShareList {
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(GetShareList.class);

    @Autowired
    FILEDSVALUEIDController filedsvalueidController;
    @Autowired
    UserProjectController userProjectController;
    //search all project
    @GetMapping(value = "/getsharelist/{uid}")
    public ArrayList<ShareDetail> getShareList(@PathVariable String uid) {
        //新建一個ArrayList，不能用數組因為初始化的時候要指定大小，不能用List因為不能轉換為json
        ArrayList<ShareDetail> shareDetails=new ArrayList<>();
        /*ArrayList<BO_PROJECT> fin_projects = new ArrayList<>();*/
        //獲取所有的拼單信息
        RestTemplate restTemplate = new RestTemplate();
        /*ResponseEntity<BO_FILEDSVALUEID[]> responseEntityFiledsValue = restTemplate.getForEntity("http://localhost:8182/fieldvalueall/1/FIELD2/"+uid, BO_FILEDSVALUEID[].class);
        BO_FILEDSVALUEID[] bo_filedsvalueids = responseEntityFiledsValue.getBody();

        for(int j=0; j< bo_filedsvalueids.length; j++) {
            System.out.println(bo_filedsvalueids[j].getField1());
            ResponseEntity<BO_PROJECT[]> responseEntityProject = restTemplate.getForEntity("http://localhost:8182/findByProjectid/"+bo_filedsvalueids[j].getField1(), BO_PROJECT[].class);
            BO_PROJECT[] bo_projects = responseEntityProject.getBody();
            for(int m=0; m<bo_projects.length; m++){
                fin_projects.add(bo_projects[m]);
            }
        }*/

        /*ResponseEntity<BO_PROJECT[]> responseEntityProject = restTemplate.getForEntity("http://localhost:8182/findByProjectid/", BO_PROJECT[].class);
        BO_PROJECT[] bo_projects = responseEntityProject.getBody();*/

        /*ResponseEntity<BO_PROJECT[]> responseEntity = restTemplate.getForEntity("http://localhost:8182/getallproject/"+uid, BO_PROJECT[].class);
        BO_PROJECT[] project = responseEntity.getBody();
        for(int m=0; m<project.length; m++){
            fin_projects.add(project[m]);
        };*/
        //循環每個拼單
        ResponseEntity<BO_PROJECT[]> responseEntity = restTemplate.getForEntity("http://localhost:8182/findProjects/"+uid, BO_PROJECT[].class);
        BO_PROJECT[] fin_projects = responseEntity.getBody();

        for(int i =0; i < fin_projects.length; i++)
        {
            ShareDetail shareDetail = new ShareDetail();

            shareDetail.Project = fin_projects[i];

            BO_FILEDSVALUEID record = new BO_FILEDSVALUEID();
            record.setProjectid(1);
            record.setField1(fin_projects[i].getProjectid().toString());
            //關注人數
            shareDetail.JoinUsers = filedsvalueidController.selectCountByField(record);
            //已經舉手，狀態是1的人數目
            shareDetail.RaiseHandCount1 = filedsvalueidController.selectCount(0,fin_projects[i].getProjectid(),"1");

            shareDetail.RaiseHandCount2 = filedsvalueidController.selectCount(0,fin_projects[i].getProjectid(),"2");

            shareDetail.RaiseHandCount3 = filedsvalueidController.selectCount(0,fin_projects[i].getProjectid(),"3");

            shareDetail.UserStatus = filedsvalueidController.getStatus(0,fin_projects[i].getProjectid(),uid);

            /*//獲取每個拼單的關注用戶數量
            ResponseEntity<Integer> responseEntityFieldsValue = restTemplate.getForEntity("http://localhost:8182/projectusers?projectid="+fin_projects[i].getProjectid(), Integer.class);
            shareDetail.JoinUsers = responseEntityFieldsValue.getBody();

            //獲取每個拼單的落單用戶數量
            ResponseEntity<Integer> responseEntityValueCount1 = restTemplate.getForEntity("http://localhost:8182/valuecount/"+fin_projects[i].getProjectid()+"/1", Integer.class);
            shareDetail.RaiseHandCount1 = responseEntityValueCount1.getBody();

            //獲取每個拼單的拼團用戶數量
            ResponseEntity<Integer> responseEntityValueCount2 = restTemplate.getForEntity("http://localhost:8182/valuecount/"+fin_projects[i].getProjectid()+"/2", Integer.class);
            shareDetail.RaiseHandCount2 = responseEntityValueCount2.getBody();

            //獲取每個拼單的拼團用戶數量
            ResponseEntity<Integer> responseEntityValueCount3 = restTemplate.getForEntity("http://localhost:8182/valuecount/"+fin_projects[i].getProjectid()+"/3", Integer.class);
            shareDetail.RaiseHandCount3 = responseEntityValueCount3.getBody();

            //獲取當前用戶的拼單狀態
            ResponseEntity<String> responseEntityUserStatus = restTemplate.getForEntity("http://localhost:8182/getstatus/"+fin_projects[i].getProjectid()+"/"+uid, String.class);
            shareDetail.UserStatus = responseEntityUserStatus.getBody();*/

            //把信息拼如返回結果
            shareDetails.add(shareDetail);
        }
        //返回完整拼單數據
        return shareDetails;
    }
}
