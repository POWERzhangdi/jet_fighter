package com.jet.fighter.encryption;

import com.jet.fighter.string.StringUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * RSA  加密
 * <p>
 * Description: {@link RSAUtils }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 16:28
 * @Version: v1.0
 */
public class RSAUtils {

    /**
     * RSA最大加密明文大小
     * 此配置是 密钥长度为 1024的配置
     */
    private static int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     * 此配置是 密钥长度为 1024的配置
     */
    private static int MAX_DECRYPT_BLOCK = 128;

    private static final KeyPairGenerator keyPairGenerator = initKeyPair();

    private static final KeyFactory keyFactory = initKeyFactory();

    /**
     * 密钥长度 默认 1024
     * 密钥长度支持 1024 / 2048 / 4096 只支持1024的2倍
     */
    private static final int KEY_DEFAULT_SIZE = 1024;

    private RSAUtils() {

    }

    static KeyPairGenerator initKeyPair() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return keyPairGenerator;
    }

    static KeyFactory initKeyFactory() {
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return keyFactory;
    }

    /**
     * Description: 获取 密钥对像
     *
     * @Author: di.zhang
     * @Date: 2023/11/20 11:40
     * @Param: keySize 密钥长度
     * @Return: java.security.KeyPair
     */
    public static KeyPair createKeyPair(int keySize) {
        if (0 == keySize) {
            keySize = KEY_DEFAULT_SIZE;
        }

        if (keySize % 2 != 0) {
            throw new RuntimeException("非2的倍数不可初始话");
        }

        //判断扩大了几倍,密文,明文的最大长度相应的扩展
        int multiple = keySize / 1024;
        MAX_ENCRYPT_BLOCK = MAX_ENCRYPT_BLOCK * multiple;
        MAX_DECRYPT_BLOCK = MAX_DECRYPT_BLOCK * multiple;

        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }


    /**
     * Description: 获取公钥
     *
     * @Author: di.zhang
     * @Date: 2023/11/20 11:48
     * @Param: KeyPair keyPair
     * @Return: java.lang.string
     */
    public static String getPublicKey(KeyPair keyPair) {
        if (null == keyPair) {
            throw new NullPointerException("getPublicKey keyPair is null");
        }
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Description: 获取公钥
     *
     * @Author: di.zhang
     * @Date: 2023/11/20 11:48
     * @Param: KeyPair keyPair
     * @Return: java.lang.string
     */
    public static String getPrivateKey(KeyPair keyPair) {
        if (null == keyPair) {
            throw new NullPointerException("getPrivateKey keyPair is null");
        }
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Description: 公钥加密
     *
     * @Author: di.zhang
     * @Date: 2023/11/20 11:53
     * @Param: message 加密消息内容, publicKey 公钥
     * @Return: java.lang.string
     */
    public static String encrypt(String message, String publicKey) {
        if (StringUtils.isEmpty(message) || StringUtils.isEmpty(publicKey)) {
            throw new NullPointerException("encrypt message or publicKey is null");
        }

        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);

        String encryptStr = null;
        try {
            byte[] data = message.getBytes("utf-8");
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generatePublic(x509EncodedKeySpec));

            //超过加密 长度,开始分段加密
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int maxLength = data.length;
            int offSet = 0;
            int i = 0;
            while (maxLength - offSet > 0) {
                byte[] cache;
                if (maxLength - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, maxLength - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedBytes = out.toByteArray();
            out.close();
            //base64编码
            encryptStr = Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            return encryptStr;
        }
    }


    /**
     * Description: 私钥解密
     *
     * @Author: di.zhang
     * @Date: 2023/11/20 13:40
     * @Param: secretTxt 加密的文本,privateKey 私钥
     * @Return: java.lang.string
     */
    public static String decrypt(String secretTxt, String privateKey) {

        if (StringUtils.isEmpty(secretTxt) || StringUtils.isEmpty(privateKey)) {
            throw new NullPointerException("decrypt secretTxt or privateKey is null");
        }

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        String decryptStr = null;
        try {
            byte[] secretTxtBytes = Base64.getDecoder().decode(secretTxt);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, keyFactory.generatePrivate(pkcs8EncodedKeySpec));

            //密文超过解密长度,分段解密
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int maxLength = secretTxtBytes.length;
            int offSet = 0;
            int i = 0;
            while (maxLength - offSet > 0) {
                byte[] cache;
                if (maxLength - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(secretTxtBytes, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(secretTxtBytes, offSet, maxLength - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedBytes = out.toByteArray();
            out.close();
            decryptStr = new String(decryptedBytes, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            return decryptStr;
        }
    }


    public static void main(String[] args) {
        KeyPair keyPair = createKeyPair(1024);
        String publicKey = getPublicKey(keyPair);
        String privateKey = getPrivateKey(keyPair);
        System.out.println("生成的公钥=" + publicKey);
        System.out.println("生成的私钥=" + privateKey);

        String message = "测试RSA非对称加密";

        String secretTxt = encrypt(message, publicKey);
        System.out.println("加密之后的密文=" + secretTxt);
        System.out.println("解密之后的文字=" + decrypt(secretTxt, privateKey));

    }


}
