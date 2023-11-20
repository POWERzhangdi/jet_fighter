package com.jet.fighter.encryption;

import com.jet.fighter.binary.BaseConversionUtils;
import com.jet.fighter.string.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * MD5 加密
 * <p>
 * Description: {@link Md5Utils }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 16:28
 * @Version: v1.0
 */
public class Md5Utils {

    private Md5Utils() {

    }

    //MD5加密对象
    private static final MessageDigest MD5;

    //初始对象
    static {
        try {
            MD5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: MD5加密
     * 当 binary 存在的时候进行进制转换 支持 2,8,10,16,32进制的转换
     * 如果为0不在进行进制转换
     *
     * @Author: di.zhang
     * @Date: 2023/11/13 15:57
     * @Param: java.lang.string
     * @Return: java.lang.string
     */
    public static String encrypt(String str, int binary) {
        if (StringUtils.isEmpty(str)) {
            throw new NullPointerException("加密字符不能为空");
        }
        //加密
        byte[] bytes = MD5.digest(str.getBytes());
        //进制转换
        if (0 != binary) {
            return BaseConversionUtils.binaryToString(bytes, binary);
        }
        return new String(bytes);
    }

    /**
     * Description: MD5解密
     *
     * @Author: di.zhang
     * @Date: 2023/11/13 15:57
     * @Param: java.lang.string
     * @Return: java.lang.string
     */
    public static String decrypt(String encrypt, String str,int binary) {
        if (StringUtils.isEmpty(str)) {
            throw new NullPointerException("加密字符不能为空");
        }
        if (StringUtils.isEmpty(encrypt)) {
            throw new NullPointerException("密文不能为空");
        }
        String newEncrypt = encrypt(str,binary);
        if (!newEncrypt.equals(encrypt)) {
            throw new RuntimeException("MD5解密不通过");
        }
        return encrypt;
    }


    public static void main(String[] args) {
        String str = "测试MD5加密";
        String encryptStr = encrypt(str,32);
        System.out.println("加密:" + encryptStr);
        String decryptStr = decrypt(encryptStr, str,32);
        System.out.println("解密:" + decryptStr);
    }
}
