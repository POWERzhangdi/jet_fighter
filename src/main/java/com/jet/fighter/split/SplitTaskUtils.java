package com.jet.fighter.split;

import com.google.common.collect.Lists;
import com.jet.fighter.collectios.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 任务分割工具类
 * <p>
 * Description: {@link SplitTaskUtils }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 16:28
 * @Version: v1.0
 */
public class SplitTaskUtils <T>{

    public SplitTaskUtils(int TASK_MAX, int SUBMIT_TOTAL_TASK) {
        if (TASK_MAX > this.MAX_SET_UP_TASK) {
            throw new RuntimeException("提交的最小任务数大于任务拆分设置的阀值,阀值=1000,提交的任务总数=" + TASK_MAX);
        }
        this.TASK_MAX = TASK_MAX;
        this.SUBMIT_TOTAL_TASK = SUBMIT_TOTAL_TASK;
    }

    /**
     * 每个任务最大 500
     * 默认 500
     */
    private int TASK_MAX = 500;

    /**
     * 提交的总任务数
     * 默认 500
     */
    private int SUBMIT_TOTAL_TASK = 500;

    /**
     * 阀值 最大拆分的小任务数 不可超过此大小
     */
    private final int MAX_SET_UP_TASK = 1000;

    /**
     * 任务拆分后的结果 返回list
     */
    private List<List<T>> totalTaskList = new ArrayList<>(this.getTaskListSize());

    /**
     * 任务拆分后的结果 返回map
     * key - 循环下标
     * value - 拆分的小任务集合
     */
    private Map<Integer, List<T>> totalTaskMap = new HashMap<>(this.getTaskListSize());


    /**
     * Description: 提供对外的 静态方法 方便调用
     *
     * @Author: di.zhang
     * @Date: 2022/7/29 16:02
     * @Param [java.util.List<T>, int]
     * @Return java.util.List<java.util.List < T>>
     */
    public static <T> List<List<T>> getStaticTaskList(List<T> tList, int taskMax) {
        SplitTaskUtils splitTaskUtils = new SplitTaskUtils(taskMax, tList.size());
        return splitTaskUtils.getTaskList(tList);
    }

    /**
     * Description: 提供对外的 静态方法 方便调用
     *
     * @Author: di.zhang
     * @Date: 2022/7/29 16:02
     * @Param [java.util.List<T>, int]
     * @Return java.util.List<java.util.List < T>>
     */
    public static <T> List<List<T>> getStaticStreamTaskList(List<T> tList, int taskMax) {
        SplitTaskUtils splitTaskUtils = new SplitTaskUtils(taskMax, tList.size());
        return splitTaskUtils.getStreamTaskList(tList);
    }

    /**
     * Description: 提供对外的 静态方法 方便调用
     *
     * @Author: di.zhang
     * @Date: 2022/7/29 16:02
     * @Param [java.util.List<T>, int]
     * @Return java.util.List<java.util.List < T>>
     */
    public static <T> List<List<T>> getStaticGuavaTaskList(List<T> tList, int taskMax) {
        SplitTaskUtils splitTaskUtils = new SplitTaskUtils(taskMax, tList.size());
        return splitTaskUtils.getGuavaTaskList(tList);
    }

    /**
     * Description: 提供对外的 静态方法 方便调用
     *
     * @Author: di.zhang
     * @Date: 2022/7/29 16:02
     * @Param [java.util.List<T>, int]
     * @Return java.util.List<java.util.List < T>>
     */
    public static <T> Map<Integer, List<T>> getStaticTaskMap(List<T> tList, int taskMax) {
        SplitTaskUtils splitTaskUtils = new SplitTaskUtils(taskMax, tList.size());
        return splitTaskUtils.getTaskMap(tList);
    }

