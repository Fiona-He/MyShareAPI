package com.vnf.myshare.valueops.service;
import org.springframework.http.*;
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

    @PostMapping(value = "/ocrservice", produces="text/plain;charset=UTF-8", headers="Accept=text/plain;charset=UTF-8")
    public String  ocrService(@RequestParam("base64Data") String base64Data){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
        bodyMap.add("image", base64Data);

        MultiValueMap<String, String> bodyMap2 = new LinkedMultiValueMap<String, String>();
        bodyMap2.add("image", base64Data.replace("data:image/jpeg;base64,",""));
        bodyMap2.add("access_token", "24.e691f903764a066d06f48463aa8b5602.2592000.1529812854.282335-11269647");
        bodyMap2.add("recognize_granularity", "big");
        bodyMap2.add("probability", "false");
        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded;charset=UTF-8;"));
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(bodyMap2, headers);


        System.out.println("aliyun start");

        ResponseEntity<String> responseEntityPicURL = restTemplate.postForEntity("http://localhost:8182/aliyunfile",bodyMap,String.class);
//        restTemplate.exchange("http://localhost:8182/aliyunfile", HttpMethod.POST, new HttpEntity(objectMapper.writeValueAsString(request), headers), String.class);
        picURL = responseEntityPicURL.getBody();
        System.out.println(picURL);

        ResponseEntity<String> responseEntityJson = restTemplate.postForEntity("https://aip.baidubce.com/rest/2.0/ocr/v1/receipt", bodyMap2, String.class);
        System.out.println(responseEntityJson.getBody());
        RsultJson = responseEntityJson.getBody().toString();
        /*MultiValueMap<String, String> bodyMap2 = new LinkedMultiValueMap<String, String>();
        bodyMap2.add("picurl", picURL);
        ResponseEntity<String> responseEntityBaiduOcr = restTemplate.postForEntity("http://localhost:8182/baiduocr",bodyMap2,String.class);
        RsultJson = responseEntityBaiduOcr.getBody();
        System.out.println(RsultJson);*/

        return RsultJson;
    }
}
