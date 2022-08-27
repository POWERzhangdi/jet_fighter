package com.jet.fighter.collectios;

import java.util.Collection;
import java.util.Map;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 数据集合判断
 * <p>
 * Description: {@link CollectionUtils }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 16:39
 * @Version: v1.0
 */
public class CollectionUtils {

    private CollectionUtils() {
    }


    /**
     * Description: 集合是否为空判断
     * false - 不为空
     * true - 空
     *
     * @Author: di.zhang
     * @Date: 2022/8/26 16:43
     * @Param [java.util.Collection<?>]
     * @Return boolean
     */
    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * Description: map 集合是否为空判断
     * false - 不为空
     * true - 空
     *
     * @Author: di.zhang
     * @Date: 2022/8/26 16:49
     * @Param [java.util.Map<?, ?>]
     * @Return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

}
