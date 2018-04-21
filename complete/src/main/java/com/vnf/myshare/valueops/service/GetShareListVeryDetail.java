package com.vnf.myshare.valueops.service;

import com.vnf.myshare.valueops.model.BO_FILEDSVALUE;
import com.vnf.myshare.valueops.model.BO_FILEDSVALUE;
import com.vnf.myshare.valueops.model.BO_PROJECT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
public class GetShareListVeryDetail {
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(GetShareListVeryDetail.class);

    //search all project
    @GetMapping(value = "/getsharelistverydtail")
    public ShareListVeryDetail getShareList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BO_PROJECT[]> responseEntity = restTemplate.getForEntity("http://localhost:8182/getallproject", BO_PROJECT[].class);
        BO_PROJECT[] project = responseEntity.getBody();
        ShareListVeryDetail shareList =  new ShareListVeryDetail();
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
    }
}
