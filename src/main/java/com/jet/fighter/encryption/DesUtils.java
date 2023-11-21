package com.jet.fighter.encryption;

import com.jet.fighter.binary.BaseConversionUtils;
import com.jet.fighter.string.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * DES 加密
 * <p>
 * Description: {@link DesUtils }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 16:28
 * @Version: v1.0
 */
public class DesUtils {

    private DesUtils() {

    }

    /**
     * 原始密钥
     * SecretKeySpec类，其规定了秘钥的长度只能是8个字节
     */
    private static final String keys = "DESENCRY";

    /**
     * 原始密钥进行处理后的对象
     */
    private static final SecretKeySpec keySpec;

    static {
        keySpec = new SecretKeySpec(keys.getBytes(), "DES");
    }

    /**
     * Description:加密
     *
     * @Author: di.zhang
     * @Date: 2023/11/21 15:27
     * @Param: message 加密信息
     * @Return: java.lang.string
     */
    public static String encrypt(String message) {
        if (StringUtils.isEmpty(message)) {
            throw new NullPointerException("加密消息为空");
        }
        byte[] bytes = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            bytes = cipher.doFinal(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String encryptStr = Base64.getEncoder().encodeToString(bytes);
        return encryptStr;
    }

    /**
     * Description:解密
     *
     * @Author: di.zhang
     * @Date: 2023/11/21 15:33
     * @Param: secretTxt 密文
     * @Return: java.lang.string
     */
    public static String decrypt(String secretTxt) {
        if (StringUtils.isEmpty(secretTxt)) {
            throw new NullPointerException("密文为空");
        }

        byte[] bytes = new byte[0];
        try {
            byte[] secretBytes = Base64.getDecoder().decode(secretTxt);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            bytes = cipher.doFinal(secretBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new String(bytes);
    }

    public static void main(String[] args) {
        String message = "测试DES加密";
        String secretTxt = encrypt(message);
        System.out.println("加密后的密文=" + secretTxt);
        String decryptMessage = decrypt(secretTxt);
        System.out.println("解密之后的信息=" + decryptMessage);
    }
}
