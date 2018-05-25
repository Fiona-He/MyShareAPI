package com.vnf.myshare.valueops.service;

import jdk.nashorn.internal.objects.NativeJSON;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.*;
import org.springframework.http.*;
import org.springframework.util.*;
import java.util.*;
import com.baidu.aip.ocr.AipOcr;
import org.json.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class BaiduService {
    //设置APPID/AK/SK
    public static final String APP_ID = "11269646";
    public static final String API_KEY = "D4uMFkNC4e400OZQ5rqCWjsa";
    public static final String SECRET_KEY = "0Kim3otjCqWamp2w25SjNX93WhI52UrL";

    public static void main(String[] args) {

    }

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @PostMapping(value = "/baiduocr")
    public String  baiduOcr(@RequestParam("picurl") String picurl) {
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "false");
        // 参数为本地图片路径
        /*String image2 = "http://pic92.huitu.com/res/20161226/582898_20161226213354506017_1.jpg";
        JSONObject res = client.basicGeneral(image2, options);
        System.out.println(res.toString(2));*/

        // 参数为本地图片二进制数组
        /*byte[] file = readImageFile(image);
        res = client.basicGeneral(file, options);
        System.out.println(res.toString(2));*/


        // 通用文字识别, 图片参数为远程url图片
        System.out.println("baidu:"+picurl);
        JSONObject res = client.basicGeneralUrl(picurl, options);
        System.out.println(res.toString(2));

        return res.toString();


        /*System.out.println("baidu");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("access_token", "24.209429b1ec79e8fe6d4a13c21e2dc536.2592000.1529488135.282335-11269647");
        System.out.println(image);
        params.add("image", image);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity("https://aip.baidubce.com/rest/2.0/ocr/v1/receipt", requestEntity , Map.class );

        System.out.println(response.toString());
        return response.toString();*/
    }
}
