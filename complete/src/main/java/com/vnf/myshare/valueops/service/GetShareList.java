package com.vnf.myshare.valueops.service;
import com.vnf.myshare.valueops.model.BO_FILEDSVALUE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vnf.myshare.valueops.model.BO_PROJECT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin
@RestController
public class GetShareList {
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(GetShareList.class);

    //search all project
    @GetMapping(value = "/getsharelist")
    public ShareList getShareList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BO_PROJECT[]> responseEntity = restTemplate.getForEntity("http://localhost:8182/getallproject", BO_PROJECT[].class);
        BO_PROJECT[] project = responseEntity.getBody();
        ShareList shareList =  new ShareList();
        for(int i =0; i < project.length; i++)
        {
            shareList.project = project[i];
            ResponseEntity<BO_FILEDSVALUE[]> responseEntityFieldsValue = restTemplate.getForEntity("http://localhost:8182/fieldvalue/"+project[i].getProjectid(), BO_FILEDSVALUE[].class);
            BO_FILEDSVALUE[] filedsvalues = responseEntityFieldsValue.getBody();
            for(int j =0; j<filedsvalues.length; j++)
            {
                shareList.fieldsvalue = filedsvalues;
            }

        }
        return shareList;
        //log.info(project.toString());
    }
}
