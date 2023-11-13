package com.jet.fighter.strategy.select;

import com.jet.fighter.strategy.StrategyBehaviorSelector;
import com.jet.fighter.strategy.StrategyEnums;
import com.jet.fighter.string.StringUtils;

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
public class DogStrategyBehaviorSelector implements StrategyBehaviorSelector<String> {


    @Override
    public StrategyEnums strategy() {
        return StrategyEnums.DOG;
    }

    @Override
    public String behavior(String s) {
        return s + "行为:汪汪";
    }
}
