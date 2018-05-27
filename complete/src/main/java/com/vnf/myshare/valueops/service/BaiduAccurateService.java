package com.vnf.myshare.valueops.service;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


@CrossOrigin
@RestController
public class BaiduAccurateService {
    //设置APPID/AK/SK
    public static final String APP_ID = "11269646";
    public static final String API_KEY = "D4uMFkNC4e400OZQ5rqCWjsa";
    public static final String SECRET_KEY = "0Kim3otjCqWamp2w25SjNX93WhI52UrL";

    public static void main(String[] args) {

    }

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @PostMapping(value = "/baiduaccurateocr")
    public String  baiduAccurateOcr(@RequestParam("base64Data") String base64Data, HttpServletRequest request, HttpServletResponse response) {

        JSONObject res = new JSONObject();

        try {
            AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

            // 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);

            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("detect_direction", "true");
            options.put("probability", "false");

            byte[] bs = Base64Utils.decodeFromString(base64Data.replace("data:image/jpeg;base64,",""));

            // 参数为本地图片二进制数组
            res = client.basicAccurateGeneral(bs, options);
            System.out.println(res.toString(2));

        /*} catch (IOException e) {
            e.printStackTrace();*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return res.toString();
    }
}
