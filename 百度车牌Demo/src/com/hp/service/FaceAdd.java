package com.hp.service;

import com.hp.utils.Base64Util;
import com.hp.utils.FileUtil;
import com.hp.utils.HttpUtil;

import java.net.URLEncoder;

/**
 * 人脸注册
 */
public class FaceAdd {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String add() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            /*Map<String, Object> map = new HashMap<>();
            map.put("image", "E:\\桌面\\wy\\图片\\050C916425B3D43BF84CBCD69819058D.jpg");
            map.put("group_id", "xuexi");
            map.put("user_id", 3);
            map.put("user_info", "roy");
            map.put("liveness_control", "NORMAL");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);*/

            // 本地文件路径
            String filePath ="E:\\桌面\\wy\\图片\\050C916425B3D43BF84CBCD69819058D.jpg";

            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            //user_id是用户名
            String param = "user_id=" + "roy" + "&user_info=" + "royhead" + "&group_id=" + "xuexi" + "&image_type=BASE64" + "&image=" + imgParam ;
            AuthService auth = new AuthService();

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
           // String accessToken = "[调用鉴权接口获取的token]";
            String accessToken = AuthService.getAuth();
            System.out.println("accessToken = " + accessToken);

            String result = HttpUtil.post(url, accessToken,param);
            System.out.println("addface:"+result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
