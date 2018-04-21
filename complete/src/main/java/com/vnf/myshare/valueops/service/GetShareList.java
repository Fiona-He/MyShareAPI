package com.vnf.myshare.valueops.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vnf.myshare.valueops.model.BO_PROJECT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.http.ResponseEntity;

@CrossOrigin
@RestController
public class GetShareList {
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(GetShareList.class);

    //search all project
    @GetMapping(value = "/getsharelist")
    public ShareDetail[] getShareList() {

        ShareDetail[] shareDetails = new ShareDetail[10];

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BO_PROJECT[]> responseEntity = restTemplate.getForEntity("http://localhost:8182/getallproject", BO_PROJECT[].class);
        BO_PROJECT[] project = responseEntity.getBody();

        for(int i =0; i < project.length; i++)
        {
            ShareDetail shareDetail = new ShareDetail();
            shareDetail.project = project[i];
            System.out.println(i);
            ResponseEntity<Integer> responseEntityFieldsValue = restTemplate.getForEntity("http://localhost:8182/projectusers?projectid="+project[i].getProjectid(), Integer.class);
            shareDetail.projectusers = responseEntityFieldsValue.getBody();

            shareDetails[i] = shareDetail;

        }

        return shareDetails;
    }
}
