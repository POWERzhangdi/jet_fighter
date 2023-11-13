package com.jet.fighter.strategy.factory;

import com.jet.fighter.strategy.StrategyBehaviorSelector;
import com.jet.fighter.strategy.StrategyEnums;
import com.jet.fighter.strategy.select.CatStrategyBehaviorSelector;
import com.jet.fighter.strategy.select.DogStrategyBehaviorSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 没事多吃华莱士,喷射战士
 * <p>
 * 策略工厂
 * <p>
 * Description: {@link StrategyBehaviorFactory }
 *
 * @Author: di.zhang
 * @Date: 2022/8/26 17:23
 * @Version: v1.0
 */
public class StrategyBehaviorFactory {

    private List<StrategyBehaviorSelector> selects;

    public StrategyBehaviorFactory() {
        selects = new ArrayList<>();
        CatStrategyBehaviorSelector catSelect = new CatStrategyBehaviorSelector();
        DogStrategyBehaviorSelector dogSelect = new DogStrategyBehaviorSelector();
        selects.add(catSelect);
        selects.add(dogSelect);
        this.selects = selects;
    }

    public StrategyBehaviorSelector getSelect(StrategyEnums strategyEnums) {

        if (null == strategyEnums) {
            throw new NullPointerException("策略行为不能为空");
        }

        return selects.stream().filter(item -> Objects.equals(item.strategy(), strategyEnums))
                .findAny().orElseThrow(() -> new NullPointerException("策略行为找不到"));
    }
}
