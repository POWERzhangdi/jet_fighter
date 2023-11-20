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

    /**
     * 盐值
     */
    private static final String SALT = "华莱士";

    /**
     * 加密次数
     */
    private static final int REPEAT = 3;

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

    /**
     * Description: base64加盐加密
     *
     * @Author: di.zhang
     * @Date: 2023/11/20 17:16
     * @Param: java.lang.string
     * @Return: java.lang.string
     */
    private static String saltEncrypt(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new NullPointerException("加密字符不能为空");
        }
        byte[] bytes = new byte[0];
        try {
            //加盐
            String tmpStr = str + SALT;
            bytes = tmpStr.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //多次加密
        for (int i = 0; i < REPEAT; i++) {
            bytes = Base64.getEncoder().encode(bytes);
        }
        return new String(bytes);
    }

    /**
     * Description: base64加盐解密
     *
     * @Author: di.zhang
     * @Date: 2023/11/13 15:59
     * @Param: java.lang.string
     * @Return: java.lang.string
     */
    public static String saltDecrypt(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new NullPointerException("解密字符不能为空");
        }
        String decryptStr = null;
        try {
            byte[] bytes = str.getBytes();
            //多次解密
            for (int i = 0; i < 3; i++) {
                bytes = Base64.getDecoder().decode(bytes);
            }
            decryptStr = new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //删除盐值
        return decryptStr.substring(0, decryptStr.length() - SALT.length());
    }

    public static void main(String[] args) {
        String str = "测试base64加密";
        String encryptStr = encrypt(str);
        System.out.println("加密:" + encryptStr);
        String decryptStr = decrypt(encryptStr);
        System.out.println("解密:" + decryptStr);

        String saltStr = "测试base64加密salt{}";
        String saltEncryptStr = saltEncrypt(saltStr);
        System.out.println("salt加密:" + saltEncryptStr);
        String saltDecryptStr = saltDecrypt(saltEncryptStr);
        System.out.println("salt解密:" + saltDecryptStr);
    }
}
