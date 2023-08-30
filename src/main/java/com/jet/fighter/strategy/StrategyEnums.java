package com.jet.fighter.strategy;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 策略的集合，存储各种策略用户后续的路由
 * <p>
 * Description: {@link StrategyEnums }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 17:23
 * @Version: v1.0
 */
public enum StrategyEnums {

    CAT("cat","猫咪行为"),
    DOG("dog","狗狗行为"),

    ;

    /**
     * 策略
     */
    private final String strategy;

    /**
     * 策略简介
     */
    private final String desc;


    StrategyEnums(String strategy, String desc) {
        this.strategy = strategy;
        this.desc = desc;
    }

    public String getStrategy() {
        return strategy;
    }

    public String getDesc() {
        return desc;
    }
}
