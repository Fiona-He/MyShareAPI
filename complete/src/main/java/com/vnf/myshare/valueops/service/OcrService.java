package com.vnf.myshare.valueops.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.json.*;
import org.springframework.util.*;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class OcrService {
    private String picURL = "";
    private String RsultJson = "";

    @PostMapping(value = "/ocrservice")
    public String  ocrService(@RequestParam("base64Data") String base64Data){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
        bodyMap.add("base64Data", base64Data);

        System.out.println("aliyun start");

        ResponseEntity<String> responseEntityPicURL = restTemplate.postForEntity("http://localhost:8182/aliyunfile",bodyMap,String.class);
//        restTemplate.exchange("http://localhost:8182/aliyunfile", HttpMethod.POST, new HttpEntity(objectMapper.writeValueAsString(request), headers), String.class);
        picURL = responseEntityPicURL.getBody();
        System.out.println(picURL);

        MultiValueMap<String, String> bodyMap2 = new LinkedMultiValueMap<String, String>();
        bodyMap2.add("picurl", picURL);
        ResponseEntity<String> responseEntityBaiduOcr = restTemplate.postForEntity("http://localhost:8182/baiduocr",bodyMap2,String.class);
        RsultJson = responseEntityBaiduOcr.getBody();
        System.out.println(RsultJson);

        return RsultJson;
    }
}
