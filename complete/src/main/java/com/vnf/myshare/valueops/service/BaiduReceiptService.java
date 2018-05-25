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
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.util.UUID;


@CrossOrigin
@RestController
public class BaiduReceiptService {
    //设置APPID/AK/SK
    public static final String APP_ID = "11269646";
    public static final String API_KEY = "D4uMFkNC4e400OZQ5rqCWjsa";
    public static final String SECRET_KEY = "0Kim3otjCqWamp2w25SjNX93WhI52UrL";

    public static void main(String[] args) {

    }

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @PostMapping(value = "/baidureceiptocr")
    public String  baiduReceiptOcr(@RequestParam("base64Data") String base64Data, HttpServletRequest request, HttpServletResponse response) {

        JSONObject res = new JSONObject();

        try {
            AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

            // 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);

            HashMap<String, String> options = new HashMap<String, String>();
            options.put("recognize_granularity", "big");
            options.put("probability", "false");
            options.put("accuracy", "normal");
            options.put("detect_direction", "false");

            /*String dataPrix = "";
            String data = "";
            if (base64Data == null || "".equals(base64Data)) {
                throw new Exception("上传失败，上传图片数据为空");
            } else {
                String[] d = base64Data.split("base64,");
                if (d != null && d.length == 2) {
                    dataPrix = d[0];
                    data = d[1];
                } else {
                    throw new Exception("上传失败，数据不合法");
                }
            }
            String suffix = "";
            if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            } else {
                throw new Exception("上传图片格式不合法");
            }
            String tempFileName = UUID.randomUUID().toString() + suffix;*/

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(base64Data.replace("data:image/jpeg;base64,",""));

//            FileUtils.writeByteArrayToFile(new File(request.getServletContext().getRealPath("/upload"), tempFileName), bs);

//            System.out.print(request.getServletContext().getRealPath("/upload"));

            // 通用文字识别, 图片参数为远程url图片
            res = client.receipt(bs, options);
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
