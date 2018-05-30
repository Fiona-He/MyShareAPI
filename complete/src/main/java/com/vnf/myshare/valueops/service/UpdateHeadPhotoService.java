package com.vnf.myshare.valueops.service;

import com.vnf.myshare.valueops.model.Friends;
import com.vnf.myshare.valueops.model.PhotoUrlUpdateRequest;
import com.vnf.myshare.valueops.model.VALUEBYFIELD;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
public class UpdateHeadPhotoService {
    @PutMapping(value = "/photourl")
    public String  ocrService(@RequestBody PhotoUrlUpdateRequest photoUrlUpdateRequest) {

        RestTemplate restTemplate = new RestTemplate();
        VALUEBYFIELD valuebyfield = new VALUEBYFIELD();
        valuebyfield.setProjectid(1);
        valuebyfield.setFieldid1(3);
        valuebyfield.setFieldid2(2);
        valuebyfield.setFieldvalue1(photoUrlUpdateRequest.getPhotourl());
        valuebyfield.setFieldvalue2(photoUrlUpdateRequest.getUid());

        restTemplate.put("http://localhost:8182/valuebyfieldid", valuebyfield);

        Friends friends = new Friends();
        friends.setBfuid(photoUrlUpdateRequest.getUid());
        friends.setBfphotourl(photoUrlUpdateRequest.getPhotourl());

        restTemplate.put("http://localhost:8182/updatefriendphotourl", friends);

        return "0";
    }
}
