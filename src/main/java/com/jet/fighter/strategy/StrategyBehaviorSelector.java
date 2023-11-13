package com.jet.fighter.strategy;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 策略接口
 * 定义广泛的行为
 * <p>
 * Description: {@link StrategyBehaviorSelector }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 17:23
 * @Version: v1.0
 */
public interface StrategyBehaviorSelector<T> {

    /**
     * 获取策略
     */
    StrategyEnums strategy();

    /**
     * 策略的行为
     */
    T behavior(T t);

}