    /**
     * Description: 此方法将list 分割成 均等的 n 个list
     * 列子1: [1,2,3,4,5,6,7]
     * 如果均等 n = 2 结果 [[1, 2, 3, 4], [5, 6, 7]]
     * 列子2: [1,2,3,4,5,6]
     * 如果均等 n = 2 结果 [[1, 2, 3], [5, 6, 7]]
     *
     * @Author: di.zhang
     * @Date: 2022/7/29 15:53
     * @Param [java.util.List<T>, int]
     * @Return java.util.List<java.util.List < T>>
     */
    public static <T> List<List<T>> getAverageAssignList(List<T> tList, int n) {
        if(CollectionUtils.isEmpty(tList)){
            throw new RuntimeException("list is null");
        }

        //这里是均等 所以不确定大小,就自己定义到方法内部
        List<List<T>> result = new ArrayList<>();

        int mod = tList.size() % n;
        int number = tList.size() / n;

        int offset = 0;
        for (int i = 0; i < n; i++) {
            List<T> value;
            if (mod > 0) {
                value = tList.subList(i * number + offset, (i + 1) * number + offset + 1);
                mod--;
                offset++;
            } else {
                value = tList.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    /**
     * Description: 获取 拆分的任务
     * 手动实现
     * 这个实现就不高明 显然开辟了 第三个存储介质 来存储数据
     * 没理解拆分任务的本质是什么
     *
     * @Author: di.zhang
     * @Date: 2022/7/29 14:21
     * @Param [java.util.List<T>]
     * @Return java.util.List<java.util.List < T>>
     */
    private List<List<T>> getTaskList(List<T> tList) {
        if(CollectionUtils.isEmpty(tList)){
            throw new RuntimeException("list is null");
        }

        int mod = this.SUBMIT_TOTAL_TASK % this.TASK_MAX;
        int number = this.SUBMIT_TOTAL_TASK / this.TASK_MAX;

        for (int i = 0; i < number; i++) {
            int start = i * TASK_MAX;
            int end = TASK_MAX + start;
            totalTaskList.add(tList.subList(start, end));
        }

        if (mod != 0) {
            totalTaskList.add(tList.subList(number * TASK_MAX, tList.size()));
        }

        return totalTaskList;
    }

    /**
     * Description: 获取 拆分的任务
     * Stream 实现 {@link java.util.stream.Stream}
     *
     * @Author: di.zhang
     * @Date: 2022/7/29 14:21
     * @Param [java.util.List<T>]
     * @Return java.util.List<java.util.List < T>>
     */
    private List<List<T>> getStreamTaskList(List<T> tList) {
        if(CollectionUtils.isEmpty(tList)){
            throw new RuntimeException("list is null");
        }
        return Stream.iterate(0, n -> n + 1)
                .limit(this.getTaskListSize())
                .parallel()
                .map(a -> tList.stream()
                        .skip(a * TASK_MAX)
                        .limit(TASK_MAX)
                        .parallel()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    /**
     * Description: 获取 拆分的任务
     * Guava 实现 {@link Lists}
     * 这里实现的 很高明 不会 另外开辟存储 介质 来存储最终的数据
     * 分割的 最终是 我们 get结果即可,我们的诉求就是 获取 分割后的数据
     *
     * @Author: di.zhang
     * @Date: 2022/7/29 14:21
     * @Param [java.util.List<T>]
     * @Return java.util.List<java.util.List < T>>
     */
    private List<List<T>> getGuavaTaskList(List<T> tList) {
        if(CollectionUtils.isEmpty(tList)){
            throw new RuntimeException("list is null");
        }
        return Lists.partition(tList, TASK_MAX);
    }


    /**
     * Description: 获取任务拆分后的map集合
     *
     * @Author: di.zhang
     * @Date: 2022/7/29 15:43
     * @Param [java.util.List<T>]
     * @Return java.util.Map<java.lang.Integer, java.util.List < T>>
     */
    private Map<Integer, List<T>> getTaskMap(List<T> tList) {
        if(CollectionUtils.isEmpty(tList)){
            throw new RuntimeException("list is null");
        }
        List<List<T>> resultList = this.getGuavaTaskList(tList);
        for (int i = 0; i < resultList.size(); i++) {
            totalTaskMap.put(i, resultList.get(i));
        }
        return totalTaskMap;
    }

    /**
     * 设置返回参数 集合 初始化大小
     *
     * @return
     */
    private int getTaskListSize() {
        return (this.getSUBMIT_TOTAL_TASK() + this.getTASK_MAX() - 1) / this.getTASK_MAX();
    }

    private int getTASK_MAX() {
        return TASK_MAX;
    }

    private void setTASK_MAX(int TASK_MAX) {
        this.TASK_MAX = TASK_MAX;
    }

    private int getSUBMIT_TOTAL_TASK() {
        return SUBMIT_TOTAL_TASK;
    }

    private void setSUBMIT_TOTAL_TASK(int SUBMIT_TOTAL_TASK) {
        this.SUBMIT_TOTAL_TASK = SUBMIT_TOTAL_TASK;
    }

}
