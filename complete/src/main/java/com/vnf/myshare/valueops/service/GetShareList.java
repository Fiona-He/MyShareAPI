package com.vnf.myshare.valueops.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vnf.myshare.valueops.model.BO_PROJECT;
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

    //search all project
    @GetMapping(value = "/getsharelist/{uid}")
    public ArrayList<ShareDetail> getShareList(@PathVariable String uid) {
        //新建一個ArrayList，不能用數組因為初始化的時候要指定大小，不能用List因為不能轉換為json
        ArrayList<ShareDetail> shareDetails=new ArrayList<>();
        //獲取所有的拼單信息
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BO_PROJECT[]> responseEntity = restTemplate.getForEntity("http://localhost:8182/getallproject/"+uid, BO_PROJECT[].class);
        BO_PROJECT[] project = responseEntity.getBody();
        //循環每個拼單
        for(int i =0; i < project.length; i++)
        {
            ShareDetail shareDetail = new ShareDetail();

            shareDetail.Project = project[i];
            //獲取每個拼單的關注用戶數量
            ResponseEntity<Integer> responseEntityFieldsValue = restTemplate.getForEntity("http://localhost:8182/projectusers?projectid="+project[i].getProjectid(), Integer.class);
            shareDetail.JoinUsers = responseEntityFieldsValue.getBody();

            //獲取每個拼單的落單用戶數量
            ResponseEntity<Integer> responseEntityValueCount1 = restTemplate.getForEntity("http://localhost:8182/valuecount/"+project[i].getProjectid()+"/1", Integer.class);
            shareDetail.RaiseHandCount1 = responseEntityValueCount1.getBody();

            //獲取每個拼單的拼團用戶數量
            ResponseEntity<Integer> responseEntityValueCount2 = restTemplate.getForEntity("http://localhost:8182/valuecount/"+project[i].getProjectid()+"/2", Integer.class);
            shareDetail.RaiseHandCount2 = responseEntityValueCount2.getBody();

            //獲取每個拼單的拼團用戶數量
            ResponseEntity<Integer> responseEntityValueCount3 = restTemplate.getForEntity("http://localhost:8182/valuecount/"+project[i].getProjectid()+"/3", Integer.class);
            shareDetail.RaiseHandCount3 = responseEntityValueCount3.getBody();

            //獲取當前用戶的拼單狀態
            ResponseEntity<String> responseEntityUserStatus = restTemplate.getForEntity("http://localhost:8182/getstatus/"+project[i].getProjectid()+"/"+uid, String.class);
            shareDetail.UserStatus = responseEntityUserStatus.getBody();
            //把信息拼如返回結果
            shareDetails.add(shareDetail);
        }
        //返回完整拼單數據
        return shareDetails;
    }
}
