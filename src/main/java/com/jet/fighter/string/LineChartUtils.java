package com.jet.fighter.string;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 获取日期折线图的  x轴
 * <p>
 * Description: {@link StringUtils }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 16:59
 * @Version: v1.0
 */
public class LineChartUtils {

    //初始种子
    public static List<String> HOURS = Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");

    private LineChartUtils() {

    }

    /**
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> initHours(LocalDateTime startTime,LocalDateTime endTime){
        int start = startTime.getHour() + 1;
        int end = endTime.getHour() + 1 >= 24 ? 0 : endTime.getHour() + 1;

        //是同一天
        if (startTime.getDayOfYear() == endTime.getDayOfYear() && end != 0) {
            if (start > end + 1) {
                List<String> list = HOURS.subList(end, start + 1);
                Collections.reverse(list);
                return list;
            }
            return HOURS.subList(start, end + 1);
        }

        List<String> startList = HOURS.subList(start, HOURS.size());
        List<String> endList = HOURS.subList(0, end + 1);

        List<String> hourList = new ArrayList<>();
        hourList.addAll(startList);
        hourList.addAll(endList);
        return hourList;
    }

}
