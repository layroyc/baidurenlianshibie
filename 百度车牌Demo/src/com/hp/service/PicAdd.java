package com.hp.service;

import com.hp.utils.Base64Util;
import com.hp.utils.FileUtil;
import com.hp.utils.HttpUtil;

import java.net.URLEncoder;

public class PicAdd {
    public static String pAdd() throws Exception {
        //请求URL
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
        //本地的一张图片
        String filePath = "E:\\桌面\\wy车牌.jpg";
        //然后 读取这张图片
        byte[] imgData = FileUtil.readFileByBytes(filePath);
        //base64 转码
        String imgStr = Base64Util.encode(imgData);
        //让 url 成为 utf-8
        String imgParam = URLEncoder.encode(imgStr,"utf-8");
        //拼接参数
        String param = "&image_type=BASE"+"&image="+imgParam;
        //获取token
        String accessToken = AuthService.getAuth();
        System.out.println("accessToken = " + accessToken);
        //获取到token后 可以请求 网址啦
        String result = HttpUtil.post(url, accessToken, param);
        System.out.println("result = " + result);
        return result;

    }

}
