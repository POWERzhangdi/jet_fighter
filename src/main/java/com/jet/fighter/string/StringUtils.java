package com.jet.fighter.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 字符串处理
 * <p>
 * Description: {@link StringUtils }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 16:59
 * @Version: v1.0
 */
public class StringUtils {

    /**
     * 纯数字判断
     */
    private static final Pattern numberPattern = Pattern.compile("[0-9]*");

    private StringUtils() {
    }

    /**
     * Description: 判断字符串是否为空
     * true - 为空
     * false - 不为空
     *
     * @Author: di.zhang
     * @Date: 2022/8/26 17:06
     * @Param [java.lang.String]
     * @Return boolean
     */
    public static boolean isEmpty(String s) {
        return null == s || s.length() == 0 || s.trim().length() == 0;
    }

    /**
     * Description: 将字符串依据特定标识分割成 list集合
     *
     * @Author: di.zhang
     * @Date: 2022/8/26 18:29
     * @Param [java.lang.String, java.lang.String]
     * @Return java.util.List<java.lang.String>
     */
    public static List<String> stringToList(String param, String value) {

        if (StringUtils.isEmpty(param)) {
            throw new NullPointerException("param不能为空");
        }

        if (StringUtils.isEmpty(value)) {
            throw new NullPointerException("value不能为空");
        }

        String[] strings = param.split(value);

        List<String> result = new ArrayList<>(strings.length);

        for (int i = 0; i < strings.length; i++) {
            result.add(strings[i]);
        }

        return result;
    }

    /**
     * Description: 将字符串依据特定标识分割成 integer元素的 list集合
     *
     * @Author: di.zhang
     * @Date: 2022/8/28 00:12
     * @Param [java.lang.String, java.lang.String]
     * @Return java.util.List<java.lang.Integer>
     */
    public static List<Integer> stringToIntegerList(String param, String value) {

        if (StringUtils.isEmpty(param)) {
            throw new NullPointerException("param不能为空");
        }

        if (StringUtils.isEmpty(value)) {
            throw new NullPointerException("value不能为空");
        }

        String[] strings = param.split(value);

        List<Integer> result = new ArrayList<>(strings.length);

        for (int i = 0; i < strings.length; i++) {
            if (!numberPattern.matcher(strings[i]).matches()) {
                throw new ClassCastException("非数字字符串不能转换成Integer类型");
            }
            result.add(Integer.valueOf(strings[i]));
        }

        return result;
    }

    /**
     * Description: 将字符串依据特定标识分割成 long元素的 list集合
     *
     * @Author: di.zhang
     * @Date: 2022/8/28 00:12
     * @Param [java.lang.String, java.lang.String]
     * @Return java.util.List<java.lang.Long>
     */
    public static List<Long> stringToLongList(String param, String value) {

        if (StringUtils.isEmpty(param)) {
            throw new NullPointerException("param不能为空");
        }

        if (StringUtils.isEmpty(value)) {
            throw new NullPointerException("value不能为空");
        }

        String[] strings = param.split(value);

        List<Long> result = new ArrayList<>(strings.length);

        for (int i = 0; i < strings.length; i++) {
            if (!numberPattern.matcher(strings[i]).matches()) {
                throw new ClassCastException("非数字字符串不能转换成Long类型");
            }
            result.add(Long.valueOf(strings[i]));
        }
        return result;
    }

}
