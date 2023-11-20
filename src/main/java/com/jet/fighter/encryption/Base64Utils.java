package com.jet.fighter.encryption;

import com.jet.fighter.string.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * base64加密
 * <p>
 * Description: {@link Base64Utils }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 16:28
 * @Version: v1.0
 */
public class Base64Utils {

    private Base64Utils() {
    }

    /**
     * Description: base64加密
     *
     * @Author: di.zhang
     * @Date: 2023/11/13 15:57
     * @Param: java.lang.string
     * @Return: java.lang.string
     */
    public static String encrypt(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new NullPointerException("加密字符不能为空");
        }
        byte[] bytes = new byte[0];
        try {
            bytes = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Description: base64解密
     *
     * @Author: di.zhang
     * @Date: 2023/11/13 15:59
     * @Param: java.lang.string
     * @Return: java.lang.string
     */
    public static String decrypt(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new NullPointerException("解密字符不能为空");
        }
        String decryptStr = null;
        try {
            decryptStr = new String(Base64.getDecoder().decode(str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return decryptStr;
    }

    public static void main(String[] args) {
        String str = "测试base64加密";
        String encryptStr = encrypt(str);
        System.out.println("加密:" + encryptStr);
        String decryptStr = decrypt(encryptStr);
        System.out.println("解密:" + decryptStr);
    }
}
