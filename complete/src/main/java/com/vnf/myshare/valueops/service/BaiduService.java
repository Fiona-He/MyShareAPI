package com.vnf.myshare.valueops.service;

import jdk.nashorn.internal.objects.NativeJSON;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.*;
import org.springframework.http.*;
import org.springframework.util.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class BaiduService {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @PostMapping(value = "/baiduocr")
    public String  baiduOcr(@RequestParam("image") String image) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("access_token", "24.209429b1ec79e8fe6d4a13c21e2dc536.2592000.1529488135.282335-11269647");
        System.out.println(image);
        params.add("image", image);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://aip.baidubce.com/rest/2.0/ocr/v1/receipt", requestEntity , String.class );

        System.out.println(response.toString());
        return response.toString();
    }
}
