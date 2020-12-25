package com.atguigu.crowd.util;

import com.atguigu.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 项目通用类
 */
public class CrowdUtil {
    /**
     * 判断当前请求是否为ajax请求
     * true：是 false:不是
     **/
    public static boolean judgeRequestType(HttpServletRequest request){
        // 1.获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Request-With");
        // 2.判断
        return acceptHeader != null && acceptHeader.contains("application/json") || (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }

    /**
     *  明文进行md5加密方法
     * @param source
     * @return  密文字符串
     */
    public static String md5(String source){
        // 1.判断source是否有效
        if(source == null || source.length() == 0){
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }

        // 2.创建MessageDigest对象
        String algorithm = "md5";

        try{
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 3.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();

            // 4.执行加密
            byte[] output = messageDigest.digest(input);

            // 5.创建BigInteger对象存储
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum,output);

            // 6.按照16进制将bigInteger转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
