package com.jet.fighter.binary;

import java.math.BigInteger;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 进制转换
 * <p>
 * Description: {@link BaseConversionUtils }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 16:28
 * @Version: v1.0
 */
public class BaseConversionUtils {

    private BaseConversionUtils() {
    }

    /**
     * Description: 进制转换
     * 将byte[]转为各种进制的字符串
     * base 基数可以转换进制的范围,从Character.MIN_RADIX到Character.MAX_RADIX,超出范围后变为10进制
     * Character.MIN_RADIX - Character.MAX_RADIX (2~36之间)
     *
     * @Author: di.zhang
     * @Date: 2023/11/13 16:39
     * @Param: byte[], radix
     * @Return: java.lang.string
     */
    public static String binaryToString(byte[] bytes, int base) {
        if (null == bytes || bytes.length == 0) {
            throw new NullPointerException("需要转换的bytes不能为空");
        }
        if (base < Character.MIN_RADIX || base > Character.MAX_RADIX) {
            throw new IndexOutOfBoundsException("进制转换超过限定范围");
        }
        return new BigInteger(1, bytes).toString(base);
    }
}
